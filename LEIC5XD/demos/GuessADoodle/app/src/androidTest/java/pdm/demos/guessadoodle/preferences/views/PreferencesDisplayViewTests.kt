package pdm.demos.guessadoodle.preferences.views

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pdm.demos.guessadoodle.domain.Nick
import pdm.demos.guessadoodle.preferences.PreferencesScreenState

@RunWith(AndroidJUnit4::class)
class PreferencesDisplayViewTests {

    @get:Rule
    val composeTree = createComposeRule()

    @Test
    fun nick_is_displayed() {
        val expected = Nick("John")
        composeTree.setContent {
            PreferencesDisplayView(
                PreferencesScreenState.Displaying(expected),
                onEditIntent = { },
                onCancelIntent = { }
            )
        }

        composeTree
            .onNodeWithTag(NICK_TEXT_TAG)
            .assertIsDisplayed()
            .assertTextContains(expected.value)
    }

    @Test
    fun buttons_are_in_correct_state() {
        val expected = Nick("John")
        composeTree.setContent {
            PreferencesDisplayView(
                PreferencesScreenState.Displaying(expected),
                onEditIntent = { },
                onCancelIntent = { }
            )
        }

        composeTree.onNodeWithTag(OK_BUTTON_TAG).assertIsDisplayed().assertIsNotEnabled()
        composeTree.onNodeWithTag(CANCEL_BUTTON_TAG).assertIsDisplayed().assertIsEnabled()
    }

    @Test
    fun onCancelIntent_is_called_when_cancel_button_is_pressed() {
        var onCancelIntentCalled = false
        val expected = Nick("John")
        composeTree.setContent {
            PreferencesDisplayView(
                PreferencesScreenState.Displaying(expected),
                onEditIntent = { },
                onCancelIntent = { onCancelIntentCalled = true }
            )
        }

        composeTree.onNodeWithTag(CANCEL_BUTTON_TAG).performClick()
        assert(onCancelIntentCalled)
    }

    @Test
    fun onEditIntent_is_called_when_text_is_entered_on_the_nick_textfield() {
        var onEditIntentCalled = false
        val expected = Nick("John")
        composeTree.setContent {
            PreferencesDisplayView(
                PreferencesScreenState.Displaying(expected),
                onEditIntent = { onEditIntentCalled = true },
                onCancelIntent = { }
            )
        }

        composeTree.onNodeWithTag(NICK_TEXT_TAG).performTextInput("something")
        assert(onEditIntentCalled)
    }
}