package pdm.demos.guessadoodle.preferences

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import pdm.demos.guessadoodle.ui.theme.GuessADoodleTheme

const val SAVING_VIEW_TAG = "saving"

@Composable
fun PreferencesSavingView() {
    Column(
        modifier = Modifier.fillMaxSize().testTag(SAVING_VIEW_TAG),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
fun PreferencesSavingViewPreview() {
    GuessADoodleTheme {
        PreferencesSavingView()
    }
}