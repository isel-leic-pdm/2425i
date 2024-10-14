package pdm.demos.jokeofday

import android.app.Application
import pdm.demos.jokeofday.domain.FakeJokesService
import pdm.demos.jokeofday.domain.JokesService

/**
 * The application wide tag used for logging
 */
const val TAG = "JOKE_OF_DAY"

/**
 * The public contract of the application's Service Locator
 */
interface DependenciesContainer {
    val jokesService: JokesService
}

/**
 * The custom Application class that provides the actual dependencies
 */
class JokeApplication : Application(), DependenciesContainer {
    override val jokesService: JokesService by lazy { FakeJokesService() }
}