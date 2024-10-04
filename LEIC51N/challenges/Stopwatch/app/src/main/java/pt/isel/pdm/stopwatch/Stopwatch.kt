package pt.isel.pdm.stopwatch

import android.os.Parcelable
import androidx.compose.runtime.MutableState
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stopwatch(
    val startTime: Long,
    val endTime: Long,
    val isRunning: Boolean
) : Parcelable {

    val currentTime: Long
        get() {
            if (isRunning == false)
                return endTime - startTime
            else
                return System.currentTimeMillis() - startTime
        }

    companion object {
        val default = Stopwatch(0, 0, false)
    }
}

fun Stopwatch.start() = Stopwatch(System.currentTimeMillis(), 0, true)
fun Stopwatch.stop() = Stopwatch(startTime, System.currentTimeMillis(), false)
fun Stopwatch.resume() = Stopwatch(System.currentTimeMillis() - currentTime, 0, true)