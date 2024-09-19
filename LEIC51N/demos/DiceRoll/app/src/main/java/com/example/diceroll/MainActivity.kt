package com.example.diceroll

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.diceroll.ui.theme.DiceRollTheme

const val TAG = "DiceRoll"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(TAG, "onCreate")
        enableEdgeToEdge()
        setContent {
            DiceRollTheme {
                DiceRollScreen()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.v(TAG, "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.v(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG, "onDestroy")
    }
}


@Preview(showSystemUi = true)
@Composable()
fun DiceRollScreen() {

    var dice by remember { mutableIntStateOf(-1) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )   
        {

            Log.v(TAG, "DiceRollScreen")
            Button(onClick = {
                Log.v(TAG, "onClick")
                dice = (1..6).random()
            }) {
                Text(text = stringResource(R.string.roll_it))
            }

            if (dice != -1)
                Image(
                    painter = painterResource(diceImages[dice - 1]),
                    contentDescription = dice.toString())
        }
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

