package pdm.demos.jokeofday.main

import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pdm.demos.jokeofday.domain.Joke
import pdm.demos.jokeofday.ui.common.ERROR_ALERT_DISMISS_BUTTON_TAG
import pdm.demos.jokeofday.ui.common.ERROR_ALERT_TAG
import java.net.URL

@RunWith(AndroidJUnit4::class)
class JokeScreenViewsTests {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun idle_view_does_not_display_a_joke() {
        composeRule.setContent {
            IdleView(onFetchRequested = { })
        }

        composeRule.onNodeWithTag(JOKE_TEXT_TAG).assertDoesNotExist()
    }

    @Test
    fun idle_view_when_fab_is_clicked_calls_onFetchRequested() {
        var wasCalled = false
        composeRule.setContent {
            IdleView(onFetchRequested = { wasCalled = true })
        }

        composeRule.onNodeWithTag(REFRESH_FAB_TAG).performClick()
        assert(wasCalled)
    }

    @Test
    fun loading_view_contains_disabled_fab() {
        composeRule.setContent {
            LoadingView()
        }

        composeRule.onNodeWithTag(REFRESH_FAB_TAG).assertIsNotEnabled()
    }

    @Test
    fun success_view_displays_the_joke() {
        val joke = Joke("text", URL("http://example.com"))
        composeRule.setContent {
            SuccessView(joke = joke, onFetchRequested = { })
        }

        composeRule.onNodeWithTag(JOKE_TEXT_TAG).assertExists()
    }

    @Test
    fun success_view_when_fab_is_clicked_calls_onFetchRequested() {
        var wasCalled = false
        composeRule.setContent {
            SuccessView(
                joke = Joke("text", URL("http://example.com")),
                onFetchRequested = { wasCalled = true })
        }

        composeRule.onNodeWithTag(REFRESH_FAB_TAG).performClick()
        assert(wasCalled)
    }

    @Test
    fun error_view_displays_error_alert() {
        composeRule.setContent {
            ErrorView(onDismissRequested = { })
        }

        composeRule.onNodeWithTag(ERROR_ALERT_TAG).assertExists()
    }

    @Test
    fun error_view_when_button_is_clicked_calls_onDismiss() {
        var wasCalled = false
        composeRule.setContent {
            ErrorView(onDismissRequested = { wasCalled = true })
        }

        composeRule.onNodeWithTag(ERROR_ALERT_DISMISS_BUTTON_TAG).performClick()
        assert(wasCalled)
    }
}