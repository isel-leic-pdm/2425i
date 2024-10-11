package pdm.demos.stopwatch.main.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pdm.demos.stopwatch.R
import pdm.demos.stopwatch.domain.StopWatch
import java.util.Locale

const val StopWatchDisplayTestTag = "stopwatch-display"
const val MinutesTestTag = "minutes-text"
const val SecondsTestTag = "seconds-text"
const val DeciSecondsTestTag = "deci-seconds-text"

@Composable
fun StopWatchDisplay(value: StopWatch.Value, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.testTag(StopWatchDisplayTestTag)
    ) {
        HeaderText(
            testTag = MinutesTestTag,
            text = String.format(Locale.ROOT, "%02d:", value.minutes),
            header = stringResource(id = R.string.minutes_label)
        )
        HeaderText(
            testTag = SecondsTestTag,
            text = String.format(Locale.ROOT, "%02d,", value.seconds),
            header = stringResource(id = R.string.seconds_label)
        )
        HeaderText(
            testTag = DeciSecondsTestTag,
            text = String.format(Locale.ROOT, "%02d", value.milliseconds / 10),
            header = stringResource(id = R.string.deciseconds_label)
        )
    }
}

@Composable
private fun HeaderText(testTag: String, text: String, header: String) {
    Column {
        Text(
            text = header,
            modifier = Modifier.offset(y = 4.dp).padding(start = 8.dp)
        )
        Text(
            modifier = Modifier.testTag(testTag),
            text = text,
            style = TextStyle(letterSpacing = 4.sp),
            fontSize = MaterialTheme.typography.displayLarge.fontSize,
        )
    }
}


@Composable
@Preview(showBackground = true)
private fun HeaderTextPreview() {
    HeaderText(testTag = MinutesTestTag, text = "00:", header = "minutes")
}

@Composable
@Preview(showBackground = true)
private fun StopWatchDisplayPreview() {
    StopWatchDisplay(StopWatch.Value(minutes = 2, seconds = 51, milliseconds = 236))
}