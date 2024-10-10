package com.example.diceroll.services

import kotlinx.coroutines.delay

class DiceRollService {
    suspend fun roll(): Int {
        delay(5000)
        return (1..6).random()
    }
}