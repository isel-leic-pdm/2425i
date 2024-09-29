package pdm.demos.crowdtally

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConfigurationViewTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun initial_capacity_is_correctly_displayed() {
        val initialCapacity = 1000
        composeTestRule.setContent {
            ConfigurationView(
                CrowdTallyScreenState.Configuration(initialCapacity = 1000),
                onSaveIntent = {}
            )
        }

        composeTestRule.onNodeWithTag(CAPACITY_TEXT_FIELD_TAG)
            .assertTextEquals(initialCapacity.toString())
    }

    @Test
    fun when_save_button_is_clicked_onSaveIntent_is_called_with_new_capacity() {
        var newCapacity = 0
        val enteredCapacity = 234
        composeTestRule.setContent {
            ConfigurationView(
                CrowdTallyScreenState.Configuration(initialCapacity = 1000),
                onSaveIntent = { newCapacity = it }
            )
        }

        composeTestRule.onNodeWithTag(CAPACITY_TEXT_FIELD_TAG)
            .performTextReplacement(enteredCapacity.toString())
        composeTestRule.onNodeWithTag(SAVE_BUTTON_TAG).performClick()
        assertEquals(enteredCapacity, newCapacity)
    }

    @Test
    fun when_cancel_button_is_clicked_onSaveIntent_is_called_with_initial_capacity() {
        var newCapacity = 0
        val enteredCapacity = 234
        val initialCapacity = 1000
        composeTestRule.setContent {
            ConfigurationView(
                CrowdTallyScreenState.Configuration(initialCapacity = 1000),
                onSaveIntent = { newCapacity = it })
        }

        composeTestRule.onNodeWithTag(CAPACITY_TEXT_FIELD_TAG)
            .performTextReplacement(enteredCapacity.toString())
        composeTestRule.onNodeWithTag(CANCEL_BUTTON_TAG).performClick()
        assertEquals(initialCapacity, newCapacity)
    }
}