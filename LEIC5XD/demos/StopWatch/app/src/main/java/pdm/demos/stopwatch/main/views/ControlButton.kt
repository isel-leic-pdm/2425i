package pdm.demos.stopwatch.main.views

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pdm.demos.stopwatch.ui.theme.StopWatchTheme

@Composable
fun ControlButton(
    modifier: Modifier = Modifier,
    text: String = "",
    enabled: Boolean = true,
    onClick: () -> Unit = { },
) {
    RoundButton(onClick = onClick, enabled = enabled, modifier = modifier) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ControlButtonPreview() {
    StopWatchTheme {
        ControlButton(onClick = {}, text = "Start")
    }
}
