package com.example.diceroll.services

import kotlinx.coroutines.delay

class DiceRollServiceImplementation : DiceRollService {
    override suspend fun roll(): Int {
        delay(5000)
        return (1..6).random()
    }
}