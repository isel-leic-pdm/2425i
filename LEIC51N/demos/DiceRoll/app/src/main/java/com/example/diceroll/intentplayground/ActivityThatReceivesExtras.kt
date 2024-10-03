package com.example.diceroll.intentplayground

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.diceroll.intentplayground.ui.theme.DiceRollTheme
import com.example.diceroll.screens.BaseAppActivity
import com.example.diceroll.screens.TAG

class ActivityThatReceivesExtras : BaseAppActivity() {

    companion object {
        private const val ArgumentName = "____testname"
        fun navigate(ctx: ComponentActivity, value: Int) {
            val intent = Intent(ctx, ActivityThatReceivesExtras::class.java)
            intent.putExtra(ArgumentName, value)
            ctx.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if(this.intent.hasExtra(ArgumentName)) {
            val intValue = intent.getIntExtra(ArgumentName, -1)
            Log.v(TAG, "The argument was $intValue")
        }

        setContent {
            DiceRollTheme {
            }
        }
    }
}

