package pt.isel.pdm.tictactoe.ui.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import pt.isel.pdm.tictactoe.domain.Cell
import pt.isel.pdm.tictactoe.domain.CellState
import pt.isel.pdm.tictactoe.domain.Cells
import pt.isel.pdm.tictactoe.domain.ShouldNotHappenException
import pt.isel.pdm.tictactoe.ui.BaseViewModel


sealed interface GameViewState {

    val board: List<Cell>

    data class Running(val currentPlayer: CellState, override val board: List<Cell>) : GameViewState
    data class Draw(override val board: List<Cell>) : GameViewState
    data class Complete(val winner: CellState, override val board: List<Cell>) : GameViewState
}

class GameViewModel : BaseViewModel() {
    private var plays = 0


    var state: GameViewState by mutableStateOf(GameViewState.Running(CellState.O, Cells.emptyBoard))

    fun play(c: Cell) = viewModelActionWithRetry {

        val currState = state

        if (currState !is GameViewState.Running)
            throw ShouldNotHappenException("play: game is not running")

        var idx = currState.board.indexOf(c)

        if (currState.board[idx].state != CellState.EMPTY)
            throw ShouldNotHappenException("play: cell not empty")

        val mutableList = currState.board.toMutableList()
        mutableList[idx] = Cell.createCell(idx, currState.currentPlayer)

        plays++

        if (Cells.checkPlayerWin(mutableList)) {
            state = GameViewState.Complete(currState.currentPlayer, mutableList)
        } else if (plays >= currState.board.size)
            state = GameViewState.Draw(mutableList)
        else {
            val nextPlayer =
                if (currState.currentPlayer == CellState.O) CellState.X else CellState.O
            state = GameViewState.Running(nextPlayer, mutableList)
        }


    }


    fun restart() = viewModelActionWithRetry {
        plays = 0
        state = GameViewState.Running(CellState.O, Cells.emptyBoard)
    }
}