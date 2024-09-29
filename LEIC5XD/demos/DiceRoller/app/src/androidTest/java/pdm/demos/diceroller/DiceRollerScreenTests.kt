package pdm.demos.diceroller

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DiceRollerScreenTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun initially_the_screen_is_in_the_idle_state() {
        composeTestRule.setContent {
            DiceRollerScreen()
        }

        composeTestRule.onNodeWithTag(IDLE_VIEW_TAG).assertExists()
        composeTestRule.onNodeWithTag(ROLLING_VIEW_TAG).assertDoesNotExist()
    }

    @Test
    fun when_the_roll_button_is_clicked_the_screen_changes_to_the_rolling_state() {
        composeTestRule.setContent {
            DiceRollerScreen()
        }

        composeTestRule.onNodeWithTag(ROLL_IT_BUTTON_TAG).performClick()
        composeTestRule.onNodeWithTag(ROLLING_VIEW_TAG).assertExists()
        composeTestRule.onNodeWithTag(IDLE_VIEW_TAG).assertDoesNotExist()
    }

    @Test
    fun when_the_rolling_time_ends_the_screen_changes_back_to_the_idle_state() {
        composeTestRule.mainClock.autoAdvance = false
        composeTestRule.setContent {
            DiceRollerScreen()
        }

        composeTestRule.onNodeWithTag(ROLL_IT_BUTTON_TAG).performClick()
        composeTestRule.mainClock.advanceTimeBy(milliseconds = ROLLING_TIME_MS + 1000)
        composeTestRule.onNodeWithTag(IDLE_VIEW_TAG).assertExists()
    }
}