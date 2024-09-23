package pdm.demos.diceroller

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// The tags used to identify the views in the tests.
const val IDLE_VIEW_TAG = "idle_view"
const val ROLLING_VIEW_TAG = "rolling_view"

/**
 * Gets the test tag of the dice image used to display a dice with the default range of 1 to 6.
 */
fun Dice.toTestTag() = "dice_$value"

// Tags used to identify relevant controls in the tests.
const val ROLL_IT_BUTTON_TAG = "roll_button"
const val PROGRESS_INDICATOR_TAG = "progress_indicator"

@Composable
fun DiceRollerIdleView(
    state: DiceRollerScreenState.Idle,
    onDiceRollIntent: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = modifier.fillMaxSize().testTag(IDLE_VIEW_TAG)
    ) {
        Image(
            painter = painterResource(state.dice.toDiceResource()),
            contentDescription = "",
            modifier = Modifier.size(200.dp).testTag(state.dice.toTestTag())
        )
        Button(onClick = { onDiceRollIntent() }, modifier = Modifier.testTag(ROLL_IT_BUTTON_TAG)) {
            Text(text = stringResource(R.string.roll_button_text))
        }
    }
}

@Composable
fun DiceRollerRollingView(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = modifier.fillMaxSize().testTag(ROLLING_VIEW_TAG)
    ) {
        CircularProgressIndicator(modifier = Modifier.size(200.dp).testTag(PROGRESS_INDICATOR_TAG))
        Button(onClick = {}, enabled = false, modifier = Modifier.testTag(ROLL_IT_BUTTON_TAG)) {
            Text(text = stringResource(R.string.roll_button_text))
        }
    }
}

/**
 * The resources used to display a dice with the default range of 1 to 6.
 */
private val resourceMap = listOf(
    R.drawable.dice_1,
    R.drawable.dice_2,
    R.drawable.dice_3,
    R.drawable.dice_4,
    R.drawable.dice_5,
    R.drawable.dice_6
)

/**
 * Gets the resource id of the dice image used to display a dice with the default range of 1 to 6.
 */
fun Dice.toDiceResource() = resourceMap[value - 1]

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DiceRollerIdleViewPreview() {
    DiceRollerIdleView(state = DiceRollerScreenState.Idle(Dice()), onDiceRollIntent = {})
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DiceRollerRollingViewPreview() {
    DiceRollerRollingView()
}