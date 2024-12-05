package pdm.demos.jokeofday.http

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import pdm.demos.jokeofday.domain.Joke
import pdm.demos.jokeofday.domain.JokesService
import java.net.URL

/**
 * Implementation of the JokesService that fetches jokes from a remote server using HTTP.
 * @param client The HTTP client to use for fetching jokes.
 */
class IcanhazDadJokes(private val client: HttpClient) : JokesService {
    private val url = "https://icanhazdadjoke.com/"
    private val source = URL(url)

    override suspend fun fetchJoke(): Joke =
        client.get(url) { header("accept", "application/json") }
            .body<JokeDto>()
            .toJoke(source)

    private val _joke: MutableStateFlow<Joke?> = MutableStateFlow(null)
    override val joke: StateFlow<Joke?> = _joke.asStateFlow()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                _joke.value = fetchJoke()
                delay(5 * 1000)
            }
        }
    }

    @Serializable
    private data class JokeDto(
        val id: String,
        val joke: String,
        val status: Int
    ) {
        fun toJoke(source: URL) = Joke(text = joke, source = source)
    }
}