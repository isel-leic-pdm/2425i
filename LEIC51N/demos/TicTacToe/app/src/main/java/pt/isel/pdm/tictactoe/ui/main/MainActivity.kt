package pt.isel.pdm.tictactoe.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pt.isel.pdm.tictactoe.ui.BaseActivity
import pt.isel.pdm.tictactoe.ui.game.GameActivity
import pt.isel.pdm.tictactoe.ui.settings.SettingsActivity
import pt.isel.pdm.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Button(onClick = {
                            navigate<GameActivity>()
                        }) {
                            Text("offline!")
                        }
                        Button(onClick = {
                            navigate<SettingsActivity>()
                        }) {
                            Text("settings!")
                        }
                    }

                }
            }
        }
    }
}
