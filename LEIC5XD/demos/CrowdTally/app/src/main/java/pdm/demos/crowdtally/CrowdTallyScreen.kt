package pdm.demos.crowdtally

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import pdm.demos.crowdtally.CrowdTallyScreenState.Configuration
import pdm.demos.crowdtally.CrowdTallyScreenState.Counting
import pdm.demos.crowdtally.ui.theme.CrowdTallyTheme

sealed interface CrowdTallyScreenState {
    data class Configuration(val initialCapacity: Int) : CrowdTallyScreenState
    data class Counting(val counter: CrowdCounter) : CrowdTallyScreenState
}

@Composable
fun CrowdTallyScreen(initialCounter: CrowdCounter = CrowdCounter(capacity = 10)) {
    CrowdTallyTheme {
        var state: CrowdTallyScreenState by remember { mutableStateOf(Counting(initialCounter)) }

        when (val currentState = state) {
            is Configuration -> {
                ConfigurationView(
                    state = currentState,
                    onSaveIntent = { newCapacity ->
                        state = Counting(CrowdCounter(value = 0, capacity = newCapacity))
                    },
                )
            }

            is Counting -> {
                CountingView(
                    state = currentState,
                    onDecrementIntent = { state = Counting(counter = currentState.counter.dec()) },
                    onIncrementIntent = { state = Counting(currentState.counter.inc()) },
                    onConfigureIntent = { state = Configuration(currentState.counter.capacity) }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CrowdTallyScreenPreview() {
    CrowdTallyScreen()
}