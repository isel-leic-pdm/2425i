package pt.isel.pdm.nasaimageoftheday

import android.app.Application
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import pt.isel.pdm.nasaimageoftheday.services.NasaImageOfTheDayService
import pt.isel.pdm.nasaimageoftheday.services.RemoteNasaImageOfTheDayService

interface DependencyContainer {
    val nasaService: NasaImageOfTheDayService
}

class NasaImageOfTheDayApplication : Application(), DependencyContainer {
    //override val nasaService: NasaImageOfTheDayService by lazy { FakeNasaImageOfTheDayService() }
    override val nasaService: NasaImageOfTheDayService by lazy { RemoteNasaImageOfTheDayService(httpClient) }

    private val httpClient by lazy {
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

}