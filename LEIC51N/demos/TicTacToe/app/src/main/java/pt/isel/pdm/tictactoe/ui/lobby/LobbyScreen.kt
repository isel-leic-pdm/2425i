package pt.isel.pdm.tictactoe.ui.lobby

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pt.isel.pdm.tictactoe.R
import pt.isel.pdm.tictactoe.domain.GameInfo
import pt.isel.pdm.tictactoe.domain.GameSession
import pt.isel.pdm.tictactoe.ui.ViewModelOperationState
import pt.isel.pdm.tictactoe.ui.components.AppScaffold
import pt.isel.pdm.tictactoe.ui.components.LoadingAndErrorComponent
import pt.isel.pdm.tictactoe.ui.components.NavigationHandlers


@Composable
fun LobbyScreen(
    viewModel: LobbyViewModel,
    navigateBack: () -> Unit,
    navigateToRemoteGame: (GameInfo) -> Unit,

) = AppScaffold(
    title = R.string.screens_lobby,
    navigationHandlers = NavigationHandlers(onBackHandler = navigateBack),
    floatingActionButton = {
        FloatingActionButton(
            onClick = { viewModel.createAndWaitInNewLobby() },
        ) {
            Icon(Icons.Filled.Add, stringResource(R.string.lobby_create_lobby))
        }
    }
) { paddingValues ->

    LaunchedEffect(null) {
        viewModel.gameSessionFlow.collect { game ->
            if (game != null)
                navigateToRemoteGame(game)
        }
    }

    Box()
    {
        val lobbies = viewModel.lobbies.collectAsStateWithLifecycle()

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(36.dp)
        )
        {
            items(lobbies.value) { lobby ->
                Button(
                    onClick = {
                        viewModel.joinLobby(lobby)
                    }) {
                    Text(text = lobby.displayName)
                }
            }
        }

        LoadingAndErrorComponent(viewModel)

        if (viewModel.viewOperationState is ViewModelOperationState.Loading)
            Button(
                onClick = { viewModel.cancelJoinLobby() },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(24.dp)
            ) {
                Text(stringResource(R.string.cancel))
            }
    }

}