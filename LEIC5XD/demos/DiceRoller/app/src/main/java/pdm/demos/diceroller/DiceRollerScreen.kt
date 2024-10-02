package pdm.demos.diceroller

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import pdm.demos.diceroller.ui.theme.DiceRollerTheme

const val ROLLING_TIME_MS = 2000L

/**
 * The screen implementation, which hosts the screen's state machine.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiceRollerScreen(onAboutNavigate: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name)) },
                actions = {
                    IconButton(onClick = { onAboutNavigate() }) {
                        Icon(Icons.Outlined.Info, contentDescription = "")
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        var state: DiceRollerScreenState by rememberSaveable(saver = DiceRollerScreenState.Saver) {
            mutableStateOf(DiceRollerScreenState.Idle(roll()))
        }

        val currentState = state
        LaunchedEffect(currentState) {
            if (currentState is DiceRollerScreenState.Rolling) {
                delay(timeMillis = ROLLING_TIME_MS)
                state = DiceRollerScreenState.Idle(roll())
            }
        }

        when (currentState) {
            is DiceRollerScreenState.Idle -> DiceRollerIdleView(
                state = currentState,
                onDiceRollIntent = { state = DiceRollerScreenState.Rolling },
                modifier = Modifier.padding(innerPadding)
            )

            else -> DiceRollerRollingView(modifier = Modifier.padding(innerPadding))
        }
    }
}

/**
 * The screen's possible states.
 */
sealed interface DiceRollerScreenState {
    data class Idle(val dice: Dice) : DiceRollerScreenState
    data object Rolling : DiceRollerScreenState

    companion object {
        val Saver = Saver<MutableState<DiceRollerScreenState>, List<Int>>(
            save = { toSave ->
                toSave.value.let { state ->
                    if (state is Idle) listOf(
                        state.dice.range.first,
                        state.dice.range.last,
                        state.dice.value
                    )
                    else emptyList()
                }
            },
            restore = { saved ->
                if (saved.isNotEmpty())
                    mutableStateOf(Idle(Dice(range = saved[0]..saved[1], value = saved[2])))
                else
                    mutableStateOf(Rolling)
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DiceRollerScreenPreview() {
    DiceRollerTheme {
        DiceRollerScreen()
    }
}