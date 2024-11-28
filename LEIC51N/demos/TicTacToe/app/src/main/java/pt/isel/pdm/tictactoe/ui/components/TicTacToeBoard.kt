package pt.isel.pdm.tictactoe.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import pt.isel.pdm.tictactoe.domain.Cell

@Composable
fun TicTacToeBoard(
    cells: List<Cell>,
    cellClicked: (Cell) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier)
    {
        Column {
            for (i in 0 until 3) {
                Row() {
                    for (j in 0 until 3) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                        )
                        {
                            TicTacToeCell(
                                cell = cells[i * 3 + j],
                                clicked = cellClicked
                            )
                        }

                    }

                }

            }

        }
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .aspectRatio(1f),
            verticalArrangement = Arrangement.Center
        ) {

            Divider(
                color = MaterialTheme.colorScheme.onSurface,
                thickness = 1.dp,
                modifier = Modifier.weight(1f, false)
            )
            Spacer(
                Modifier
                    .weight(1f)
            )

            Divider(
                color = MaterialTheme.colorScheme.onSurface,
                thickness = 1.dp,
                modifier = Modifier.weight(1f, false)
            )


        }
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .aspectRatio(1f)
                .rotate(90f),
            verticalArrangement = Arrangement.Center


        ) {

            Divider(
                color = MaterialTheme.colorScheme.onSurface,
                thickness = 1.dp,
                modifier = Modifier.weight(1f, false)
            )
            Spacer(
                Modifier
                    .weight(1f)
            )

            Divider(
                color = MaterialTheme.colorScheme.onSurface,
                thickness = 1.dp,
                modifier = Modifier.weight(1f, false)
            )


        }

    }

}