package pt.isel.pdm.nasaimageoftheday.services

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.Serializable
import pt.isel.pdm.nasaimageoftheday.model.NasaImage

@Serializable
data class NasaImageDto(
    val title: String,
    val author: String = "",
    val explanation: String,
    val date: String,
    val url: String,
    val copyright : String,
)
{
    fun toNasaImage() = NasaImage(title, author, date, explanation, url)
}

class RemoteNasaImageOfTheDayService(
    private val httpClient: HttpClient
) : NasaImageOfTheDayService{

    private val url = "https://api.nasa.gov/planetary/apod?api_key=S6RMxbTyb9pAqhr823IBOzI3BNdtplUVxRRqw4z1"

    override suspend fun getImageOfTheDay(): NasaImage {
       return httpClient.get(url).body<NasaImageDto>().toNasaImage()
    }
}