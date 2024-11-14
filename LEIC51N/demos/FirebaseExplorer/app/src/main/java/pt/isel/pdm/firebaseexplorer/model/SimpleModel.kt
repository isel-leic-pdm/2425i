package pt.isel.pdm.firebaseexplorer.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SimpleModel(
    val id : String,
    val number: Int,
    val array : List<Int>,
) : Parcelable{

    companion object
    {
        val none = SimpleModel("none", 1, listOf())
    }
}