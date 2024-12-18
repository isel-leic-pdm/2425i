package pdm.demos.guessadoodle.preferences.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pdm.demos.guessadoodle.R
import pdm.demos.guessadoodle.domain.Nick
import pdm.demos.guessadoodle.domain.UserInfo
import pdm.demos.guessadoodle.domain.isValidNick
import pdm.demos.guessadoodle.preferences.PreferencesScreenState
import pdm.demos.guessadoodle.ui.theme.GuessADoodleTheme

const val EDIT_VIEW_TAG = "edit"

@Composable
fun PreferencesEditView(
    state: PreferencesScreenState.Editing,
    onSaveIntent: (UserInfo?) -> Unit,
    onCancelIntent: () -> Unit,
    modifier: Modifier = Modifier
) {

    var nick by rememberSaveable { mutableStateOf(state.prevState.userInfo?.nick?.value ?: "") }
    var tagline by rememberSaveable { mutableStateOf(state.prevState.userInfo?.tagline ?: "") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize().testTag(EDIT_VIEW_TAG)
    ) {
        NickTextField(
            nick = nick,
            requestFocus = state.selected == EditableField.Nick,
            onValueChange = { nick = it }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        TaglineTextField(
            tagline = tagline,
            requestFocus = state.selected == EditableField.Tagline,
            onValueChange = { tagline = it }
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
        ) {
            Button(
                modifier = Modifier.testTag(OK_BUTTON_TAG),
                enabled = nick.isValidNick(),
                onClick = { onSaveIntent(UserInfo(Nick(nick), tagline)) }
            ) {
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
     val previousState = PreferencesScreenState.Displaying(
        UserInfo(Nick("Palecas"), "Sem medos!")
    )
    GuessADoodleTheme {
        PreferencesEditView(
            state = PreferencesScreenState.Editing(prevState = previousState),
            onSaveIntent = { },
            onCancelIntent = { }
        )
    }
}