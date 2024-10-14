package pdm.demos.jokeofday.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import pdm.demos.jokeofday.DependenciesContainer

/**
 * Script:
 *  1) Revisit the current state of the app
 *  2) Revisit the implementation of the JokeScreen views so that there's no repetitive
 *  use of the Scaffold composable
 *  3) Refine the implementation of the ViewModel in order to:
 *      - Increase the robustness of the implementation (state transitions and visibility of the
 *      state property)
 *      - Inject the service implementation, to enable testing
 *  4) Use a custom Application class as a way to provide the service implementation (Service Locator)
 *    - Use a DependenciesContainer interface and implement it in the custom Application class
 *    - Implement the DependenciesContainer interface in test custom Application class
 *  5) Lets add the required tests
 *      - Discuss approaches
 *      - Implement the tests
 */
class JokeActivity : ComponentActivity() {

    private val viewModel by viewModels<JokeScreenViewModel>(
        factoryProducer = {
            JokeScreenViewModelFactory((application as DependenciesContainer).jokesService)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JokeScreen(viewModel = viewModel)
        }
    }
}
