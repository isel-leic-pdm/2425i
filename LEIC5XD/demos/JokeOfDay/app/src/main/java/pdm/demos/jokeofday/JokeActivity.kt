package pdm.demos.jokeofday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import pdm.demos.jokeofday.domain.FakeJokesService

const val TAG = "JOKE_OF_DAY"

/**
 * Script:
 *  1) Explain current state of the app
 *      - Clear separation between domain and UI
 *      - JokeScreen is the root composable that hosts the screen's state machine
 *      - JokeScreenState is a sealed interface that represents the different states of the screen
 *      - IdleView, LoadingView, SuccessView, and ErrorAlert are the views for each screen state
 *  2) Explain the JokeService interface and the FakeJokesService implementation
 *  3) Implement the fetch operation using rememberCoroutineScope.
 *      - Discuss the need for using a coroutine when performing network operations
 *      - Discuss the implications of using rememberCoroutineScope
 *  4) Introduce ViewModel
 *      - Its lifecycle and how it can be used to manage the state of the screen in the
 *      context of configuration changes
 *      - Its role as a bridge between the UI and the domain
 *  5) Revisit the implementation of the JokeScreen using a ViewModel
 *  6) (Optional) Revisit the implementation of the JokeScreen views so that there's no repetitive
 *  use of the Scaffold composable
 */
class JokeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JokeScreen(service = FakeJokesService())
        }
    }
}
