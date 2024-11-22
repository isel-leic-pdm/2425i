package pdm.demos.guessadoodle.preferences.views

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pdm.demos.guessadoodle.domain.Nick
import pdm.demos.guessadoodle.domain.UserInfo
import pdm.demos.guessadoodle.preferences.PreferencesScreenState

@RunWith(AndroidJUnit4::class)
class PreferencesSavingViewTests {

    @get:Rule
    val composeTree = createComposeRule()


    @Test
    fun all_ui_elements_are_disabled() {
        val state = PreferencesScreenState.Saving(UserInfo(nick = Nick("I'm"), tagline = "I'm a tagline"))
        composeTree.setContent {
            PreferencesSavingView(state)
        }

        composeTree.onNodeWithTag(OK_BUTTON_TAG).assertIsDisplayed().assertIsNotEnabled()
        composeTree.onNodeWithTag(CANCEL_BUTTON_TAG).assertIsDisplayed().assertIsNotEnabled()
        composeTree.onNodeWithTag(NICK_TEXT_TAG).assertIsDisplayed().assertIsNotEnabled()
        composeTree.onNodeWithTag(TAGLINE_TEXT_TAG).assertIsDisplayed().assertIsNotEnabled()
    }

    @Test
    fun userInfo_is_displayed() {
        val expected = UserInfo(nick = Nick("I'm"), tagline = "I'm a tagline")
        composeTree.setContent {
            PreferencesSavingView(
                PreferencesScreenState.Saving(expected)
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
}