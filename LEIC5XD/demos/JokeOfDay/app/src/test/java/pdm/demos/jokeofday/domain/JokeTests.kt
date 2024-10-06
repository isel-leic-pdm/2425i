package pdm.demos.jokeofday.domain

import org.junit.Test
import java.net.URL

/**
 * Tests for the domain's [Joke] class
 */
class JokeTests {
    @Test
    fun create_joke_with_valid_arguments_succeeds() {
        Joke("This is a joke", URL("https://www.example.com"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun create_joke_with_blank_text_fails() {
        Joke("", URL("https://www.example.com"))
    }
}