package pdm.demos.guessadoodle.preferences.views

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.TextFieldValue
import org.junit.Rule
import org.junit.Test

class NickTextFieldTests {

    @get:Rule
    val composeTree = createComposeRule()

    @Test
    fun nick_is_displayed() {
        val expected = TextFieldValue("John")
        composeTree.setContent {
            NickTextField(nick = expected, onValueChange = { })
        }

        composeTree.onNodeWithTag(NICK_TEXT_TAG, useUnmergedTree = true).assertTextEquals(expected.text)
    }

    @Test
    fun onValueChange_is_called_when_text_is_changed() {
        var actual = TextFieldValue("")
        val expected = "John"
        composeTree.setContent {
            NickTextField(nick = actual, onValueChange = { actual = it })
        }

        composeTree.onNodeWithTag(NICK_TEXT_TAG).performTextInput(expected)
        assert(actual.text == expected)
    }

    @Test
    fun enabled_state_is_correct() {
        val enabled = true
        composeTree.setContent {
            NickTextField(nick = TextFieldValue(""), onValueChange = { }, enabled = enabled)
        }

        composeTree.onNodeWithTag(NICK_TEXT_TAG).assertIsEnabled()
    }

    @Test
    fun focus_state_is_correct() {
        val focused = true
        composeTree.setContent {
            NickTextField(nick = TextFieldValue(""), onValueChange = { }, requestFocus = focused)
        }

        composeTree.onNodeWithTag(NICK_TEXT_TAG).assertIsFocused()
    }
}