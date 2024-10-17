package com.example.diceroll.rules

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.example.diceroll.DependencyContainer
import com.example.diceroll.services.DiceRollService
import com.example.diceroll.services.DiceRollServiceImplementation
import kotlinx.coroutines.delay


class TestDiceRollService : DiceRollService {
     var delayTime = 1L

    override suspend fun roll(): Int {
        delay(delayTime)
        return 1
    }

}

class TestApplication : Application(), DependencyContainer {
    override val rollService: DiceRollService by lazy {
        TestDiceRollService()
    }
}


class DiceRollInstrumentationTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, TestApplication::class.java.name, context)
    }
}
