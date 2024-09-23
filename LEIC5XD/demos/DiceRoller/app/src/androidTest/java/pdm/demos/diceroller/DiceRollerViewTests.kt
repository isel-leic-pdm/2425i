package pdm.demos.diceroller

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DiceRollerViewTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun idle_view_correctly_displays_the_screen_state() {
        val dice = roll()
        composeTestRule.setContent {
            DiceRollerIdleView(
                state = DiceRollerScreenState.Idle(dice),
                onDiceRollIntent = {}
            )
        }

        composeTestRule.onNodeWithTag(testTag = dice.toTestTag()).assertExists()
        composeTestRule.onNodeWithTag(testTag = ROLL_IT_BUTTON_TAG).assertIsEnabled()
    }

    @Test
    fun idle_view_calls_the_on_dice_roll_intent_when_the_button_is_clicked() {
        var called = false
        composeTestRule.setContent {
            DiceRollerIdleView(
                state = DiceRollerScreenState.Idle(Dice()),
                onDiceRollIntent = { called = true }
            )
        }

        composeTestRule.onNodeWithTag(testTag = ROLL_IT_BUTTON_TAG).performClick()
        assertTrue("onDiceRollIntent was not called", called)
    }

    @Test
    fun rolling_view_correctly_displays_the_screen_state() {
        composeTestRule.setContent {
            DiceRollerRollingView()
        }

        composeTestRule.onNodeWithTag(testTag = PROGRESS_INDICATOR_TAG).assertExists()
        composeTestRule.onNodeWithTag(testTag = ROLL_IT_BUTTON_TAG).assertIsNotEnabled()
    }
}