package pdm.demos.jokeofday.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import pdm.demos.jokeofday.R
import pdm.demos.jokeofday.domain.FakeJokesService
import pdm.demos.jokeofday.ui.common.ErrorAlert
import pdm.demos.jokeofday.ui.theme.JokeOfDayTheme

/**
 * Root composable for the main screen, the one that displays a joke fetched from the used API.
 */
@Composable
fun JokeScreen(viewModel: JokeScreenViewModel) {
    JokeOfDayTheme {

        val currentState = viewModel.state
        when (currentState) {
            is JokeScreenState.Idle -> IdleView(
                onFetchRequested = { viewModel.fetchJoke() }
            )

            is JokeScreenState.Loading -> LoadingView()
            is JokeScreenState.Success -> SuccessView(
                joke = currentState.joke,
                onFetchRequested = { viewModel.fetchJoke() }
            )

            is JokeScreenState.Error -> ErrorAlert(
                title = R.string.error_api_title,
                message = R.string.error_could_not_reach_api,
                buttonText = R.string.error_retry_button_text,
                onDismiss = { viewModel.fetchJoke() }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun JokeScreenPreview() {
    JokeScreen(viewModel = JokeScreenViewModel(FakeJokesService()))
}
