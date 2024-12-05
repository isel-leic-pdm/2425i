package pt.isel.pdm.tictactoe.services

import kotlinx.coroutines.flow.Flow
import pt.isel.pdm.tictactoe.domain.GameLobby
import pt.isel.pdm.tictactoe.domain.GameSession

interface MatchmakingService {
    fun activeLobbies(): Flow<List<GameLobby>>
    suspend fun createLobbyAndWaitForPlayer(userName: String): GameSession
    suspend fun joinLobby(userName: String, lobby: GameLobby): GameSession
    suspend fun cancel()
}