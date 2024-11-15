package pdm.demos.guessadoodle.preferences.views

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PreferencesLoadingViewTests {

    @get:Rule
    val composeTree = createComposeRule()

    @Test
    fun progressIndicator_is_shown() {
        composeTree.setContent {
            PreferencesLoadingView()
        }
        composeTree.onNodeWithTag(PROGRESS_INDICATOR_TAG).assertIsDisplayed()
    }
}