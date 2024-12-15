package pdm.demos.guessadoodle.preferences.views

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import org.junit.Rule
import org.junit.Test

class TaglineTextFieldTests {

    @get:Rule
    val composeTree = createComposeRule()

    @Test
    fun tagline_is_displayed() {
        val expected = "A tagline"
        composeTree.setContent {
            TaglineTextField(tagline = expected, onValueChange = { })
        }

        composeTree.onNodeWithTag(TAGLINE_TEXT_TAG, useUnmergedTree = true).assertTextEquals(expected)
    }

    @Test
    fun onValueChange_is_called_when_text_is_changed() {
        var actual = ""
        val expected = "A tagline"
        composeTree.setContent {
            TaglineTextField(tagline = actual, onValueChange = { actual = it })
        }

        composeTree.onNodeWithTag(TAGLINE_TEXT_TAG).performTextReplacement(expected)
        assert(actual == expected)
    }

    @Test
    fun enabled_state_is_correct() {
        val enabled = true
        composeTree.setContent {
            TaglineTextField(tagline = "", enabled = enabled)
        }

        composeTree.onNodeWithTag(TAGLINE_TEXT_TAG).assertIsEnabled()
    }

    @Test
    fun onFocusReceived_is_called_when_clicked() {
        var focused = false
        composeTree.setContent {
            TaglineTextField(tagline = "", onFocusReceived = { focused = true })
        }

        composeTree.onNodeWithTag(TAGLINE_TEXT_TAG).assertIsNotFocused()
        composeTree.onNodeWithTag(TAGLINE_TEXT_TAG).performClick()
        assert(focused)
    }
}