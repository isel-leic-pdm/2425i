package pdm.demos.stopwatch

import org.junit.Test
import pdm.demos.stopwatch.domain.StopWatch

class StopWatchTests {

    @Test(expected = IllegalArgumentException::class)
    fun `create a stopwatch with a start time higher than fails`() {
        StopWatch(start = -1, end = null)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `create a stopwatch with negative end time fails`() {
        StopWatch(start = 0, end = -1)
    }
}