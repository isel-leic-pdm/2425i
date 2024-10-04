package com.imaginationoverflow.com.revenuemesh.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

private val stopWatchSaver = Saver<Stopwatch, List<Any>>(
    save = { toSave ->
        listOf(toSave.startTime, toSave.endTime, toSave.isRunning)
    },
    restore = { saved ->
        if (saved.isEmpty())
            Stopwatch.zero
        else
            Stopwatch(saved[0] as Long, saved[1] as Long, saved[2] as Boolean)
    }
)

@Composable
fun StopwatchScreen() {
    var stopwatch by rememberSaveable(stateSaver = stopWatchSaver) { mutableStateOf(Stopwatch.zero) }
    var currentTime by rememberSaveable { mutableStateOf(stopwatch.currentTime) }

    LaunchedEffect(stopwatch)
    {
        while (stopwatch.isRunning) {
            currentTime = stopwatch.currentTime
            delay(100)
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()

    ) {
        TimeView(currentTime / 1000)
        TimeControl(
            stopwatch,
            onPlay = {
                if (stopwatch.currentTime == 0L)
                    stopwatch = stopwatch.start()
                else
                    stopwatch = stopwatch.resume()
            },
            onStop = {
                stopwatch = stopwatch.stop()
            },
            onReset = {
                stopwatch = Stopwatch.zero
                currentTime = 0
            }
        )
    }
}

@Composable
fun TimeControl(
    stopwatch: Stopwatch,
    onPlay: () -> Unit,
    onStop: () -> Unit,
    onReset: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {

        if (stopwatch.isRunning) {
            OutlinedButton(onClick = onStop) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "")
            }
        } else {
            OutlinedButton(onClick = onPlay) {
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "")
            }

            if (stopwatch.currentTime > 0)
                OutlinedButton(onClick = onReset) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "")
                }
        }
    }

}

@Composable
fun TimeView(totalSeconds: Long) {
    var hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = hours.toString().padStart(2, '0') + ":",
            fontSize = 70.sp
        )
        Text(
            text = minutes.toString().padStart(2, '0') + ":",
            fontSize = 70.sp
        )
        Text(
            text = seconds.toString().padStart(2, '0'),
            fontSize = 70.sp
        )
    }


}

