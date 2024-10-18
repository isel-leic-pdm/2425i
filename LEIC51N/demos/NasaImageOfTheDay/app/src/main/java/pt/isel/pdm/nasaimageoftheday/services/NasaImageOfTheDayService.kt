package pt.isel.pdm.nasaimageoftheday.services

import pt.isel.pdm.nasaimageoftheday.model.NasaImage

interface NasaImageOfTheDayService {
    suspend fun getImageOfTheDay(): NasaImage
}