package pt.isel.pdm.tictactoe.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.isel.pdm.tictactoe.R
import pt.isel.pdm.tictactoe.domain.CellState
import pt.isel.pdm.tictactoe.domain.ShouldNotHappenException
import pt.isel.pdm.tictactoe.ui.components.AppScaffold
import pt.isel.pdm.tictactoe.ui.components.TicTacToeBoard
import pt.isel.pdm.tictactoe.ui.components.preventBehindElementsFromBeingClicked

@Composable
fun GameScreen(viewModel: GameViewModel) {

    AppScaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
        {
            TicTacToeBoard(
                cells = viewModel.state.board,
                cellClicked = viewModel::play,
                modifier = Modifier
                    .padding(paddingValues)
                    .align(Alignment.Center)
            )

            when (val state = viewModel.state) {
                is GameViewState.Running -> CurrentPlayerView(
                    state.currentPlayer,
                    Modifier.align(Alignment.BottomCenter)
                )

                else -> {
                    Box {
                        GameEndStateView(
                            viewModel.state,
                            Modifier
                                .fillMaxSize()
                                .align(Alignment.Center)
                        )

                        Button(
                            onClick = viewModel::restart,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(24.dp)

                        ) {
                            Text(stringResource(R.string.game_retry))
                        }
                    }
                }
            }


        }

    }


}

@Composable
fun CurrentPlayerView(
    currentPlayer: CellState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.game_current_player))
        Text(text = currentPlayer.toString(), fontSize = 120.sp)
    }
}

@Composable
fun GameEndStateView(
    state: GameViewState,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White.copy(alpha = .8f))
            .preventBehindElementsFromBeingClicked(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(stringResource(R.string.game_end))

        when (state) {
            is GameViewState.Draw -> Text(
                text = stringResource(R.string.game_draw),
                fontSize = 130.sp
            )

            is GameViewState.Complete -> {
                Text(
                    text = state.winner.toString(),
                    fontSize = 130.sp
                )
                Text(stringResource(R.string.game_won))
            }

            else -> {
                throw ShouldNotHappenException("GameEndStateView")
            }
        }
    }

}
