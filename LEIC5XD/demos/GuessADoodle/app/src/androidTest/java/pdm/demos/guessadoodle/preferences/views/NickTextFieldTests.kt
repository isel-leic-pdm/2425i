package pdm.demos.guessadoodle.preferences.views

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class NickTextFieldTests {

    @get:Rule
    val composeTree = createComposeRule()

    @Test
    fun nick_is_displayed() {
        val expected = "John"
        composeTree.setContent {
            NickTextField(nick = expected)
        }

        composeTree.onNodeWithTag(NICK_TEXT_TAG, useUnmergedTree = true).assertTextEquals(expected)
    }

    @Test
    fun onValueChange_is_called_when_text_is_changed() {
        var actual = ""
        val expected = "John"
        composeTree.setContent {
            NickTextField(nick = actual, onValueChange = { actual = it })
        }

        composeTree.onNodeWithTag(NICK_TEXT_TAG).performTextInput(expected)
        assert(actual == expected)
    }

    @Test
    fun enabled_state_is_correct() {
        val enabled = true
        composeTree.setContent {
            NickTextField(nick = "", enabled = enabled)
        }

        composeTree.onNodeWithTag(NICK_TEXT_TAG).assertIsEnabled()
    }

    @Test
    fun onFocusReceived_is_called_when_clicked() {
        var focused = false
        composeTree.setContent {
            NickTextField(nick = "", onFocusReceived = { focused = true })
        }

        composeTree.onNodeWithTag(NICK_TEXT_TAG).assertIsNotFocused()
        composeTree.onNodeWithTag(NICK_TEXT_TAG).performClick()
        assert(focused)
    }
}