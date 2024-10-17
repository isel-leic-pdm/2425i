package com.example.diceroll.services

interface DiceRollService {
    suspend fun roll(): Int
}

