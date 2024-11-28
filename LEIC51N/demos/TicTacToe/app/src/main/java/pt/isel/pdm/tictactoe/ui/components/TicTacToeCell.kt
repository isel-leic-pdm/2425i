package pt.isel.pdm.tictactoe.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import pt.isel.pdm.tictactoe.domain.Cell
import pt.isel.pdm.tictactoe.domain.CellState

@Composable
fun TicTacToeCell(
    cell: Cell,
    clicked: (Cell) -> Unit,
    modifier: Modifier = Modifier
) {

    var modifier = modifier.fillMaxSize();

    if (cell.state == CellState.EMPTY)
        modifier = modifier.then(Modifier.clickable {
            clicked(cell)
        })
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        val value: String =
            when (cell.state) {
                CellState.O -> "O"
                CellState.X -> "X"
                else -> ""
            }
        Text(
            text = value,
            overflow = TextOverflow.Ellipsis,
            fontSize = 100.sp,
            color = if (cell.state == CellState.O) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
        )

    }
}

@Composable
@Preview
fun TicTacToeCellO() {
    TicTacToeCell(cell = Cell(0,0, CellState.O), {})
}

@Composable
@Preview
fun TicTacToeCellX() {
    TicTacToeCell(cell = Cell(0,0, CellState.X), {})
}