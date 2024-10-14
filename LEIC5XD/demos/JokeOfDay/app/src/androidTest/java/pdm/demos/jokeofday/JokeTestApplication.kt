package pdm.demos.jokeofday

import android.app.Application
import pdm.demos.jokeofday.domain.FakeJokesService
import pdm.demos.jokeofday.domain.JokesService

class JokeTestApplication : Application(), DependenciesContainer {
    override val jokesService: JokesService by lazy { FakeJokesService() }
}