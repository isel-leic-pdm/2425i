package pdm.demos.stopwatch

import org.junit.Test
import pdm.demos.stopwatch.domain.StopWatch

class StopWatchValueTests {

    @Test(expected = IllegalArgumentException::class)
    fun `create a stopwatch value with non-positive minutes fails`() {
        StopWatch.Value(minutes = -1, seconds = 0, milliseconds = 0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create a stopwatch value with negative seconds fails`() {
        StopWatch.Value(minutes = 0, seconds = -1, milliseconds = 0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create a stopwatch value with seconds greater than 59 fails`() {
        StopWatch.Value(minutes = 0, seconds = 60, milliseconds = 0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create a stopwatch value with negative milliseconds fails`() {
        StopWatch.Value(minutes = 0, seconds = 0, milliseconds = -1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create a stopwatch value with milliseconds greater than 999 fails`() {
        StopWatch.Value(minutes = 0, seconds = 0, milliseconds = 1000)
    }
}