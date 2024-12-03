package pdm.demos.jokeofday

import android.app.Application
import androidx.room.Room
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import pdm.demos.jokeofday.domain.JokesService
import pdm.demos.jokeofday.http.IcanhazDadJokes
import pdm.demos.jokeofday.storage.JokesDB
import java.util.concurrent.TimeUnit

/**
 * The application wide tag used for logging
 */
const val TAG = "JOKE_OF_DAY"

/**
 * The public contract of the application's Service Locator
 */
interface DependenciesContainer {
    val jokesService: JokesService
    val jokesDb: JokesDB
}

/**
 * The custom Application class that provides the actual dependencies
 */
class JokeApplication : Application(), DependenciesContainer {
    private val client by lazy {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
    }

    override val jokesService: JokesService by lazy {
        IcanhazDadJokes(client = client)
    }

    override val jokesDb: JokesDB by lazy {
        Room.databaseBuilder(
            context = this,
            klass = JokesDB::class.java,
            name = "jokes-db"
        ).build()
    }

    override fun onCreate() {
        super.onCreate()

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(androidx.work.NetworkType.UNMETERED)
            .build()

        val workItem = PeriodicWorkRequestBuilder<CoroutineJokeDownloadWorkItem>(
            repeatInterval = 1,
            TimeUnit.DAYS
        ).setConstraints(constraints).build()

        WorkManager.getInstance(this)
            .enqueue(workItem)
    }
}