package pt.isel.pdm.tictactoe.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pt.isel.pdm.tictactoe.R
import pt.isel.pdm.tictactoe.ui.components.AppScaffold

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    navigateToOfflineGame: () -> Unit,
    navigateToOnLineGame: () -> Unit,
    navigateToSettings: () -> Unit,
) = AppScaffold { innerPadding ->

    val userName = viewModel.userName.collectAsStateWithLifecycle();

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if(userName.value.isNotBlank())
        {
            Button(onClick = navigateToOnLineGame) {
                Text(stringResource(R.string.main_play_online))
            }


        }
        else{
            Text(stringResource(R.string.main_setup_username))
        }

        Button(onClick = navigateToOfflineGame) {
            Text(stringResource(R.string.main_play_offline))
        }

        Button(onClick = navigateToSettings) {
            Text(stringResource(R.string.screens_settings))
        }

    }
}