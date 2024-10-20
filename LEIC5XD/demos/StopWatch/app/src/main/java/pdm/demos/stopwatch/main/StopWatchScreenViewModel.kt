package pdm.demos.stopwatch.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pdm.demos.stopwatch.domain.StopWatch

/**
 * Represents the state of the stopwatch screen.
 */
sealed interface StopWatchScreenState {

    data object Zero : StopWatchScreenState

    data class Running(
        val stopWatch: StopWatch,
        val value: StopWatch.Value = stopWatch.value()
    ) : StopWatchScreenState

    data class Stopped(val value: StopWatch) : StopWatchScreenState
}

/**
 * The view model for the stopwatch screen, as per the MVVM architecture and our design.
 */
class StopWatchScreenViewModel : ViewModel() {

    var state: StopWatchScreenState by mutableStateOf(StopWatchScreenState.Zero)
        private set

    fun start() {
        val currentState = state
        if (currentState is StopWatchScreenState.Running)
            return


        state = StopWatchScreenState.Running(StopWatch.start())
        viewModelScope.launch {
            while (true) {
                delay(10)
                state.let {
                    if (it is StopWatchScreenState.Running) {
                        state = it.copy(value = it.stopWatch.value())
                    }
                }
            }
        }
    }

    fun stop() {
        val currentState = state
        if (currentState is StopWatchScreenState.Running) {
            state = StopWatchScreenState.Stopped(currentState.stopWatch.stop())
        }
    }

    fun reset() {
        if (state is StopWatchScreenState.Stopped) {
            state = StopWatchScreenState.Zero
        }
    }
}