package pdm.demos.jokeofday.http

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
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

    @Serializable
    private data class JokeDto(
        val id: String,
        val joke: String,
        val status: Int
    ) {
        fun toJoke(source: URL) = Joke(text = joke, source = source)
    }
}