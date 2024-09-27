package pt.isel.pdm.crowdtally

import org.junit.Test

import org.junit.Assert.*
import pt.isel.pdm.crowdtally.Main.CrowdTally
import pt.isel.pdm.crowdtally.Main.changeMaxCapacity
import pt.isel.pdm.crowdtally.Main.decrement
import pt.isel.pdm.crowdtally.Main.increment

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun `assert that crowd tally increments correctly`() {
        //arrange
        val ct = CrowdTally.default
        //act
        val inc = ct.increment()
        //assert
        assertTrue(inc.currentCrowd == ct.currentCrowd + 1)
    }

    @Test
    fun `assert that crowd tally decrements correctly`() {
        //arrange
        val ct = CrowdTally.default
        //act
        val dec = ct.decrement()
        //assert
        assertTrue(dec.currentCrowd == ct.currentCrowd - 1)
    }

    @Test
    fun `assert that crowd tally changes max capacity`() {
        //arrange
        val ct = CrowdTally.default
        //act
        val cap = ct.changeMaxCapacity(100)
        //assert
        assertTrue(cap.maxCrowd == 100)
    }
}

//arrange
//act
//assert