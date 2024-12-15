package pt.isel.pdm.tictactoe.services.firestore

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.MetadataChanges
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.timeout
import kotlinx.coroutines.tasks.await
import pt.isel.pdm.tictactoe.domain.GameInfo
import pt.isel.pdm.tictactoe.domain.GameLobby
import pt.isel.pdm.tictactoe.domain.GameSession
import pt.isel.pdm.tictactoe.services.MatchmakingService
import pt.isel.pdm.tictactoe.services.firestore.FirestoreExtensions.Companion.toGameInfo
import pt.isel.pdm.tictactoe.services.firestore.FirestoreExtensions.Companion.toGameLobby
import pt.isel.pdm.tictactoe.services.firestore.FirestoreExtensions.Companion.toGameSession
import java.time.Duration
import java.util.Random
import java.util.UUID
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class FirestoreMatchmakingService(
    private val db: FirebaseFirestore
) : MatchmakingService {
    override fun activeLobbies(): Flow<List<GameLobby>> = db
        .collection(FirestoreExtensions.LOBBY_COLLECTION)
        .snapshots()
        .map { list ->
            list.map { doc ->
                doc.toGameLobby()
            }
        }

    override suspend fun createLobbyAndWaitForPlayer(userName: String): GameInfo {

        //
        //  creates a lobby and waits for the player 2
        //  The matchmaking is made when player 2 inserts in the BD object a game id
        //  P1 is responsible to create the game instance in the DB
        //  P1 then waits for P2 to add its name to the game instance completing the matchmaking
        //

        val lobbyDoc = db.collection(FirestoreExtensions.LOBBY_COLLECTION).add(
            hashMapOf(
                FirestoreExtensions.LOBBY_DISPLAY_NAME_FIELD to userName
            )
        ).await()

        var lobbyDocReference: DocumentReference? = lobbyDoc

        var gameDoc: DocumentReference? = null

        try {
            val gameId = lobbyDocReference!!
                .snapshots()
                .filter { doc ->
                    doc.getString(FirestoreExtensions.LOBBY_GAMEID_FIELD)
                        .isNullOrBlank() == false
                }
                .map { doc -> doc.getString(FirestoreExtensions.LOBBY_GAMEID_FIELD)!! }
                .first()

            //create game
            gameDoc = db.collection(FirestoreExtensions.GAMES_COLLECTION).document(gameId)

            gameDoc.set(
                hashMapOf(
                    FirestoreExtensions.GAME_PLAYER1_NAME to userName,
                    FirestoreExtensions.GAME_IS_PLAYER1_TURN to Random().nextBoolean(),
                    FirestoreExtensions.GAME_ID_FIELD to gameId,
                    FirestoreExtensions.GAME_BOARD_FIELD to FirestoreExtensions.EMPTY_GAME_BOARD,
                    FirestoreExtensions.GAME_STATE_FIELD to FirestoreExtensions.REMOTE_GAME_STATE_RUNNING
                )
            ).await()

            //delete lobby
            lobbyDocReference.delete().await()
            lobbyDocReference = null

            //wait for player 2, with timeout!
            val game = gameDoc.snapshots()
                .filter { doc ->
                    doc.getString(FirestoreExtensions.GAME_PLAYER2_NAME).isNullOrBlank() == false
                }
                .timeout(60.toDuration(DurationUnit.SECONDS))
                .first()

            return game.toGameInfo(userName)
        } catch (e: Exception) {
            lobbyDocReference?.delete()?.await()
            gameDoc?.delete()?.await()
            throw e
        }
    }

    override suspend fun joinLobby(user: String, lobby: GameLobby): GameInfo {

        //
        //  Players can't have the same name!
        //
        val userName = if (user == lobby.displayName) "${user}2" else user

        //  unique gameid
        val gameId = userName + UUID.randomUUID().toString()

        val lobbyReference = db.collection(FirestoreExtensions.LOBBY_COLLECTION)
            .document(lobby.id)

        //Player 2 sets unique name on lobby
        lobbyReference
            .update(FirestoreExtensions.LOBBY_GAMEID_FIELD, gameId)
            .await()

        //
        // Wait for the lobby to be destroyed
        //
        lobbyReference.snapshots()
            .filter { obj -> obj.exists() == false }
            .first()

        val gameRef = db.collection(FirestoreExtensions.GAMES_COLLECTION).document(gameId)


        var gameDoc = gameRef.get().await()

        if (gameDoc == null || gameDoc.exists() == false)
            throw Exception("Failed to connect with a player")

        //Player 2 adds its player name and game starts
        gameRef.update(FirestoreExtensions.GAME_PLAYER2_NAME, userName).await()

        return gameRef.get().await().toGameInfo(userName)

    }

    override suspend fun cancel() {
        TODO("Not yet implemented")
    }
}