package pdm.demos.jokeofday.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pdm.demos.jokeofday.R
import pdm.demos.jokeofday.domain.Joke
import pdm.demos.jokeofday.ui.common.ErrorAlert
import pdm.demos.jokeofday.ui.common.RefreshFab
import pdm.demos.jokeofday.ui.theme.JokeOfDayTheme
import java.net.URL

const val IDLE_VIEW_TAG = "IdleView"
const val LOADING_VIEW_TAG = "LoadingView"
const val SUCCESS_VIEW_TAG = "SuccessView"
const val ERROR_VIEW_TAG = "ErrorView"

@Composable
fun IdleView(onFetchRequested: () -> Unit) {
    JokeViewsStructure(
        onFabClicked = onFetchRequested,
        modifier = Modifier.testTag(IDLE_VIEW_TAG)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IdleViewPreview() {
    JokeOfDayTheme {
        IdleView(onFetchRequested = { })
    }
}

@Composable
fun LoadingView() {
    JokeViewsStructure(
        modifier = Modifier.testTag(LOADING_VIEW_TAG)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoadingViewPreview() {
    JokeOfDayTheme {
        LoadingView()
    }
}

const val JOKE_TEXT_TAG = "JokeText"

@Composable
fun SuccessView(joke: Joke, onFetchRequested: () -> Unit) {
    JokeViewsStructure(
        onFabClicked = onFetchRequested,
        modifier = Modifier.testTag(SUCCESS_VIEW_TAG)
    ) {
        Text(
            text = joke.text,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .testTag(JOKE_TEXT_TAG)
        )
        Text(
            text = joke.source.host,
            style = MaterialTheme.typography.bodySmall,
            textDecoration = TextDecoration.Underline,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SuccessViewPreview() {
    val aJoke = Joke(
        text = "It's a 5 minute walk from my house to the pub\n" +
                "It's a 35 minute walk from the pub to my house\n" +
                "The difference is staggering.",
        source = URL("https://www.keeplaughingforever.com/corny-dad-jokes")
    )
    SuccessView(joke = aJoke, onFetchRequested = { })
}

@Composable
fun ErrorView(onDismissRequested: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
            .testTag(ERROR_VIEW_TAG),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ErrorAlert(
            title = R.string.error_api_title,
            message = R.string.error_could_not_reach_api,
            buttonText = R.string.error_retry_button_text,
            onDismiss = onDismissRequested
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ErrorViewPreview() {
    JokeOfDayTheme {
        ErrorView(onDismissRequested = { })
    }
}

const val REFRESH_FAB_TAG = "RefreshFab"

@Composable
fun JokeViewsStructure(
    modifier: Modifier = Modifier,
    onFabClicked: (() -> Unit)? = null,
    content: @Composable () -> Unit = { },
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
        floatingActionButton = {
            RefreshFab(
                refreshing = onFabClicked == null,
                onClick = onFabClicked ?: { },
                modifier = Modifier.testTag(REFRESH_FAB_TAG)
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(32.dp)
        ) {
            content()
        }
    }
}