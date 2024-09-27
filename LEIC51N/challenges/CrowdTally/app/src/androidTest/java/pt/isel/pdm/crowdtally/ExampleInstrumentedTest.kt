package pt.isel.pdm.crowdtally

import android.util.Log
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import pt.isel.pdm.crowdtally.Main.CrowdTally
import pt.isel.pdm.crowdtally.Main.CrowdTallyView
import pt.isel.pdm.crowdtally.Main.decrement
import pt.isel.pdm.crowdtally.Main.increment

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `CrowdTally Increment button is correctly callled`() {

        //arrange
        val state = CrowdTally.default
        var incrementIsCalled = false

        composeTestRule.setContent {
            CrowdTallyView(
                crowd = state.currentCrowd,
                onDecrementAvailable = false,
                onIncrementAvailable = true,
                onIncrementCurrent = {
                    incrementIsCalled = true
                },
                onDecrementCurrent = {
                }
            )
        }



        //act
        composeTestRule.onNodeWithTag(TestTags.CrowdTallyViewIncButton).performClick()

        //assert
        assertTrue(incrementIsCalled)




    }

}