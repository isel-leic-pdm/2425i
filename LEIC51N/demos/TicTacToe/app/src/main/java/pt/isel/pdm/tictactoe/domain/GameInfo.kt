package pt.isel.pdm.tictactoe.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameInfo(
    val gameId: String,
    val playerName: String
) : Parcelable {
}

