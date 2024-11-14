package pdm.demos.guessadoodle.domain

const val MIN_NICK_SIZE = 3
const val MAX_NICK_SIZE = 20

class Nick(theValue: String) {

    val value: String

    init {
        val trimmedValue = theValue.trim()
        require(trimmedValue.isValidNick()) {
            "Nick must have between $MIN_NICK_SIZE and $MAX_NICK_SIZE characters and not be blank"
        }
        value = trimmedValue
    }
}

fun String.isValidNick() = trim().let {
    it.length >= MIN_NICK_SIZE && it.length <= MAX_NICK_SIZE && !it.isBlank()
}
