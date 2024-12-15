package pt.isel.pdm.tictactoe.ui.remotegame

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import pt.isel.pdm.tictactoe.domain.Cell
import pt.isel.pdm.tictactoe.domain.Cells
import pt.isel.pdm.tictactoe.domain.GameInfo
import pt.isel.pdm.tictactoe.domain.GameSession
import pt.isel.pdm.tictactoe.domain.GameState
import pt.isel.pdm.tictactoe.domain.ShouldNotHappenException
import pt.isel.pdm.tictactoe.domain.getMyCellState
import pt.isel.pdm.tictactoe.services.RemoteGameService
import pt.isel.pdm.tictactoe.ui.BaseViewModel

data class RemoteGame(
    val board: List<Cell>,
    val challenger: String
) {
    companion object {
        val none = RemoteGame(Cells.emptyBoard, "")
    }
}


sealed interface RemoteGameViewState {

    val remoteGame: RemoteGame

    data class Starting(override val remoteGame: RemoteGame) : RemoteGameViewState
    data class MyTurn(override val remoteGame: RemoteGame) : RemoteGameViewState
    data class RemotePlayerTurn(override val remoteGame: RemoteGame) : RemoteGameViewState
    data class Draw(override val remoteGame: RemoteGame) : RemoteGameViewState
    data class Win(override val remoteGame: RemoteGame) : RemoteGameViewState
    data class Lose(override val remoteGame: RemoteGame) : RemoteGameViewState
}


class RemoteGameViewModel(
    private val remoteGameService: RemoteGameService,
) : BaseViewModel() {


    private val mutableFlow =
        MutableStateFlow<RemoteGameViewState>(RemoteGameViewState.Starting(RemoteGame.none))

    private var gameSession = GameSession.none
    val stateFlow: StateFlow<RemoteGameViewState>
        get() = mutableFlow

    fun start(gameInfo: GameInfo) = viewModelAction {
        val game = remoteGameService.getGameSession(gameInfo)
        publishGameSession(game)

        if(!game.isMyTurn)
            WaitForOtherPlayer()

    }

    fun play(c: Cell) = viewModelAction {

        if (mutableFlow.value !is RemoteGameViewState.MyTurn)
            throw ShouldNotHappenException("Playing outside my turn")

        val idx = mutableFlow.value.remoteGame.board.indexOf(c)

        //change locally to update the ui with the player move
        val mutableBoard = gameSession.board.toMutableList()
        mutableBoard[idx] = c.copy(state = gameSession.getMyCellState())

        mutableFlow.emit(
            RemoteGameViewState.RemotePlayerTurn(
                mutableFlow.value.remoteGame.copy(
                    board = mutableBoard,
                )
            )
        )
        //use the old gameSession when contacting the server
        publishGameSession(remoteGameService.play(gameSession, idx))

        if (gameSession.gameState != GameState.Running)
            return@viewModelAction


        WaitForOtherPlayer()
    }

    private suspend fun RemoteGameViewModel.WaitForOtherPlayer() {
        while (mutableFlow.value is RemoteGameViewState.RemotePlayerTurn) {
            publishGameSession(remoteGameService.waitForOtherPlayer(gameSession))
        }
    }

    fun forfeit() = viewModelAction {
        publishGameSession(remoteGameService.forfeitGame(gameSession))
    }

    private suspend fun publishGameSession(session: GameSession) {
        gameSession = session
        val game = RemoteGame(session.board, session.otherPlayerName)
        when (session.gameState) {
            GameState.Running -> {
                if (session.isMyTurn)
                    mutableFlow.emit(RemoteGameViewState.MyTurn(game))
                else
                    mutableFlow.emit(RemoteGameViewState.RemotePlayerTurn(game))
            }

            GameState.Draw -> mutableFlow.emit(RemoteGameViewState.Draw(game))
            GameState.Win -> mutableFlow.emit(RemoteGameViewState.Win(game))
            GameState.Lose -> mutableFlow.emit(RemoteGameViewState.Lose(game))
        }
    }


}