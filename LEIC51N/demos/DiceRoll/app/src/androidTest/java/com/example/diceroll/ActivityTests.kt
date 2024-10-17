package com.example.diceroll

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.diceroll.rules.TestDiceRollService
import com.example.diceroll.screens.TestTags
import com.example.diceroll.screens.main.MainActivity
import kotlinx.coroutines.withTimeout
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityTests {
    @get:Rule
    var activityRule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `activity pushing roll button show dice result`() {

        activityRule.onNodeWithTag(TestTags.LoadingWheel)
            .assertDoesNotExist()

        activityRule.onNodeWithTag(TestTags.DiceImage)
            .assertDoesNotExist()

        //act
        activityRule.onNodeWithTag(TestTags.RollButton)
            .assertExists()
            .performClick()



        activityRule.waitUntilExactlyOneExists(
            hasTestTag(TestTags.DiceImage),
            timeoutMillis = 6000
        )
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun `activity pushing roll button forces loading wheel to appear`() {


        val service =
            (activityRule.activity.application as DependencyContainer).rollService as TestDiceRollService


        service.delayTime = 1000

        activityRule.onNodeWithTag(TestTags.LoadingWheel)
            .assertDoesNotExist()
        //act
        activityRule.onNodeWithTag(TestTags.RollButton)
            .assertExists()
            .performClick()
        activityRule.waitUntilExactlyOneExists(
            hasTestTag(TestTags.LoadingWheel),
            timeoutMillis = 6000
        )
    }
}
