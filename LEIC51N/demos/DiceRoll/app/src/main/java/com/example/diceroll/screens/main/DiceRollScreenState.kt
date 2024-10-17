package com.example.diceroll.screens.main

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DiceRollScreenState(
    val dice: Int,
    val isRolling: Boolean
) : Parcelable {
    companion object {
        val empty = DiceRollScreenState(-1, false)
        val error = DiceRollScreenState(-0xdead, false)
    }
}