package pt.isel.pdm.nasaimageoftheday.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NasaImage(
    val title: String,
    val author: String?,
    val description: String,
    val date: String,
    val url: String,
) : Parcelable {
}