package pt.isel.pdm.tictactoe.ui.settings

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import pt.isel.pdm.tictactoe.R
import pt.isel.pdm.tictactoe.ui.components.AppScaffold
import pt.isel.pdm.tictactoe.ui.components.NavigationHandlers


@Composable
fun test() = AppScaffold(
    title = R.string.screens_settings,
) {

}

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    navigateBack: () -> Unit
) = AppScaffold(
    title = R.string.screens_settings,
    navigationHandlers = NavigationHandlers(
        onBackHandler = navigateBack
    )
) { paddingValues ->

    val currentUserName = viewModel.userNameFlow.collectAsStateWithLifecycle()

    var currText by remember(currentUserName.value) { mutableStateOf(currentUserName.value) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = currText,
            onValueChange = {
                currText = it
            },
            singleLine = true,
        )

        Button(
            onClick = {
                viewModel.changeUserName(currText)
            }) {
            Text(stringResource(R.string.settings_save))
        }
    }
}



