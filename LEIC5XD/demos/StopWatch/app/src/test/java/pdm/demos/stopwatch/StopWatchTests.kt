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

    @Test
    fun `stop on a stopWatch returns a new instance with the end time`() {
        val stopWatch = StopWatch(start = 0, end = null)
        val sut = stopWatch.stop()
        assert(sut.start == 0L) { "start time is not the same" }
        assert(sut.end != null) { "end time is null" }
    }

    @Test
    fun `start on a zero instance returns a new instance with no end time`() {
        val stopWatch = StopWatch.Zero
        val sut = stopWatch.start()
        assert(sut.end == null) { "end time is not null" }
    }

    @Test
    fun `isRunning on a zero instance returns false`() {
        val stopWatch = StopWatch.Zero
        assert(!stopWatch.isRunning()) { "isRunning is true" }
    }

    @Test
    fun `isStopped on a zero instance returns true`() {
        val stopWatch = StopWatch.Zero
        assert(stopWatch.isStopped()) { "isStopped is false" }
    }

    @Test
    fun `isStopped in a stopped instance returns true`() {
        val stopWatch = StopWatch(start = 0, end = 1)
        assert(stopWatch.isStopped()) { "isStopped is false" }
    }

    @Test
    fun `isRunning in a stopped instance returns false`() {
        val stopWatch = StopWatch(start = 0, end = 1)
        assert(!stopWatch.isRunning()) { "isRunning is true" }
    }

    @Test
    fun `isStopped in a running instance returns false`() {
        val stopWatch = StopWatch(start = 0, end = null)
        assert(!stopWatch.isStopped()) { "isStopped is true" }
    }

    @Test
    fun `isStopped in a running instance returns true`() {
        val stopWatch = StopWatch(start = 0, end = null)
        assert(stopWatch.isRunning()) { "isRunning is false" }
    }

    @Test
    fun `value in a stopped instance returns the correct value`() {
        val expectedMinutes = 1
        val expectedSeconds = 1
        val stopWatch =
            StopWatch(start = 0, end = expectedMinutes * 60 * 1000L + expectedSeconds * 1000L)
        val value = stopWatch.value()
        assert(value.minutes == expectedMinutes) {
            "Expected $expectedMinutes minutes but got ${value.minutes}"
        }
        assert(value.seconds == expectedSeconds) {
            "Expected $expectedSeconds minutes but got ${value.seconds}"
        }
        assert(value.milliseconds == 0) {
            "Expected 0 milliseconds but got ${value.milliseconds}"
        }
    }
}