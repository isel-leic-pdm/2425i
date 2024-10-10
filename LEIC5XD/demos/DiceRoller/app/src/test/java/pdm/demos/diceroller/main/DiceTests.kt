package pdm.demos.diceroller.main

import org.junit.Test
import org.junit.Assert.*

class DiceTests {
    @Test
    fun `dice roll returns value in the range`() {
        // Arrange
        val range = 1..6
        // Act
        val dice = roll(range)
        // Assert
        assertTrue(dice.value in range)
    }

    @Test
    fun `dice rolls returns different values in different rolls`() {
        // Arrange
        val rollsCount = 10

        // Act
        val firstRoll = roll().value
        var diceRollsTotal = firstRoll
        repeat(times = rollsCount - 1) {
            val dice = roll()
            diceRollsTotal += dice.value
        }

        // Assert
        assertNotEquals(diceRollsTotal, firstRoll * rollsCount)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `creating a dice with a value outside of the range throws an exception`() {
        Dice(value = 7, range = 1..6)
    }
}