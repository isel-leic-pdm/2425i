package com.example.diceroll.screens.Main

import android.os.Parcelable
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.diceroll.R
import kotlinx.coroutines.delay
import kotlinx.parcelize.Parcelize

@Parcelize
data class DiceRollScreenState(
    val dice: Int,
    val isRolling: Boolean
) : Parcelable

@Preview(showSystemUi = true)
@Composable()
fun DiceRollScreen() {

    var state by rememberSaveable { mutableStateOf(DiceRollScreenState(-1, false)) }

    LaunchedEffect(state.isRolling) {
        if (state.isRolling) {
            delay(2000)
            state = DiceRollScreenState((1..6).random(), false)
        }
    }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        DiceRollIdleView(
            dice = state.dice,
            rollDice = {
                state = DiceRollScreenState(state.dice, true)
            },
            buttonEnabled = !state.isRolling,
            modifier = Modifier.padding(innerPadding)
        )
        if (state.isRolling)
            DiceRollingView()
    }
}



