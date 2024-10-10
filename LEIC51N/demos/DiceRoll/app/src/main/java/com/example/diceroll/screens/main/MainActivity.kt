package com.example.diceroll.screens.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.diceroll.screens.about.AboutActivity
import com.example.diceroll.intentplayground.ActivityThatReceivesExtras
import com.example.diceroll.screens.BaseAppActivity
import com.example.diceroll.services.DiceRollService
import com.example.diceroll.ui.theme.DiceRollTheme


class MainActivity : BaseAppActivity() {

    val viewModel by viewModels<DiceRollViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollTheme {
                DiceRollScreen(
                    onAboutRequested = {
                        AboutActivity.navigate(this)
                        //ActivityThatReceivesExtras.navigate(this, 123)
                    },
                    viewModel = viewModel
                )
            }
        }
    }

}

