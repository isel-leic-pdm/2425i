package pdm.demos.crowdtally

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CrowdTallyScreenTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun initially_the_screen_displays_the_counting_view() {
        composeTestRule.setContent {
            CrowdTallyScreen()
        }

        composeTestRule.onNodeWithTag(COUNTING_VIEW_TAG).assertExists()
    }

    @Test
    fun when_the_configure_button_is_clicked_the_screen_displays_the_configuration_view() {
        composeTestRule.setContent {
            CrowdTallyScreen()
        }

        composeTestRule.onNodeWithTag(CONFIGURE_BUTTON_TAG).performClick()
        composeTestRule.onNodeWithTag(CONFIGURE_VIEW_TAG).assertExists()
    }

    @Test
    fun when_the_save_button_is_clicked_the_screen_displays_the_counting_view() {
        composeTestRule.setContent {
            CrowdTallyScreen()
        }

        composeTestRule.onNodeWithTag(CONFIGURE_BUTTON_TAG).performClick()
        composeTestRule.onNodeWithTag(SAVE_BUTTON_TAG).performClick()
        composeTestRule.onNodeWithTag(COUNTING_VIEW_TAG).assertExists()

    }
}