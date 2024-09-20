package pdm.demos.crowdtally

/**
 * A counter of the number of people in a venue of limited capacity. Instances are immutable.
 * @property value the current number of people. Must be between 0 and [capacity].
 * @property capacity the maximum number of people allowed in the venue. Must be positive.
 */
data class CrowdCounter(
    val value: Int = 0,
    val capacity: Int
) {
    init {
        require(capacity > 0) { "Capacity must be positive" }
        require(value in 0..capacity) { "Value must be between 0 and capacity" }
    }

    /**
     * Increments the counter by one.
     * @return a new counter with the incremented value.
     */
    operator fun inc() = if (isNotAtMaximum()) copy(value = value + 1) else this

    /**
     * Decrements the counter by one.
     * @return a new counter with the decremented value.
     */
    operator fun dec() = if (isNotAtMinimum()) copy(value = value - 1) else this

    /**
     * @return `true` if the counter is not at the minimum value, `false` otherwise.
     */
    fun isNotAtMinimum(): Boolean = value > 0

    /**
     * @return `true` if the counter is not at the maximum value, `false` otherwise.
     */
    fun isNotAtMaximum(): Boolean = value < capacity
}
