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

const val LOADING_VIEW_TAG = "loading"
const val PROGRESS_INDICATOR_TAG = "progress_indicator"

@Composable
fun PreferencesLoadingView() {
    Column(
        modifier = Modifier.fillMaxSize().testTag(LOADING_VIEW_TAG),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(modifier = Modifier.testTag(PROGRESS_INDICATOR_TAG))
    }
}

@Preview
@Composable
fun PreferencesLoadingViewPreview() {
    GuessADoodleTheme {
        PreferencesLoadingView()
    }
}