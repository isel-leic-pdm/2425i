package pdm.demos.guessadoodle.preferences

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith
import pdm.demos.guessadoodle.domain.Nick
import pdm.demos.guessadoodle.preferences.views.DISPLAY_VIEW_TAG
import pdm.demos.guessadoodle.preferences.views.EDIT_VIEW_TAG
import pdm.demos.guessadoodle.preferences.views.LOADING_VIEW_TAG
import pdm.demos.guessadoodle.preferences.views.SAVING_VIEW_TAG

@RunWith(AndroidJUnit4::class)
class PreferencesRootViewTests {

    @get:Rule
    val composeTree = createComposeRule()

    @Test
    fun when_Loading_the_Loading_view_is_shown() {
        composeTree.setContent {
            PreferencesRootView(PreferencesScreenState.Loading)
        }
        composeTree.onNodeWithTag(LOADING_VIEW_TAG).assertExists()
    }

    @Test
    fun when_Displaying_the_Display_view_is_shown() {
        composeTree.setContent {
            PreferencesRootView(PreferencesScreenState.Displaying(Nick("John")))
        }
        composeTree.onNodeWithTag(DISPLAY_VIEW_TAG).assertIsDisplayed()
    }

    @Test
    fun when_Editing_the_Edit_view_is_shown() {
        composeTree.setContent {
            PreferencesRootView(PreferencesScreenState.Editing(Nick("John")))
        }
        composeTree.onNodeWithTag(EDIT_VIEW_TAG).assertIsDisplayed()
    }

    @Test
    fun when_Saving_the_Saving_view_is_shown() {
        composeTree.setContent {
            PreferencesRootView(PreferencesScreenState.Saving)
        }
        composeTree.onNodeWithTag(SAVING_VIEW_TAG).assertIsDisplayed()
    }
}