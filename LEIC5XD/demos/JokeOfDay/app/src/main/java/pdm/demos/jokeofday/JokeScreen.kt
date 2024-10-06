package pdm.demos.jokeofday

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import pdm.demos.jokeofday.domain.FakeJokesService
import pdm.demos.jokeofday.domain.Joke
import pdm.demos.jokeofday.domain.JokesService
import pdm.demos.jokeofday.ui.common.ErrorAlert
import pdm.demos.jokeofday.ui.theme.JokeOfDayTheme

/**
 * Represents the state of the joke screen.
 */
sealed interface JokeScreenState {
    data object Idle : JokeScreenState
    data object Loading : JokeScreenState
    data class Success(val joke: Joke) : JokeScreenState
    data class Error(val exception: Throwable) : JokeScreenState
}

/**
 * Root composable for the main screen, the one that displays a joke fetched from the used API.
 */
@Composable
fun JokeScreen(service: JokesService) {
    JokeOfDayTheme {
        var state: JokeScreenState by remember { mutableStateOf(JokeScreenState.Idle) }

        val currentState = state
        when (currentState) {
            is JokeScreenState.Idle -> IdleView(
                onFetchRequested = {
                    // TODO (1): Add implementation using coroutine scope and discuss its
                    //  implications.
                }
            )

            is JokeScreenState.Loading -> LoadingView()
            is JokeScreenState.Success -> SuccessView(
                joke = currentState.joke,
                onFetchRequested = { state = JokeScreenState.Loading }
            )

            is JokeScreenState.Error -> ErrorAlert(
                title = R.string.error_api_title,
                message = R.string.error_could_not_reach_api,
                buttonText = R.string.error_retry_button_text,
                onDismiss = { state = JokeScreenState.Loading }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun JokeScreenPreview() {
    JokeScreen(service = FakeJokesService())
}
