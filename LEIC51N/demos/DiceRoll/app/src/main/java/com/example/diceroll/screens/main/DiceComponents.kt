package com.example.diceroll.screens.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.diceroll.R
import com.example.diceroll.screens.TAG
import com.example.diceroll.screens.TestTags

@Composable
fun DiceRollIdleView(
    dice: Int,
    buttonEnabled: Boolean,
    rollDice: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Log.v(TAG, "DiceRollScreen")
        Button(
            modifier = Modifier.testTag(TestTags.RollButton),
            onClick = {
                Log.v(TAG, "onClick")
                rollDice()
            },
            enabled = buttonEnabled
        ) {
            Text(text = stringResource(R.string.roll_it))
        }

        if (dice != -1)
            Image(
                modifier = Modifier.testTag(TestTags.DiceImage),
                painter = painterResource(diceImages[dice - 1]),
                contentDescription = dice.toString()
            )
    }
}

@Composable
fun DiceRollingView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White.copy(alpha = .5f)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(200.dp).testTag(TestTags.LoadingWheel))
    }
}

val diceImages = listOf(
    R.drawable.dice1,
    R.drawable.dice2,
    R.drawable.dice3,
    R.drawable.dice4,
    R.drawable.dice5,
    R.drawable.dice6,
)