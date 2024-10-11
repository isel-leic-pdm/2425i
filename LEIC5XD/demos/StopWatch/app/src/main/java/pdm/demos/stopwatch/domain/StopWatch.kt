package pdm.demos.stopwatch.domain

private const val MILLISECONDS_IN_MINUTE = 60000
private const val MILLISECONDS_IN_SECOND = 1000

/**
 * Represents a stop watch. Instances are either started or stopped.
 */
data class StopWatch(val start: Long, val end: Long?) {

    companion object {
        fun start(): StopWatch = StopWatch(System.currentTimeMillis(), null)
    }

    /**
     * Represents a stop watch value.
     * @property minutes the number of minutes, non-negative.
     * @property seconds the number of seconds, between 0 and 59.
     * @property milliseconds the number of milliseconds, between 0 and 999.
     */
    data class Value(val minutes: Int, val seconds: Int, val milliseconds: Int) {
        init {
            require(minutes >= 0) { "minutes must be non-negative" }
            require(seconds in 0..59) { "seconds must be between 0 and 59" }
            require(milliseconds in 0..999) { "milliseconds must be between 0 and 999" }
        }
    }

    fun start(): StopWatch = copy(end = null)
    fun stop(): StopWatch = copy(end = System.currentTimeMillis())

    fun isStopped(): Boolean = end != null
    fun isRunning(): Boolean = !isStopped()

    fun value(): Value {
        val elapsed = (end ?: System.currentTimeMillis()) - start
        val minutes = (elapsed / MILLISECONDS_IN_MINUTE).toInt()
        val seconds = ((elapsed % MILLISECONDS_IN_MINUTE) / MILLISECONDS_IN_SECOND).toInt()
        val milliseconds = (elapsed % MILLISECONDS_IN_SECOND).toInt()
        return Value(minutes, seconds, milliseconds)
    }
}
