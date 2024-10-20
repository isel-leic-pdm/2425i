package pdm.demos.stopwatch

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pdm.demos.stopwatch.domain.StopWatch
import pdm.demos.stopwatch.main.StopWatchScreenState
import pdm.demos.stopwatch.main.views.DeciSecondsTestTag
import pdm.demos.stopwatch.main.views.MinutesTestTag
import pdm.demos.stopwatch.main.views.ResetButtonTag
import pdm.demos.stopwatch.main.views.SecondsTestTag
import pdm.demos.stopwatch.main.views.StartResumeButtonTag
import pdm.demos.stopwatch.main.views.StopWatchRunningView
import pdm.demos.stopwatch.main.views.StopWatchStoppedView
import pdm.demos.stopwatch.main.views.StopWatchZeroView

@RunWith(AndroidJUnit4::class)
class StopWatchViewsTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun zero_view_displays_a_zeroed_stopwatch() {
        composeTestRule.setContent {
            StopWatchZeroView(onStartIntent = { })
        }

        composeTestRule.onNodeWithTag(MinutesTestTag).assertTextContains("00", substring = true)
        composeTestRule.onNodeWithTag(SecondsTestTag).assertTextContains("00", substring = true)
        composeTestRule.onNodeWithTag(DeciSecondsTestTag).assertTextContains("00", substring = true)
    }

    @Test
    fun zero_view_has_disabled_reset_button() {
        composeTestRule.setContent {
            StopWatchZeroView(onStartIntent = { })
        }

        composeTestRule
            .onNodeWithTag(ResetButtonTag)
            .assertIsNotEnabled()
    }

    @Test
    fun zero_view_click_on_start_button_calls_onStartIntent() {
        var onStartIntentCalled = false
        composeTestRule.setContent {
            StopWatchZeroView(onStartIntent = { onStartIntentCalled = true })
        }

        composeTestRule
            .onNodeWithTag(StartResumeButtonTag)
            .performClick()

        assert(onStartIntentCalled) { "onStartIntent was not called" }
    }

    @Test
    fun running_view_has_a_disabled_reset() {
        composeTestRule.setContent {
            StopWatchRunningView(
                state = StopWatchScreenState.Running(StopWatch.start()),
                onStopIntent = { },
            )
        }

        composeTestRule
            .onNodeWithTag(ResetButtonTag)
            .assertIsNotEnabled()
    }

    @Test
    fun running_view_click_on_stop_button_calls_onStopIntent() {
        var onStopIntentCalled = false
        composeTestRule.setContent {
            StopWatchRunningView(
                state = StopWatchScreenState.Running(StopWatch.start()),
                onStopIntent = { onStopIntentCalled = true },
            )
        }

        composeTestRule
            .onNodeWithTag(StartResumeButtonTag)
            .performClick()

        assert(onStopIntentCalled) { "onStopIntent was not called" }
    }

    @Test
    fun stopped_view_has_enabled_reset_and_resume_buttons_and_displays_the_correct_value() {
        val stopWatch = StopWatch(start = 0, end = 60 * 1000L + 1000L)
        composeTestRule.setContent {
            StopWatchStoppedView(
                state = StopWatchScreenState.Stopped(stopWatch),
                onResetIntent = { },
                onResumeIntent = { },
            )
        }

        composeTestRule.onNodeWithTag(ResetButtonTag).assertIsEnabled()
        composeTestRule.onNodeWithTag(StartResumeButtonTag).assertIsEnabled()
        composeTestRule.onNodeWithTag(MinutesTestTag).assertTextContains("01", substring = true)
        composeTestRule.onNodeWithTag(SecondsTestTag).assertTextContains("01", substring = true)
        composeTestRule.onNodeWithTag(DeciSecondsTestTag).assertTextContains("00", substring = true)
    }

    @Test
    fun stopped_view_click_on_reset_button_calls_onResetIntent() {
        var onResetIntentCalled = false
        composeTestRule.setContent {
            StopWatchStoppedView(
                state = StopWatchScreenState.Stopped(StopWatch.start().stop()),
                onResetIntent = { onResetIntentCalled = true },
                onResumeIntent = { },
            )
        }

        composeTestRule
            .onNodeWithTag(ResetButtonTag)
            .performClick()

        assert(onResetIntentCalled) { "onResetIntent was not called" }
    }

    @Test
    fun stopped_view_click_on_resume_button_calls_onResumeIntent() {
        var onResumeIntentCalled = false
        composeTestRule.setContent {
            StopWatchStoppedView(
                state = StopWatchScreenState.Stopped(StopWatch.start().stop()),
                onResetIntent = { },
                onResumeIntent = { onResumeIntentCalled = true },
            )
        }

        composeTestRule
            .onNodeWithTag(StartResumeButtonTag)
            .performClick()

        assert(onResumeIntentCalled) { "onResumeIntent was not called" }
    }
}