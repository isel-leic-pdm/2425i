package pt.isel.pdm.tictactoe.services.firestore

import com.google.firebase.firestore.DocumentSnapshot
import pt.isel.pdm.tictactoe.domain.Cell
import pt.isel.pdm.tictactoe.domain.CellState
import pt.isel.pdm.tictactoe.domain.Cells
import pt.isel.pdm.tictactoe.domain.GameInfo
import pt.isel.pdm.tictactoe.domain.GameLobby
import pt.isel.pdm.tictactoe.domain.GameSession
import pt.isel.pdm.tictactoe.domain.GameState
import pt.isel.pdm.tictactoe.services.RemoteGameService

class FirestoreExtensions {
    companion object {

        const val LOBBY_COLLECTION = "lobbies"

        const val LOBBY_DISPLAY_NAME_FIELD = "displayName"
        const val LOBBY_GAMEID_FIELD = "gameId"


        const val GAMES_COLLECTION = "games"
        const val GAME_PLAYER1_NAME = "player1"
        const val GAME_PLAYER2_NAME = "player2"
        const val GAME_IS_PLAYER1_TURN = "isPlayer1Turn"
        const val GAME_ID_FIELD = "gameId"
        const val GAME_BOARD_FIELD = "board"
        const val GAME_STATE_FIELD = "state"

        const val EMPTY_GAME_BOARD = "_________"


        const val GAME_MOVE_PLAYER1 = '1'
        const val GAME_MOVE_PLAYER2 = '2'


        const val REMOTE_GAME_STATE_RUNNING = 0
        const val REMOTE_GAME_STATE_DRAW = 1
        const val REMOTE_GAME_STATE_WINNER_P1 = 10
        const val REMOTE_GAME_STATE_WINNER_P2 = 20


        fun documentToGameLobby(lobbyDoc: DocumentSnapshot): GameLobby {
            return GameLobby(
                id = lobbyDoc.id,
                displayName = lobbyDoc.getString(LOBBY_DISPLAY_NAME_FIELD)!!
            )
        }

        /*
        hashMapOf(
                            FirestoreExtensions.GAME_PLAYER1_NAME to userName,
                            FirestoreExtensions.GAME_IS_PLAYER1_TURN to Random().nextBoolean(),
                            FirestoreExtensions.GAME_ID_FLIED to gameId,
                            FirestoreExtensions.GAME_BOARD_FIELD to FirestoreExtensions.EMPTY_GAME_BOARD
                        )

            val gameId: String,
            val myName : String,
            val otherPlayerName: String,
            val isMyTurn: Boolean,
            val board: List<Cell>,
            val gameState: GameState
         */

        fun documentToGameInfo(
            doc: DocumentSnapshot,
            thisPlayerName: String

        ): GameInfo {
            return GameInfo(
                doc.getString(GAME_ID_FIELD)!!,
                thisPlayerName
            )
        }

        fun documentToGameSession(
            doc: DocumentSnapshot,
            thisPlayerName: String

        ): GameSession {

            val p1 = doc.getString(GAME_PLAYER1_NAME)!!
            val p2 = doc.getString(GAME_PLAYER2_NAME)!!

            val thisPlayerP1 = (p1 == thisPlayerName)

            val otherPlayerName = if (thisPlayerP1) p2 else p1

            val p1Turn = doc.getBoolean(GAME_IS_PLAYER1_TURN)!!

            val remoteState = doc.getLong(GAME_STATE_FIELD)!!.toInt()

            var state = remoteStateToGameState(remoteState, thisPlayerP1)



            return GameSession(
                gameId = doc.id,
                myName = thisPlayerName,
                otherPlayerName = otherPlayerName,
                isMyTurn = p1Turn && p1 == thisPlayerName || !p1Turn && p1 != thisPlayerName,
                board = stringToBoard(doc.getString(GAME_BOARD_FIELD)!!),
                gameState = state,
                isPlayer1 = thisPlayerP1
            )
        }

        private fun remoteStateToGameState(
            remoteState: Int,
            thisPlayerP1: Boolean
        ): GameState {
            var state = GameState.Running

            when (remoteState) {
                REMOTE_GAME_STATE_DRAW -> state = GameState.Draw
                REMOTE_GAME_STATE_WINNER_P1 -> state =
                    if (thisPlayerP1) GameState.Win else GameState.Lose

                REMOTE_GAME_STATE_WINNER_P2 -> state =
                    if (thisPlayerP1) GameState.Lose else GameState.Win
            }
            return state
        }

        fun gameStateToRemoteState(
            gameState: GameState,
            gameSession: GameSession
        ): Int {
            return when (gameState) {
                GameState.Running -> REMOTE_GAME_STATE_RUNNING
                GameState.Win -> if (gameSession.isPlayer1) REMOTE_GAME_STATE_WINNER_P1 else REMOTE_GAME_STATE_WINNER_P2
                GameState.Lose -> if (gameSession.isPlayer1) REMOTE_GAME_STATE_WINNER_P2 else REMOTE_GAME_STATE_WINNER_P1
                GameState.Draw -> REMOTE_GAME_STATE_DRAW
            }
        }


        fun DocumentSnapshot.toGameLobby(): GameLobby {
            return documentToGameLobby(this)
        }

        fun DocumentSnapshot.toGameSession(thisPlayer: String): GameSession {
            return documentToGameSession(this, thisPlayer)
        }

        fun DocumentSnapshot.toGameInfo(thisPlayer: String): GameInfo {
            return documentToGameInfo(this, thisPlayer)
        }

        fun boardToString(board: List<Cell>): String {
            val str = StringBuilder()

            board.forEachIndexed { idx, cell ->
                if (cell.state == CellState.EMPTY)
                    str.append("_")
                else
                    str.append(if (cell.state == GameSession.PLAYER1_CELLSTATE) GAME_MOVE_PLAYER1 else GAME_MOVE_PLAYER2)
            }

            return str.toString()
        }

        fun stringToBoard(board: String): List<Cell> {

            val retBoard = Cells.emptyBoard.toMutableList()
            board.forEachIndexed { idx, cell ->

                if (cell == '_')
                    return@forEachIndexed

                retBoard[idx] =
                    Cell.createCell(idx, GameSession.getCellState(cell == GAME_MOVE_PLAYER1))

            }
            return retBoard
        }
        /*
                fun mapToGameSession(gameDoc: DocumentSnapshot): GameSession {
                    return FirestoreGame(
                        player1 = gameDoc.getString(GamePlayer1Field)!!,
                        player2 = gameDoc.getString(GamePlayer2Field)!!,
                        isPlayer1Turn = gameDoc.getBoolean(GameIsPlayer1Turn)!!,
                        gameId = gameDoc.getString(GameIdField)!!,
                        board = gameDoc.getString(GameBoardField)!!
                    )
                }

         */
    }
}

