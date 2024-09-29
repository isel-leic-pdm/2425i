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
class CountingViewTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun counter_value_is_displayed_and_buttons_are_enabled_when_not_at_limits() {
        val counter = CrowdCounter(value = 4, capacity = 10)
        composeTestRule.setContent {
            CountingView(
                state = CrowdTallyScreenState.Counting(counter),
                onIncrementIntent = {},
                onDecrementIntent = {}
            )
        }

        composeTestRule.onNodeWithTag(COUNTER_VIEW_TAG).assertTextEquals(counter.value.toString())
        composeTestRule.onNodeWithTag(INCREMENT_BUTTON_TAG).assertIsEnabled()
        composeTestRule.onNodeWithTag(DECREMENT_BUTTON_TAG).assertIsEnabled()
    }

    @Test
    fun when_counter_is_at_minimum_buttons_state_is_correct() {
        composeTestRule.setContent {
            CountingView(
                state = CrowdTallyScreenState.Counting(CrowdCounter(value = 0, capacity = 10)),
                onIncrementIntent = {},
                onDecrementIntent = {}
            )
        }

        composeTestRule.onNodeWithTag(DECREMENT_BUTTON_TAG).assertIsNotEnabled()
        composeTestRule.onNodeWithTag(INCREMENT_BUTTON_TAG).assertIsEnabled()
    }

    @Test
    fun when_counter_is_at_maximum_buttons_state_is_correct() {
        composeTestRule.setContent {
            CountingView(
                state = CrowdTallyScreenState.Counting(CrowdCounter(value = 10, capacity = 10)),
                onIncrementIntent = {},
                onDecrementIntent = {}
            )
        }

        composeTestRule.onNodeWithTag(DECREMENT_BUTTON_TAG).assertIsEnabled()
        composeTestRule.onNodeWithTag(INCREMENT_BUTTON_TAG).assertIsNotEnabled()
    }

    @Test
    fun when_increment_button_is_clicked_onIncrementIntent_is_called() {
        var onIncrementIntentCalled = false
        composeTestRule.setContent {
            CountingView(
                state = CrowdTallyScreenState.Counting(CrowdCounter(value = 4, capacity = 10)),
                onIncrementIntent = { onIncrementIntentCalled = true },
                onDecrementIntent = {}
            )
        }

        composeTestRule.onNodeWithTag(INCREMENT_BUTTON_TAG).performClick()
        assert(onIncrementIntentCalled)
    }

    @Test
    fun when_decrement_button_is_clicked_onDecrementIntent_is_called() {
        var onDecrementIntentCalled = false
        composeTestRule.setContent {
            CountingView(
                state = CrowdTallyScreenState.Counting(CrowdCounter(value = 4, capacity = 10)),
                onIncrementIntent = {},
                onDecrementIntent = { onDecrementIntentCalled = true }
            )
        }

        composeTestRule.onNodeWithTag(DECREMENT_BUTTON_TAG).performClick()
        assert(onDecrementIntentCalled)
    }

    @Test
    fun when_configure_button_is_clicked_onConfigureIntent_is_called() {
        var onConfigureIntentCalled = false
        composeTestRule.setContent {
            CountingView(
                state = CrowdTallyScreenState.Counting(CrowdCounter(value = 4, capacity = 10)),
                onIncrementIntent = {},
                onDecrementIntent = {},
                onConfigureIntent = { onConfigureIntentCalled = true }
            )
        }

        composeTestRule.onNodeWithTag(CONFIGURE_BUTTON_TAG).performClick()
        assert(onConfigureIntentCalled)
    }
}