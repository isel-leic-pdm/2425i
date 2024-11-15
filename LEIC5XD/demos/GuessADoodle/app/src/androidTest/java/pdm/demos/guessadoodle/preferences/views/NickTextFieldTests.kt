package pdm.demos.guessadoodle.preferences.views

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
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
            NickTextField(nick = expected, onValueChange = { })
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
}