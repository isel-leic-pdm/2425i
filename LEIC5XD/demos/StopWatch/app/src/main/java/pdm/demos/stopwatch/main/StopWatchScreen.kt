package pdm.demos.stopwatch.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import pdm.demos.stopwatch.R
import pdm.demos.stopwatch.main.views.StopWatchRunningView
import pdm.demos.stopwatch.main.views.StopWatchStoppedView
import pdm.demos.stopwatch.main.views.StopWatchZeroView


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StopWatchScreen(viewModel: StopWatchScreenViewModel) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = stringResource(R.string.app_name)) }) },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        val currentState = viewModel.state
        when (currentState) {
            is StopWatchScreenState.Zero -> StopWatchZeroView(
                onStartIntent = { viewModel.start() },
                modifier = Modifier.padding(padding)
            )

            is StopWatchScreenState.Running -> StopWatchRunningView(
                state = currentState,
                onStopIntent = { viewModel.stop() },
                modifier = Modifier.padding(padding)
            )

            is StopWatchScreenState.Stopped -> StopWatchStoppedView(
                state = currentState,
                onResetIntent = { viewModel.reset() },
                onResumeIntent = { viewModel.start() },
                modifier = Modifier.padding(padding)
            )
        }
    }
}