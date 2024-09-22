package pdm.demos.diceroller

/**
 * The main domain element for a dice rolling application: the dice itself. Instances are immutable.
 * @param value the value of the dice. Must be in the range of the dice.
 * @param range the range of possible values of the dice.
 */
data class Dice(
    val range: IntRange = DEFAULT_RANGE,
    val value: Int = range.first
) {
    init {
        require(value in range) { "Dice value must be in range $range" }
    }
}

/**
 * Rolls the dice with the same range of the current dice (a.k.a. a dice re-roll).
 */
fun Dice.reRoll() = roll(range)

/**
 * The default range of possible values of a dice.
 */
val DEFAULT_RANGE = 1..6

/**
 * Rolls the dice with the given range.
 */
fun roll(range: IntRange = DEFAULT_RANGE) = Dice(range = range, value = range.random())
