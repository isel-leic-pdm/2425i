package pdm.demos.diceroller.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import pdm.demos.diceroller.about.AboutActivity
import pdm.demos.diceroller.ui.theme.DiceRollerTheme

const val TAG = "DICE_ROLLER"

/**
 * The main activity of the application, responsible for displaying the dice roller screen.
 */
class DiceRollerActivity : ComponentActivity() {

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
}
