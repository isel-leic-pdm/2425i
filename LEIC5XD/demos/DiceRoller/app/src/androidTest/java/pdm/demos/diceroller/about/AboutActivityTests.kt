package pdm.demos.diceroller.about

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tests the [AboutActivity] class.
 * https://developer.android.com/guide/components/activities/testing
 */
@RunWith(AndroidJUnit4::class)
class AboutActivityTests {

    @get:Rule
    val testRule = createAndroidComposeRule<AboutActivity>()

    @Test
    fun initially_the_screen_is_displayed() {
        testRule.onNodeWithTag(ABOUT_SCREEN_TEST_TAG).assertExists()
    }

    @Test
    fun when_the_back_button_is_clicked_the_activity_is_finished() {
        // Arrange
        testRule.waitUntil { testRule.activityRule.scenario.state.isAtLeast(Lifecycle.State.STARTED) }
        // Act
        testRule.onNodeWithTag(NAVIGATE_BACK_TEST_TAG).performClick()
        // Assert
        testRule.waitUntil { testRule.activityRule.scenario.state == Lifecycle.State.DESTROYED }
    }
}