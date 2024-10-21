package pdm.demos.jokeofday.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import pdm.demos.jokeofday.DependenciesContainer

/**
 * Activity that displays a joke.
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
