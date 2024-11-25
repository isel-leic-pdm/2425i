package pdm.demos.guessadoodle.preferences

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith
import pdm.demos.guessadoodle.domain.Nick
import pdm.demos.guessadoodle.domain.UserInfo
import pdm.demos.guessadoodle.domain.UserInfoRepository
import pdm.demos.guessadoodle.preferences.PreferencesScreenState.Displaying
import pdm.demos.guessadoodle.preferences.PreferencesScreenState.Editing
import pdm.demos.guessadoodle.preferences.PreferencesScreenState.Initialized
import pdm.demos.guessadoodle.preferences.PreferencesScreenState.Loading
import pdm.demos.guessadoodle.preferences.PreferencesScreenState.Saving
import pdm.demos.guessadoodle.preferences.views.CANCEL_BUTTON_TAG
import pdm.demos.guessadoodle.preferences.views.DISPLAY_VIEW_TAG
import pdm.demos.guessadoodle.preferences.views.EDIT_VIEW_TAG
import pdm.demos.guessadoodle.preferences.views.LOADING_VIEW_TAG
import pdm.demos.guessadoodle.preferences.views.OK_BUTTON_TAG
import pdm.demos.guessadoodle.preferences.views.SAVING_VIEW_TAG
import pdm.demos.guessadoodle.preferences.views.TAGLINE_TEXT_TAG

@RunWith(AndroidJUnit4::class)
class PreferencesRootViewTests {

    @get:Rule
    val composeTree = createComposeRule()

    private fun createFakeViewModel(screenState: PreferencesScreenState) =
        PreferencesViewModel(
            userInfoRepository = object : UserInfoRepository {
                override val userInfo: Flow<UserInfo?>
                    get() = flowOf(null)
                override suspend fun getUserInfo(): UserInfo? = null
                override suspend fun updateUserInfo(userInfo: UserInfo) { }
                override suspend fun clearUserInfo() { }
            },
            initialState = screenState
        )

    @Test
    fun when_Initialized_the_Loading_view_is_shown() {
        composeTree.setContent {
            PreferencesRootView(
                viewModel = createFakeViewModel(screenState = Initialized),
                onBackIntent = { }
            )
        }
        composeTree.onNodeWithTag(LOADING_VIEW_TAG).assertExists()
    }

    @Test
    fun when_Loading_the_Loading_view_is_shown() {
        composeTree.setContent {
            PreferencesRootView(
                viewModel = createFakeViewModel(screenState = Loading),
                onBackIntent = { }
            )
        }
        composeTree.onNodeWithTag(LOADING_VIEW_TAG).assertExists()
    }

    @Test
    fun when_Displaying_the_Display_view_is_shown() {
        composeTree.setContent {
            PreferencesRootView(
                viewModel = createFakeViewModel(screenState = Displaying(UserInfo(Nick("John")))),
                onBackIntent = { }
            )
        }
        composeTree.onNodeWithTag(DISPLAY_VIEW_TAG).assertIsDisplayed()
    }

    @Test
    fun when_Displaying_cancel_button_calls_onBackIntent() {
        var backIntentCalled = false
        composeTree.setContent {
            PreferencesRootView(
                viewModel = createFakeViewModel(screenState = Displaying(UserInfo(Nick("John")))),
                onBackIntent = { backIntentCalled = true }
            )
        }
        composeTree.onNodeWithTag(DISPLAY_VIEW_TAG).assertIsDisplayed()
        composeTree.onNodeWithTag(CANCEL_BUTTON_TAG).performClick()
        assert(backIntentCalled) { "onBackIntent should've been called" }
    }

    @Test
    fun when_Displaying_entering_text_transitions_to_Editing_with_received_text() {
        val expectedTagline = "Tagline"
        composeTree.setContent {
            PreferencesRootView(
                viewModel = createFakeViewModel(screenState = Displaying(UserInfo(Nick("John")))),
                onBackIntent = { }
            )
        }
        composeTree.onNodeWithTag(DISPLAY_VIEW_TAG).assertIsDisplayed()
        composeTree.onNodeWithTag(TAGLINE_TEXT_TAG).performTextInput(expectedTagline)

        composeTree.onNodeWithTag(EDIT_VIEW_TAG).assertIsDisplayed()
        composeTree
            .onNodeWithTag(TAGLINE_TEXT_TAG, useUnmergedTree = true)
            .assertTextEquals(expectedTagline)
    }

    @Test
    fun when_Editing_the_Edit_view_is_shown() {
        composeTree.setContent {
            PreferencesRootView(
                viewModel = createFakeViewModel(
                    screenState = Editing(
                        prevState = Displaying(UserInfo(Nick("John"))),
                        nickText = "John",
                        taglineText = "Tagline"
                    )
                ),
                onBackIntent = { }
            )
        }
        composeTree.onNodeWithTag(EDIT_VIEW_TAG).assertIsDisplayed()
    }

    @Test
    fun when_Editing_cancel_button_transitions_to_Displaying_with_original_info() {
        val expectedNick = "John"
        val expectedTagline = "Tagline"
        val previousState = Displaying(UserInfo(Nick(expectedNick), expectedTagline))
        composeTree.setContent {
            PreferencesRootView(
                viewModel = createFakeViewModel(
                    screenState = Editing(
                        prevState = previousState,
                        nickText = "Modified Nick",
                        taglineText = "Modified Tagline"
                    )
                ),
                onBackIntent = { }
            )
        }
        composeTree.onNodeWithTag(EDIT_VIEW_TAG).assertIsDisplayed()
        composeTree.onNodeWithTag(CANCEL_BUTTON_TAG).performClick()

        composeTree.onNodeWithTag(DISPLAY_VIEW_TAG).assertIsDisplayed()
        composeTree
            .onNodeWithTag(TAGLINE_TEXT_TAG, useUnmergedTree = true)
            .assertTextEquals(expectedTagline)
    }

    @Test
    fun when_Saving_the_Saving_view_is_shown() {
        composeTree.setContent {
            PreferencesRootView(
                viewModel = createFakeViewModel(screenState = Saving(UserInfo(Nick("John")))),
                onBackIntent = { }
            )
        }
        composeTree.onNodeWithTag(SAVING_VIEW_TAG).assertIsDisplayed()
    }

    @Test
    fun when_Editing_save_button_calls_onBackIntent_once_save_completes() {
        var backIntentCalled = false
        composeTree.setContent {
            PreferencesRootView(
                viewModel = createFakeViewModel(
                    screenState = Editing(
                        prevState = Displaying(UserInfo(Nick("John"))),
                        nickText = "John",
                        taglineText = "Tagline"
                    )
                ),
                onBackIntent = { backIntentCalled = true }
            )
        }

        composeTree.onNodeWithTag(EDIT_VIEW_TAG).assertIsDisplayed()
        composeTree.onNodeWithTag(OK_BUTTON_TAG).performClick()
        composeTree.waitForIdle()

        assert(backIntentCalled) { "onBackIntent should have been called" }
    }
}