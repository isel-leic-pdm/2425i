package pdm.demos.guessadoodle.preferences

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import pdm.demos.guessadoodle.preferences.views.CANCEL_BUTTON_TAG
import pdm.demos.guessadoodle.preferences.views.DISPLAY_VIEW_TAG

class PreferencesActivityTests {

    @get:Rule
    val testRule = createAndroidComposeRule<PreferencesActivity>()

    @Test
    fun when_activity_is_launched_and_data_is_loaded_the_display_view_is_shown() {
        // We expect the data to be loaded automatically as soon as the activity is started
        testRule.onNodeWithTag(DISPLAY_VIEW_TAG).assertExists()
    }

    @Test
    fun pressing_cancel_button_in_display_view_should_exit_activity() {
        testRule.onNodeWithTag(CANCEL_BUTTON_TAG).performClick()
        assert(testRule.activity.isFinishing || testRule.activity.isDestroyed) {
            "Activity should be finishing or destroyed after pressing the cancel button"
        }
    }
}