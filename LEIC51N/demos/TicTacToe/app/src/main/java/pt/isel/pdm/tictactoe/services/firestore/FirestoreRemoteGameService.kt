package pt.isel.pdm.tictactoe.services.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.FirebaseFirestoreException.*
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.timeout
import kotlinx.coroutines.tasks.await
import pt.isel.pdm.tictactoe.domain.Cell
import pt.isel.pdm.tictactoe.domain.Cells
import pt.isel.pdm.tictactoe.domain.GameInfo
import pt.isel.pdm.tictactoe.domain.GameSession
import pt.isel.pdm.tictactoe.domain.GameState
import pt.isel.pdm.tictactoe.domain.getMyCellState
import pt.isel.pdm.tictactoe.services.RemoteGameService
import pt.isel.pdm.tictactoe.services.firestore.FirestoreExtensions.Companion.GAMES_COLLECTION
import pt.isel.pdm.tictactoe.services.firestore.FirestoreExtensions.Companion.toGameSession
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class FirestoreRemoteGameService(
    private val db: FirebaseFirestore,
    private val timeout: Duration = 10.toDuration(DurationUnit.SECONDS)
) : RemoteGameService {
    override suspend fun getGameSession(info: GameInfo): GameSession {
        return getGameSession(info.gameId, info.playerName)
    }

    private suspend fun getGameSession(
        id: String,
        playerName: String
    ): GameSession {
        return db
            .collection(FirestoreExtensions.GAMES_COLLECTION)
            .document(id)
            .get()
            .await()
            .toGameSession(playerName)
    }

    override suspend fun play(game: GameSession, idx: Int): GameSession {

        //  Server Logic
        val board = game.board.toMutableList()
        board[idx] = Cell.createCell(idx, game.getMyCellState())

        var win = Cells.checkPlayerWin(board)

        var state = GameState.Running
        if (win)
            state = GameState.Win
        else if (Cells.checkDraw(board))
            state = GameState.Draw


        val map = if (state != GameState.Running)
            mapOf(
                FirestoreExtensions.GAME_BOARD_FIELD to FirestoreExtensions.boardToString(
                    board
                ),
                FirestoreExtensions.GAME_STATE_FIELD to FirestoreExtensions.gameStateToRemoteState(
                    state,
                    game
                ),
                FirestoreExtensions.GAME_IS_PLAYER1_TURN to !game.isPlayer1
            )
        else {
            mapOf(
                FirestoreExtensions.GAME_BOARD_FIELD to FirestoreExtensions.boardToString(
                    board
                ),
                FirestoreExtensions.GAME_IS_PLAYER1_TURN to !game.isPlayer1
            )
        }

        // Db Logic
        db.collection(FirestoreExtensions.GAMES_COLLECTION)
            .document(game.gameId)
            .update(map)
            .await()


        val ret = getGameSession(game.gameId, game.myName)

        //
        //  If for some reason the state isn't the same as we set on the DB
        //  the other player quit so, delete it
        //
        if (ret.gameState != state)
            tryDeletingGame(game)

        return ret
    }


    override suspend fun waitForOtherPlayer(game: GameSession): GameSession {

        try {
            val ret = db.collection(GAMES_COLLECTION)
                .document(game.gameId)
                .snapshots()
                .timeout(timeout)
                .map { it.toGameSession(game.myName) }
                .filter { it.isMyTurn || it.gameState != GameState.Running }
                .first()

            if (ret.gameState != GameState.Running)
                tryDeletingGame(game)

            return ret

        } catch (e: TimeoutCancellationException) {
            return updateGameState(game, GameState.Win)
        }


    }

    override suspend fun forfeitGame(remoteGame: GameSession): GameSession {
        return updateGameState(remoteGame, GameState.Lose)
    }

    private suspend fun updateGameState(remoteGame: GameSession, state: GameState): GameSession {
        db.collection(FirestoreExtensions.GAMES_COLLECTION)
            .document(remoteGame.gameId)
            .update(
                mapOf(
                    FirestoreExtensions.GAME_STATE_FIELD to FirestoreExtensions.gameStateToRemoteState(
                        state,
                        remoteGame
                    )
                )
            )
            .await()

        return remoteGame.copy(gameState = state)
    }

    private suspend fun tryDeletingGame(game: GameSession) {
        db
            .collection(FirestoreExtensions.GAMES_COLLECTION)
            .document(game.gameId)
            .delete()
            .await()
    }


}