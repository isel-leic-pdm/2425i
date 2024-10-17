package com.example.diceroll.screens.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.diceroll.DependencyContainer
import com.example.diceroll.screens.about.AboutActivity
import com.example.diceroll.intentplayground.ActivityThatReceivesExtras
import com.example.diceroll.screens.BaseAppActivity
import com.example.diceroll.screens.helpers.initWithSavedState
import com.example.diceroll.screens.helpers.viewModelInit
import com.example.diceroll.services.DiceRollService
import com.example.diceroll.ui.theme.DiceRollTheme


class MainActivity : BaseAppActivity() {

    /*
    val viewModel by viewModels<DiceRollViewModel>(factoryProducer = {
        DiceRollViewModelFactory(
            DiceRollService()
        )
    })
    */

    val viewModel by viewModels<DiceRollViewModel>() {
        viewModelInit {
            DiceRollViewModel(((this.application) as DependencyContainer).rollService)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollTheme {
                DiceRollScreen(
                    onAboutRequested = {
                        AboutActivity.navigate(this)
                        //ActivityThatReceivesExtras.navigate(this, 123)
                    }, viewModel = viewModel
                )
            }
        }
    }

}

