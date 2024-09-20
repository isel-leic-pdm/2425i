package pdm.demos.crowdtally

import org.junit.Test

import org.junit.Assert.*

class CrowdCounterTests {
    @Test
    fun `counter creation succeeds if counter value is between 0 and capacity`() {
        CrowdCounter(value = 5, capacity = 10)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `counter creation fails if counter value is less than 0`() {
        CrowdCounter(value = -1, capacity = 10)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `counter creation fails if counter value is greater than capacity`() {
        CrowdCounter(value = 15, capacity = 10)
    }

    @Test
    fun `increment counter when at capacity returns same value`() {
        val counter = CrowdCounter(value = 10, capacity = 10)
        assertEquals(counter.inc(), counter)
    }

    @Test
    fun `decrement counter when at minimum returns same value`() {
        val counter = CrowdCounter(value = 0, capacity = 10)
        assertEquals(counter.dec(), counter)
    }

    @Test
    fun `increment counter when not at capacity has correct value`() {
        val counter = CrowdCounter(value = 5, capacity = 10)
        val expected = CrowdCounter(value = 6, capacity = 10)
        assertEquals(counter.inc(), expected)
    }

    @Test
    fun `decrement counter when not at minimum has correct value`() {
        val counter = CrowdCounter(value = 5, capacity = 10)
        val expected = CrowdCounter(value = 4, capacity = 10)
        assertEquals(counter.dec(), expected)
    }
}