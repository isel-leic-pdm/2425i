package pdm.demos.stopwatch.main.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pdm.demos.stopwatch.R
import pdm.demos.stopwatch.domain.StopWatch

// TODO: Implement the several views for the stopwatch screen
@Composable
fun StopWatchView(stopWatch: StopWatch) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        ControlButton(
            text = stringResource(id = R.string.reset_button_label),
            onClick = { /* TODO: Implement */ }
        )
        Spacer(modifier = Modifier.width(32.dp))
        ControlButton(
            text = "Start or Stop", // TODO: Use string resource
            onClick = { /* TODO: Implement */ },
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun StopWatchViewPreview() {
    StopWatchView(StopWatch(0, null))
}