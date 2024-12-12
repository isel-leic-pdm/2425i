package pt.isel.pdm.tictactoe.services

import pt.isel.pdm.tictactoe.domain.GameInfo
import pt.isel.pdm.tictactoe.domain.GameSession


interface RemoteGameService {


    suspend fun getGameSession(info: GameInfo): GameSession
    suspend fun play(game: GameSession, idx: Int): GameSession
    suspend fun waitForOtherPlayer(game: GameSession): GameSession
    suspend fun forfeitGame(remoteGame: GameSession): GameSession
}