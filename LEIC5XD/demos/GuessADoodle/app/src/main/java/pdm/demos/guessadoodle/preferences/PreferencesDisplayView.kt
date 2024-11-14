package pdm.demos.guessadoodle.preferences

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import pdm.demos.guessadoodle.domain.Nick
import pdm.demos.guessadoodle.ui.theme.GuessADoodleTheme
import pdm.demos.guessadoodle.R

const val DISPLAY_VIEW_TAG = "display"
const val NICK_TEXT_TAG = "nick"

const val OK_BUTTON_TAG = "ok"
const val CANCEL_BUTTON_TAG = "cancel"

@Composable
fun PreferencesDisplayView(
    state: PreferencesScreenState.Displaying,
    onEditIntent: () -> Unit,
    onCancelIntent: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize().testTag(DISPLAY_VIEW_TAG)) {
        TextField(
            value = state.nick.value,
            modifier = Modifier.testTag(NICK_TEXT_TAG),
            onValueChange = { }
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(modifier = Modifier.testTag(OK_BUTTON_TAG), enabled = false, onClick = { }) {
                Text(stringResource(R.string.preferences_ok_button))
            }
            Button(modifier = Modifier.testTag(CANCEL_BUTTON_TAG), onClick = onCancelIntent) { Text(stringResource(R.string.preferences_cancel_button)) }
        }
    }
}

@Preview
@Composable
fun PreferencesDisplayViewPreview() {
    GuessADoodleTheme {
        PreferencesDisplayView(
            state = PreferencesScreenState.Displaying(Nick("Palecas")),
            onEditIntent = { },
            onCancelIntent = { }
        )
    }
}