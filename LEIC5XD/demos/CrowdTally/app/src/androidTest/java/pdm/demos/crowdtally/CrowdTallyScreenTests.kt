package pdm.demos.crowdtally

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CrowdTallyScreenTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun counter_value_is_displayed() {
        val counter = CrowdCounter(value = 4, capacity = 10)
        composeTestRule.setContent {
            CrowdTallyScreen(initialCounter = counter)
        }

        composeTestRule.onNodeWithTag(COUNTER_VIEW_TAG).assertTextEquals(counter.value.toString())
    }

    @Test
    fun increment_button_increments_value() {
        val counter = CrowdCounter(value = 4, capacity = 10)
        composeTestRule.setContent {
            CrowdTallyScreen(initialCounter = counter)
        }
        val expectedDisplayedCounter = counter.inc().value.toString()

        composeTestRule.onNodeWithTag(INCREMENT_BUTTON_TAG).performClick()
        composeTestRule.onNodeWithTag(COUNTER_VIEW_TAG).assertTextEquals(expectedDisplayedCounter)
    }

    @Test
    fun decrement_button_decrements_value() {
        val counter = CrowdCounter(value = 4, capacity = 10)
        composeTestRule.setContent {
            CrowdTallyScreen(initialCounter = counter)
        }
        val expectedDisplayedCounter = counter.dec().value.toString()

        composeTestRule.onNodeWithTag(DECREMENT_BUTTON_TAG).performClick()
        composeTestRule.onNodeWithTag(COUNTER_VIEW_TAG).assertTextEquals(expectedDisplayedCounter)
    }

    @Test
    fun when_at_maximum_increment_button_is_disabled_and_decrement_button_is_enabled() {
        composeTestRule.setContent {
            CrowdTallyScreen(initialCounter = CrowdCounter(value = 10, capacity = 10))
        }
        composeTestRule.onNodeWithTag(INCREMENT_BUTTON_TAG).assertIsNotEnabled()
        composeTestRule.onNodeWithTag(DECREMENT_BUTTON_TAG).assertIsEnabled()
    }

    @Test
    fun when_at_minimum_decrement_button_is_disabled_and_increment_button_is_enabled() {
        composeTestRule.setContent {
            CrowdTallyScreen(initialCounter = CrowdCounter(value = 0, capacity = 10))
        }
        composeTestRule.onNodeWithTag(DECREMENT_BUTTON_TAG).assertIsNotEnabled()
    }
}