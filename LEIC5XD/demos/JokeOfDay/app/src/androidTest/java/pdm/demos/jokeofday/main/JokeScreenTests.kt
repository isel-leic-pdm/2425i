package pdm.demos.jokeofday.main

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class JokeScreenTests {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun idle_view_is_displayed_initially() = fail("Not implemented")

    @Test
    fun loading_view_is_displayed_while_fetching_joke() = fail("Not implemented")

    @Test
    fun success_view_is_displayed_when_fetching_joke_succeeds() = fail("Not implemented")

    @Test
    fun error_view_is_displayed_when_fetching_joke_fails() = fail("Not implemented")

}