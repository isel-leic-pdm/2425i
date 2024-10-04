package com.imaginationoverflow.com.revenuemesh.screens

import kotlinx.datetime.Clock

data class Stopwatch(
    val startTime: Long,
    val endTime: Long,
    val isRunning: Boolean,
) {
    val currentTime: Long
        get() {

            if (isRunning)
                return Clock.System.now().toEpochMilliseconds() - startTime
            else
                return endTime - startTime
        }

    companion object {
        val zero = Stopwatch(0, 0, false)
    }
}

fun Stopwatch.start() = Stopwatch(Clock.System.now().toEpochMilliseconds(), 0, true)
fun Stopwatch.stop() = Stopwatch(startTime, Clock.System.now().toEpochMilliseconds(), false)
fun Stopwatch.resume() = Stopwatch(Clock.System.now().toEpochMilliseconds() - currentTime, 0, true)

