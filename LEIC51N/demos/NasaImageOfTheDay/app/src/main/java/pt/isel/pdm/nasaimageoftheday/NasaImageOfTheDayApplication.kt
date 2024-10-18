package pt.isel.pdm.nasaimageoftheday

import android.app.Application
import pt.isel.pdm.nasaimageoftheday.services.FakeNasaImageOfTheDayService
import pt.isel.pdm.nasaimageoftheday.services.NasaImageOfTheDayService

interface DependencyContainer {
    val nasaService: NasaImageOfTheDayService
}

class NasaImageOfTheDayApplication : Application(), DependencyContainer {
    override val nasaService: NasaImageOfTheDayService by lazy { FakeNasaImageOfTheDayService() }
}