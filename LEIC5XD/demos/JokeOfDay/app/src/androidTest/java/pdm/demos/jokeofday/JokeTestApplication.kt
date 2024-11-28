package pdm.demos.jokeofday

import android.app.Application
import androidx.room.Room
import pdm.demos.jokeofday.domain.FakeJokesService
import pdm.demos.jokeofday.domain.JokesService
import pdm.demos.jokeofday.storage.JokesDB

class JokeTestApplication : Application(), DependenciesContainer {
    override val jokesService: JokesService by lazy { FakeJokesService() }
    override val jokesDb: JokesDB by lazy {
        Room.inMemoryDatabaseBuilder(
            context = this,
            klass = JokesDB::class.java
        ).build()
    }
}