package com.example.diceroll.screens.about

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.diceroll.screens.BaseAppActivity
import com.example.diceroll.screens.components.AppTopBar
import com.example.diceroll.screens.components.NavigationActions
import com.example.diceroll.ui.theme.DiceRollTheme

class AboutActivity : BaseAppActivity() {
    companion object {
        fun navigate(ctx: ComponentActivity) {
            val intent = Intent(ctx, AboutActivity::class.java)

            ctx.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        AppTopBar(
                            navActions = NavigationActions(
                                onBackAction = { this.finish() },
                            )
                        )
                    }

                ) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Text("About activity")

                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)


    }
}
