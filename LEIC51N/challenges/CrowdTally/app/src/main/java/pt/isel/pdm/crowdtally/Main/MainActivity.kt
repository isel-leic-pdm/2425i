package pt.isel.pdm.crowdtally.Main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {

    val LogTag = "CrowdTally"
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(LogTag, "onCreate")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CrowdTallyScreen()
        }
    }

    override fun onPause() {
        Log.d(LogTag, "onPause")
        super.onPause()
    }

    override fun onDestroy() {
        Log.d(LogTag, "onDestroy")
        super.onDestroy()
    }


    override fun onStop() {
        Log.d(LogTag, "onStop")
        super.onStop()
    }

    override fun onStart() {
        Log.d(LogTag, "onStart")
        super.onStart()
    }

}