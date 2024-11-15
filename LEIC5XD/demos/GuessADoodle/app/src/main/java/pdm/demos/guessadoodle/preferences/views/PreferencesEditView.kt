package pdm.demos.guessadoodle.preferences.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pdm.demos.guessadoodle.R
import pdm.demos.guessadoodle.domain.MAX_NICK_SIZE
import pdm.demos.guessadoodle.domain.MIN_NICK_SIZE
import pdm.demos.guessadoodle.domain.Nick
import pdm.demos.guessadoodle.preferences.PreferencesScreenState
import pdm.demos.guessadoodle.ui.theme.GuessADoodleTheme

const val EDIT_VIEW_TAG = "edit"

@Composable
fun PreferencesEditView(
    state: PreferencesScreenState.Editing,
    onSaveIntent: (Nick) -> Unit,
    onCancelIntent: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize().testTag(EDIT_VIEW_TAG)
    ) {
        NickTextField(nick = state.nick.value, onValueChange = { })

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        ) {
            Button(modifier = Modifier.testTag(OK_BUTTON_TAG), onClick = { }) {
                Text(stringResource(R.string.preferences_ok_button))
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Button(modifier = Modifier.testTag(CANCEL_BUTTON_TAG), onClick = onCancelIntent) {
                Text(
                    stringResource(R.string.preferences_cancel_button)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreferencesEditViewPreview() {
    GuessADoodleTheme {
        PreferencesEditView(
            state = PreferencesScreenState.Editing(Nick("Palecas")),
            onSaveIntent = {},
            onCancelIntent = {}
        )
    }
}