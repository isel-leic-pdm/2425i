package com.example.diceroll.screens.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diceroll.services.DiceRollService
import kotlinx.coroutines.launch

class DiceRollViewModel : ViewModel() {

    private val rollService = DiceRollService()

    var state by mutableStateOf(DiceRollScreenState.empty)

    fun roll() {
        state = DiceRollScreenState(state.dice, true)
        viewModelScope.launch {
            val newValue = rollService.roll()
            state = DiceRollScreenState(newValue, false)
        }
    }


}