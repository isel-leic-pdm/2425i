package pdm.demos.guessadoodle

import org.junit.Test

import org.junit.Assert.*
import pdm.demos.guessadoodle.domain.Nick
import kotlin.math.exp

class NickTests {
    @Test(expected = IllegalArgumentException::class)
    fun create_a_nick_with_insufficient_length_throws() {
        Nick("a")
    }

    @Test
    fun create_a_nick_with_sufficient_length_succeeds() {
        Nick("Palecas")
    }

    @Test(expected = IllegalArgumentException::class)
    fun create_a_nick_too_large_fails() {
        Nick("This is a very large nick")
    }

    @Test(expected = IllegalArgumentException::class)
    fun create_a_nick_with_blank_value_fails() {
        Nick("           ")
    }

    @Test(expected = IllegalArgumentException::class)
    fun create_a_nick_with_trimmed_value_with_insufficient_length_fails() {
        Nick("   a      ")
    }

    @Test
    fun create_a_nick_with_non_trimmed_value_with_valid_content_succeeds() {
        Nick("   Palecas      ")
    }

    @Test
    fun nick_value_is_trimmed() {
        val expected = "Palecas"
        val nick = Nick("   $expected      ")
        assert(nick.value == expected)
    }

    @Test
    fun equals_returns_true_for_equal_nicks() {
        val nick1 = Nick("Palecas")
        val nick2 = Nick("    Palecas   ")
        assert(nick1 == nick2) {
            "Instances should be equivalent. Nick1: ${nick1.value}, Nick2: ${nick2.value}"
        }
    }

    @Test
    fun hashCode_returns_same_value_for_equal_nicks() {
        val nick1 = Nick("Palecas")
        val nick2 = Nick("    Palecas   ")
        assert(nick1.hashCode() == nick2.hashCode()) {
            "Hash codes should be equal. Nick1: ${nick1.value}, Nick2: ${nick2.value}"
        }
    }
}