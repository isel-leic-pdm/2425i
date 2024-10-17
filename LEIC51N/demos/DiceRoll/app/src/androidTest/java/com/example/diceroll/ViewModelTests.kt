package com.example.diceroll

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.diceroll.rules.ReplaceMainDispatcherRule
import com.example.diceroll.screens.main.DiceRollScreenState
import com.example.diceroll.screens.main.DiceRollViewModel
import com.example.diceroll.services.DiceRollService
import com.example.diceroll.services.DiceRollServiceImplementation
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ViewModelTests {

    @get:Rule
    val dispatcherRule = ReplaceMainDispatcherRule()

    @Test
    fun `viewmodel shows loading when roll requested`() {
        //  arrange
        val vm = DiceRollViewModel(DiceRollServiceImplementation())
        //  act
        vm.roll()
        //  assert
        assertTrue(vm.state.isRolling)
    }

    @Test
    fun `viewmodel shows result after rolling`() = runTest {
        //  arrange
        val vm = DiceRollViewModel(object : DiceRollService {
            override suspend fun roll(): Int {
                delay(100)
                return 1
            }
        })

        //  act
        vm.roll()

        advanceUntilIdle()

        assertTrue(vm.state.dice != -1)
    }

    @Test
    fun `viewmodel handles errors`() = runTest {
        //  arrange
        val vm = DiceRollViewModel(object : DiceRollService {
            override suspend fun roll(): Int {
                throw Exception("hahahaha")
            }
        })

        //  act
        vm.roll()

        //  assert
        advanceUntilIdle()

        assertTrue(vm.state == DiceRollScreenState.error)
    }
}