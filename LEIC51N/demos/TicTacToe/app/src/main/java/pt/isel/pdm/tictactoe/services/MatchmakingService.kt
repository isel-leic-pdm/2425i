package pt.isel.pdm.tictactoe.services

import kotlinx.coroutines.flow.Flow
import pt.isel.pdm.tictactoe.domain.GameInfo
import pt.isel.pdm.tictactoe.domain.GameLobby
import pt.isel.pdm.tictactoe.domain.GameSession

interface MatchmakingService {
    fun activeLobbies(): Flow<List<GameLobby>>
    suspend fun createLobbyAndWaitForPlayer(userName: String): GameInfo
    suspend fun joinLobby(userName: String, lobby: GameLobby): GameInfo
    suspend fun cancel()
}