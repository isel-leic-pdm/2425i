package pdm.demos.guessadoodle.preferences.views

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
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
class PreferencesEditViewTests {

    @get:Rule
    val composeTree = createComposeRule()

    @Test
    fun userInfo_is_displayed() {
        val expected = UserInfo(nick = Nick("I'm"), tagline = "I'm a tagline")
        composeTree.setContent {
            PreferencesEditView(
                PreferencesScreenState.Editing(expected),
                onSaveIntent = { },
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
            PreferencesEditView(
                PreferencesScreenState.Editing(expected),
                onSaveIntent = { },
                onCancelIntent = { }
            )
        }

        composeTree.onNodeWithTag(OK_BUTTON_TAG).assertIsDisplayed().assertIsEnabled()
        composeTree.onNodeWithTag(CANCEL_BUTTON_TAG).assertIsDisplayed().assertIsEnabled()
    }

    @Test
    fun onCancelIntent_is_called_when_cancel_button_is_pressed() {
        var onCancelIntentCalled = false
        val expected = UserInfo(nick = Nick("I'm"), tagline = "I'm a tagline")
        composeTree.setContent {
            PreferencesEditView(
                PreferencesScreenState.Editing(expected),
                onSaveIntent = { },
                onCancelIntent = { onCancelIntentCalled = true }
            )
        }

        composeTree.onNodeWithTag(CANCEL_BUTTON_TAG).performClick()
        assert(onCancelIntentCalled)
    }

    @Test
    fun onSaveIntent_is_called_with_the_entered_userInfo() {
        var onSaveIntentParam: UserInfo? = null
        val initial = UserInfo(nick = Nick("I'm"), tagline = "I'm a tagline")
        val expected = UserInfo(nick = Nick("I'm a new nick"), tagline = "I'm a tagline")
        composeTree.setContent {
            PreferencesEditView(
                PreferencesScreenState.Editing(initial),
                onSaveIntent = { onSaveIntentParam = it },
                onCancelIntent = { }
            )
        }

        composeTree.onNodeWithTag(NICK_TEXT_TAG).performTextInput(expected.nick.value)
        composeTree.onNodeWithTag(OK_BUTTON_TAG).performClick()
        assert(onSaveIntentParam == expected)
    }
}