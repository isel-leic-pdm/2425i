package pdm.demos.jokeofday.domain

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import pdm.demos.jokeofday.TAG
import java.net.URL
import kotlin.random.Random

/**
 * Contract of the service that provides jokes.
 */
interface JokesService {
    /**
     * Fetches a joke.
     * @return The fetched joke.
     * @throws FetchJokeException If the joke could not be fetched.
     * @throws kotlinx.coroutines.CancellationException If the operation was cancelled.
     */
    suspend fun fetchJoke(): Joke

    /**
     * The flow of jokes fetched by the service, if one exists.
     */
    val joke: Flow<Joke?>
}

/**
 * Represents an error that occurred while fetching a joke.
 * @param message The error message
 * @param cause The cause of the error, if any
 */
class FetchJokeException(message: String, cause: Throwable? = null) : Exception(message, cause)

/**
 * Fake implementation of the JokesService. It returns a random joke from a pre-established list
 * of jokes.
 */
class FakeJokesService : JokesService {

    companion object {
        val jokes = listOf(
            Joke(
                text = "Chuck Norris didn't call the wrong number, you answered the wrong phone.",
                source = URL("https://www.keeplaughingforever.com/chuck-norris-jokes")
            ),
            Joke(
                text = "The dinosaurs once looked at Chuck Norris the wrong way" +
                        " and now we call them extinct.",
                source = URL("https://www.keeplaughingforever.com/chuck-norris-jokes"),
            ),
            Joke(
                text = "Somebody asked Chuck Norris how many press ups he could do, " +
                        "Chuck Norris replied \"all of them\".",
                source = URL("https://www.keeplaughingforever.com/chuck-norris-jokes"),
            ),
            Joke(
                text = "This graveyard looks overcrowded. People must be dying to get in there.",
                source = URL("https://www.keeplaughingforever.com/corny-dad-jokes")
            ),
            Joke(
                text = "Chuck Norris didn't call the wrong number, you answered the wrong phone.",
                source = URL("https://www.keeplaughingforever.com/chuck-norris-jokes")
            ),
            Joke(
                text = "The dinosaurs once looked at Chuck Norris the wrong way" +
                        " and now we call them extinct.",
                source = URL("https://www.keeplaughingforever.com/chuck-norris-jokes"),
            ),
            Joke(
                text = "Somebody asked Chuck Norris how many press ups he could do, " +
                        "Chuck Norris replied \"all of them\".",
                source = URL("https://www.keeplaughingforever.com/chuck-norris-jokes"),
            ),
            Joke(
                text = "This graveyard looks overcrowded. People must be dying to get in there.",
                source = URL("https://www.keeplaughingforever.com/corny-dad-jokes")
            ),
            Joke(
                text = "Chuck Norris didn't call the wrong number, you answered the wrong phone.",
                source = URL("https://www.keeplaughingforever.com/chuck-norris-jokes")
            ),
            Joke(
                text = "The dinosaurs once looked at Chuck Norris the wrong way" +
                        " and now we call them extinct.",
                source = URL("https://www.keeplaughingforever.com/chuck-norris-jokes"),
            ),
            Joke(
                text = "Somebody asked Chuck Norris how many press ups he could do, " +
                        "Chuck Norris replied \"all of them\".",
                source = URL("https://www.keeplaughingforever.com/chuck-norris-jokes"),
            ),
            Joke(
                text = "This graveyard looks overcrowded. People must be dying to get in there.",
                source = URL("https://www.keeplaughingforever.com/corny-dad-jokes")
            ),
        )
    }

    override suspend fun fetchJoke(): Joke {
        Log.v(TAG, "fetchJoke started")
        delay(10000)
        val index = Random.nextInt(from = 0, until = jokes.size)
        Log.v(TAG, "fetchJoke finishing")
        return jokes[index]
    }

    override val joke: Flow<Joke>
        get() = jokes.asFlow()

}
