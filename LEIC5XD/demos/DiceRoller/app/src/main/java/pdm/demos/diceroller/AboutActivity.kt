package pdm.demos.diceroller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import pdm.demos.diceroller.ui.theme.DiceRollerTheme

class AboutActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(TAG, "AboutActivity.onCreate")
        setContent {
            DiceRollerTheme {
                AboutScreen(onNavigateBack = { finish() })
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.v(TAG, "AboutActivity.onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.v(TAG, "AboutActivity..onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG, "AboutActivity.onDestroy")
    }

}