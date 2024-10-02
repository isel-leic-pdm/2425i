package pdm.demos.diceroller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import pdm.demos.diceroller.ui.theme.DiceRollerTheme

const val TAG = "DICE_ROLLER"

class MainActivity : ComponentActivity() {

    private val navigateToAboutIntent: Intent by lazy {
        Intent(this, AboutActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(TAG, "MainActivity.onCreate")
        enableEdgeToEdge()
        setContent {
            DiceRollerTheme {
                DiceRollerScreen(onAboutNavigate = { startActivity(navigateToAboutIntent) })
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.v(TAG, "MainActivity.onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.v(TAG, "MainActivity.onStop")
    }
}
