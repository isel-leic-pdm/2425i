package pdm.demos.diceroller.main

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pdm.demos.diceroller.about.ABOUT_SCREEN_TEST_TAG

/**
 * Tests the [DiceRollerActivity] class.
 * https://developer.android.com/guide/components/activities/testing
 */
@RunWith(AndroidJUnit4::class)
class DiceRollerActivityTests {

    @get:Rule
    val testRule = createAndroidComposeRule<DiceRollerActivity>()

    @Test
    fun initially_the_idle_state_view_is_displayed() {
        testRule.onNodeWithTag(IDLE_VIEW_TAG).assertExists()
    }

    @Test
    fun when_the_info_button_is_clicked_the_about_activity_is_launched() {
        testRule
            .onNodeWithTag(INFO_BUTTON_TAG)
            .performClick()

        testRule.onNodeWithTag(ABOUT_SCREEN_TEST_TAG).assertExists()
    }
}
