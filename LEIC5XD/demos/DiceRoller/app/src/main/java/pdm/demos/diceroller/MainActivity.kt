package pdm.demos.diceroller

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import pdm.demos.diceroller.ui.theme.DiceRollerTheme

const val TAG = "DICE_ROLLER"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(TAG, "onCreate")
        enableEdgeToEdge()
        setContent {
            DiceRollerTheme {
                DiceRollerScreen()
            }
        }
    }
}
