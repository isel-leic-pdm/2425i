package pt.isel.pdm.tictactoe.ui.settings

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pt.isel.pdm.tictactoe.R

@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {

    val currentUserName = viewModel.userNameFlow.collectAsStateWithLifecycle()

    var currText by remember(currentUserName.value) { mutableStateOf(currentUserName.value) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = currText,
            onValueChange = {
                currText = it
            },
            singleLine = true,
        )

        Button(onClick = {
            viewModel.changeUserName(currText)
        }) {
            Text(stringResource(R.string.settings_save))
        }
    }


}

