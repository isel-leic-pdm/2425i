package com.example.diceroll.screens.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.diceroll.services.DiceRollService
import kotlinx.coroutines.launch

class DiceRollViewModel(
    private val rollService: DiceRollService
) : ViewModel() {


    var state by mutableStateOf(DiceRollScreenState.empty)


    fun roll() {
        state = DiceRollScreenState(state.dice, true)
        viewModelScope.launch {
            try {
                val newValue = rollService.roll()
                state = DiceRollScreenState(newValue, false)
            } catch (_: Exception) {
                state = DiceRollScreenState.error
            }
        }
    }


}

class DiceRollViewModelFactory(
    private val service: DiceRollService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DiceRollViewModel(service) as T
    }
}