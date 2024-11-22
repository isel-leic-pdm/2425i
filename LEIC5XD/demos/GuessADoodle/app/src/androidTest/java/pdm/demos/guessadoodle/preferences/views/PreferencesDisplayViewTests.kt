package pdm.demos.guessadoodle.preferences.views

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pdm.demos.guessadoodle.domain.Nick
import pdm.demos.guessadoodle.domain.UserInfo
import pdm.demos.guessadoodle.preferences.PreferencesScreenState

@RunWith(AndroidJUnit4::class)
class PreferencesDisplayViewTests {

    @get:Rule
    val composeTree = createComposeRule()

    @Test
    fun userInfo_is_displayed() {
        val expected = UserInfo(nick = Nick("I'm"), tagline = "I'm a tagline")
        composeTree.setContent {
            PreferencesDisplayView(
                PreferencesScreenState.Displaying(expected),
                onEditIntent = { _, _ -> },
                onCancelIntent = { }
            )
        }

        composeTree
            .onNodeWithTag(NICK_TEXT_TAG, useUnmergedTree = true)
            .assertIsDisplayed()
            .assertTextEquals(expected.nick.value)

        composeTree
            .onNodeWithTag(TAGLINE_TEXT_TAG, useUnmergedTree = true)
            .assertIsDisplayed()
            .assertTextEquals(expected.tagline ?: "")
    }

    @Test
    fun buttons_are_in_correct_state() {
        val expected = UserInfo(nick = Nick("I'm"), tagline = "I'm a tagline")
        composeTree.setContent {
            PreferencesDisplayView(
                PreferencesScreenState.Displaying(expected),
                onEditIntent = { _, _ -> },
                onCancelIntent = { }
            )
        }

        composeTree.onNodeWithTag(OK_BUTTON_TAG).assertIsDisplayed().assertIsNotEnabled()
        composeTree.onNodeWithTag(CANCEL_BUTTON_TAG).assertIsDisplayed().assertIsEnabled()
    }

    @Test
    fun onCancelIntent_is_called_when_cancel_button_is_pressed() {
        var onCancelIntentCalled = false
        val expected = UserInfo(nick = Nick("I'm"), tagline = "I'm a tagline")
        composeTree.setContent {
            PreferencesDisplayView(
                PreferencesScreenState.Displaying(expected),
                onEditIntent = { _, _ -> },
                onCancelIntent = { onCancelIntentCalled = true }
            )
        }

        composeTree.onNodeWithTag(CANCEL_BUTTON_TAG).performClick()
        assert(onCancelIntentCalled)
    }

    @Test
    fun onEditIntent_is_called_when_text_is_entered_on_the_nick_text_field() {
        var onEditIntentParams: Pair<String, EditableField>? = null
        val expected = UserInfo(nick = Nick("I'm"), tagline = "I'm a tagline")
        composeTree.setContent {
            PreferencesDisplayView(
                PreferencesScreenState.Displaying(expected),
                onEditIntent = { text, field -> onEditIntentParams = Pair(text, field) },
                onCancelIntent = { }
            )
        }

        val enteredText = "something"
        composeTree.onNodeWithTag(NICK_TEXT_TAG).performTextInput(enteredText)
        assert(onEditIntentParams != null)
        assert(onEditIntentParams?.first == enteredText)
        assert(onEditIntentParams?.second == EditableField.Nick)
    }

    @Test
    fun onEditIntent_is_called_when_text_is_entered_on_the_tagline_text_field() {
        var onEditIntentParams: Pair<String, EditableField>? = null
        val expected = UserInfo(nick = Nick("I'm"), tagline = "I'm a tagline")
        composeTree.setContent {
            PreferencesDisplayView(
                PreferencesScreenState.Displaying(expected),
                onEditIntent = { enteredText, editableField -> onEditIntentParams = Pair(enteredText, editableField) },
                onCancelIntent = { }
            )
        }

        val enteredText = "something"
        composeTree.onNodeWithTag(TAGLINE_TEXT_TAG).performTextInput(enteredText)
        assert(onEditIntentParams != null)
        assert(onEditIntentParams?.first == enteredText)
        assert(onEditIntentParams?.second == EditableField.Tagline)
    }
}