package pt.isel.pdm.tictactoe.ui.remotegame

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pt.isel.pdm.tictactoe.R
import pt.isel.pdm.tictactoe.ui.components.AppScaffold
import pt.isel.pdm.tictactoe.ui.components.LoadingMessageComponent
import pt.isel.pdm.tictactoe.ui.components.TicTacToeBoard
import pt.isel.pdm.tictactoe.ui.components.preventBehindElementsFromBeingClicked
import pt.isel.pdm.tictactoe.ui.game.CurrentPlayerView
import pt.isel.pdm.tictactoe.ui.game.GameEndStateView
import pt.isel.pdm.tictactoe.ui.game.GameViewState

@Composable
fun RemoteGameScreen(
    viewModel: RemoteGameViewModel,
    goBackAction: () -> Unit
) {

    val state = viewModel.stateFlow.collectAsStateWithLifecycle().value
    AppScaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),

            )
        {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "VS ${state.remoteGame.challenger}",
                    fontSize = 20.sp
                )

                TicTacToeBoard(
                    cells = state.remoteGame.board,
                    cellClicked = viewModel::play,
                    modifier = Modifier
                        .padding(paddingValues)
                )
            }

            when (state) {
                is RemoteGameViewState.Starting -> {
                    LoadingMessageComponent(R.string.remote_game_starting)
                }

                is RemoteGameViewState.MyTurn -> {}
                is RemoteGameViewState.RemotePlayerTurn -> {
                    LoadingMessageComponent(R.string.remote_game_waitingForOtherPlayer)
                    Button(
                        onClick = viewModel::forfeit,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(36.dp),
                    ) {
                        Text(text = stringResource(R.string.remote_game_forfeit))
                    }
                }

                else -> {
                    var msg = R.string.game_draw

                    if (state is RemoteGameViewState.Lose)
                        msg = R.string.game_loss
                    else if (state is RemoteGameViewState.Win)
                        msg = R.string.game_won

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background.copy(alpha = .8f))
                            .preventBehindElementsFromBeingClicked()

                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = stringResource(msg),
                            fontSize = 130.sp
                        )

                        Button(
                            onClick = goBackAction,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(36.dp),
                        ) {
                            Text(text = stringResource(R.string.top_bar_go_back))
                        }


                    }

                }

            }


        }

    }

}