package pdm.demos.jokeofday

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pdm.demos.jokeofday.domain.Joke
import pdm.demos.jokeofday.ui.common.ErrorAlert
import pdm.demos.jokeofday.ui.common.RefreshFab
import pdm.demos.jokeofday.ui.theme.JokeOfDayTheme
import java.net.URL

@Composable
fun IdleView(onFetchRequested: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize().padding(bottom = 16.dp),
        floatingActionButton = { RefreshFab(refreshing = false, onClick = onFetchRequested) },
        floatingActionButtonPosition = FabPosition.Center,
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) { }
    }
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
    Scaffold(
        modifier = Modifier.fillMaxSize().padding(bottom = 16.dp),
        floatingActionButton = { RefreshFab(refreshing = true, onClick = { }) },
        floatingActionButtonPosition = FabPosition.Center,
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) { }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoadingViewPreview() {
    JokeOfDayTheme {
        LoadingView()
    }
}

@Composable
fun SuccessView(joke: Joke, onFetchRequested: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize().padding(bottom = 16.dp),
        floatingActionButton = { RefreshFab(refreshing = false, onClick = onFetchRequested) },
        floatingActionButtonPosition = FabPosition.Center,
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(paddingValues).fillMaxSize().padding(32.dp)
        ) {
            Text(
                text = joke.text,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
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
        modifier = Modifier.fillMaxSize().padding(bottom = 16.dp),
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
