package pdm.demos.stopwatch.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import pdm.demos.stopwatch.ui.theme.StopWatchTheme

class StopWatchActivity : ComponentActivity() {

    private val viewModel by viewModels<StopWatchScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StopWatchTheme {
                StopWatchScreen(viewModel)
            }
        }
    }
}
