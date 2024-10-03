package com.example.diceroll.screens.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.diceroll.about.AboutActivity
import com.example.diceroll.intentplayground.ActivityThatReceivesExtras
import com.example.diceroll.screens.BaseAppActivity
import com.example.diceroll.ui.theme.DiceRollTheme


class MainActivity : BaseAppActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollTheme {
                DiceRollScreen(onAboutRequested = {
                    AboutActivity.navigate(this)
                    //ActivityThatReceivesExtras.navigate(this, 123)
                })
            }
        }
    }

}

