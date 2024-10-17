package com.example.diceroll

import android.app.Application
import com.example.diceroll.services.DiceRollService
import com.example.diceroll.services.DiceRollServiceImplementation

interface DependencyContainer {
    val rollService: DiceRollService
}


class DiceRollApplication : Application(), DependencyContainer {

    override fun onCreate() {
        super.onCreate()
    }

    override val rollService by lazy {
        DiceRollServiceImplementation()
    }

}