package pdm.demos.stopwatch.main.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pdm.demos.stopwatch.R
import pdm.demos.stopwatch.domain.StopWatch
import pdm.demos.stopwatch.main.StopWatchScreenState

const val StopWatchZeroViewTag = "StopWatchZeroView"
const val StopWatchRunningViewTag = "StopWatchRunningView"
const val StopWatchStoppedViewTag = "StopWatchStoppedView"

const val ResetButtonTag = "ResetButton"
const val StartResumeButtonTag = "StartResumeButton"

@Composable
fun StopWatchZeroView(
    onStartIntent: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize().testTag(StopWatchZeroViewTag)
    ) {
        StopWatchDisplay(value = StopWatch.Zero.value())
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            ControlButton(
                text = stringResource(id = R.string.reset_button_label),
                onClick = { },
                enabled = false,
                modifier = Modifier.testTag(ResetButtonTag)
            )
            Spacer(modifier = Modifier.width(32.dp))
            ControlButton(
                text = stringResource(R.string.start_button_label),
                onClick = onStartIntent,
                modifier = Modifier.testTag(StartResumeButtonTag)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StopWatchZeroViewPreview() {
    StopWatchZeroView(onStartIntent = { })
}

@Composable
fun StopWatchRunningView(
    state: StopWatchScreenState.Running,
    onStopIntent: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize().testTag(StopWatchRunningViewTag)
    ) {
        StopWatchDisplay(value = state.value, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            ControlButton(
                text = stringResource(id = R.string.reset_button_label),
                enabled = false,
                onClick = { },
                modifier = Modifier.testTag(ResetButtonTag)
            )
            Spacer(modifier = Modifier.width(32.dp))
            ControlButton(
                text = stringResource(R.string.stop_button_label),
                onClick = onStopIntent,
                modifier = Modifier.testTag(StartResumeButtonTag)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StopWatchRunningViewPreview() {
    StopWatchRunningView(
        state = StopWatchScreenState.Running(StopWatch.start()),
        onStopIntent = { }
    )
}

@Composable
fun StopWatchStoppedView(
    state: StopWatchScreenState.Stopped,
    onResumeIntent: () -> Unit,
    onResetIntent: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize().testTag(StopWatchStoppedViewTag)
    ) {
        StopWatchDisplay(value = state.value.value())
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            ControlButton(
                text = stringResource(id = R.string.reset_button_label),
                onClick = onResetIntent,
                modifier = Modifier.testTag(ResetButtonTag)
            )
            Spacer(modifier = Modifier.width(32.dp))
            ControlButton(
                text = stringResource(R.string.resume_button_label),
                onClick = onResumeIntent,
                modifier = Modifier.testTag(StartResumeButtonTag)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StopWatchStoppedViewPreview() {
    StopWatchStoppedView(
        state = StopWatchScreenState.Stopped(StopWatch.start().stop()),
        onResetIntent = { },
        onResumeIntent = { }
    )
}
