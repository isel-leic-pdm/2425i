package pdm.demos.guessadoodle.domain

const val MIN_NICK_SIZE = 3
const val MAX_NICK_SIZE = 20

/**
 * A Nick is a value object that represents a user's nickname.
 */
class Nick(theValue: String) {

    val value: String = theValue.trim().also { trimmedValue ->
        require(trimmedValue.isValidNick()) {
            "Nick must have between $MIN_NICK_SIZE and $MAX_NICK_SIZE characters and not be blank"
        }
    }

    override fun equals(other: Any?) = when (other) {
        is Nick -> if (this === other) true else value == other.value
        else -> false
    }

    override fun hashCode() = toString().hashCode()
    override fun toString() = value
}

/**
 * Checks if a string is a valid nickname.
 */
fun String.isValidNick() = trim().let {
    it.length >= MIN_NICK_SIZE && it.length <= MAX_NICK_SIZE && !it.isBlank()
}
