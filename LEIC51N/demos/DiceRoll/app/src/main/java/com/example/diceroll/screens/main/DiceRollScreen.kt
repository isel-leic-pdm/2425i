package com.example.diceroll.screens.main

import android.os.Parcelable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable()
fun DiceRollScreen(
    onAboutRequested: () -> Unit
) {

    var state by rememberSaveable { mutableStateOf(DiceRollScreenState(-1, false)) }

    LaunchedEffect(state.isRolling) {
        if (state.isRolling) {
            delay(2000)
            state = DiceRollScreenState((1..6).random(), false)
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                actions = {
                    IconButton(onClick = onAboutRequested) {
                        Icon(imageVector = Icons.Default.Info, contentDescription = "")
                    }
                }
            )
        }
    ) { innerPadding ->
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

@Preview(showSystemUi = true)
@Composable()
fun DiceRollScreenPreview()
{
    DiceRollScreen(
        onAboutRequested = {}
    )
}



