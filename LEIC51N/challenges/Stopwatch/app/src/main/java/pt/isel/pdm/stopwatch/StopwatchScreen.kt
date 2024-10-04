package pt.isel.pdm.stopwatch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun StopwatchScreen() {
    var stopwatch by rememberSaveable { mutableStateOf(Stopwatch.default) }
    var uiCurrentTime by remember { mutableLongStateOf(stopwatch.currentTime) }

    LaunchedEffect(stopwatch) {
        while (stopwatch.isRunning)
        {
            uiCurrentTime = stopwatch.currentTime
            delay(500)
        }
    }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimeView(uiCurrentTime)

            TimeControlView(
                stopwatch,
                onPlay = {
                    if (stopwatch.currentTime > 0)
                        stopwatch = stopwatch.resume()
                    else
                        stopwatch = stopwatch.start()

                },
                onStop = {
                    stopwatch = stopwatch.stop()
                },
                onReset = {
                    stopwatch = Stopwatch.default
                    uiCurrentTime = stopwatch.currentTime
                }
            )
        }
    }
}

@Composable
fun TimeControlView(
    stopwatch: Stopwatch,
    onPlay: () -> Unit,
    onStop: () -> Unit,
    onReset: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {

        if (stopwatch.isRunning == false) {
            OutlinedButton(onClick = onPlay) {
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "")
            }

            if (stopwatch.currentTime > 0)
                OutlinedButton(onClick = onReset) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "")
                }
        } else
            OutlinedButton(onClick = onStop) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "")
            }
    }
}

@Composable
fun TimeView(currentTimeMilis: Long) {

    val totalSeconds = currentTimeMilis / 1000

    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = (totalSeconds % 3600) % 60

    Text(
        text = String.format("%02d:%02d:%02d", hours, minutes, seconds),
        fontSize = 70.sp
    )

}
