package pdm.demos.diceroller

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pdm.demos.diceroller.ui.theme.DiceRollerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiceRollerScreen(initialDice: Dice = Dice()) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = stringResource(R.string.app_name)) }) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        var dice by rememberSaveable { mutableStateOf(initialDice) }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(dice.toDiceResource()),
                contentDescription = "",
                modifier = Modifier.size(200.dp).testTag(dice.toTestTag())
            )
            Button(onClick = { dice = dice.reRoll() }) {
                Text(text = stringResource(R.string.roll_button_text))
            }
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

/**
 * Gets the test tag of the dice image used to display a dice with the default range of 1 to 6.
 */
fun Dice.toTestTag() = "dice_$value"

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DiceRollerScreenPreview() {
    DiceRollerTheme {
        DiceRollerScreen()
    }
}