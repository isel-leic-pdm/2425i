package pdm.demos.diceroller

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DiceRollerScreenTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun dice_value_is_displayed() {
        val dice = roll()
        composeTestRule.setContent {
            DiceRollerScreen(dice)
        }

        composeTestRule.onNodeWithTag(dice.toTestTag()).assertExists()
    }
}