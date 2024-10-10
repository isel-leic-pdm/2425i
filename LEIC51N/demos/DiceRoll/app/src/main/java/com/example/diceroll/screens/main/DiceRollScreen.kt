package com.example.diceroll.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.diceroll.screens.components.AppTopBar
import com.example.diceroll.screens.components.NavigationActions
import com.example.diceroll.services.DiceRollService
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable()
fun DiceRollScreen(
    onAboutRequested: () -> Unit,
    viewModel: DiceRollViewModel
) {
    val state = viewModel.state

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                navActions = NavigationActions(
                    onAboutAction = onAboutRequested,
                )
            )
        }
    ) { innerPadding ->
        DiceRollIdleView(
            dice = state.dice,
            rollDice = {
                viewModel.roll()
            },
            buttonEnabled = !state.isRolling,
            modifier = Modifier.padding(innerPadding)
        )

        if (state.isRolling)
            DiceRollingView()
    }
}





