package pdm.demos.guessadoodle.preferences

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import pdm.demos.guessadoodle.domain.Nick
import pdm.demos.guessadoodle.ui.theme.GuessADoodleTheme

const val EDIT_VIEW_TAG = "edit"

@Composable
fun PreferencesEditView(
    state: PreferencesScreenState.Editing,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize().testTag(EDIT_VIEW_TAG)) {
    }
}

@Preview
@Composable
fun PreferencesEditViewPreview() {
    GuessADoodleTheme {
        PreferencesEditView(PreferencesScreenState.Editing(Nick("Palecas")))
    }
}