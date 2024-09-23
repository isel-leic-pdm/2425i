package pdm.demos.diceroller

import android.os.Parcel
import android.os.Parcelable

/**
 * The main domain element for a dice rolling application: the dice itself. Instances are immutable.
 * @param value the value of the dice. Must be in the range of the dice.
 * @param range the range of possible values of the dice.
 */
data class Dice(
    val range: IntRange = DEFAULT_RANGE,
    val value: Int = range.first
) : Parcelable {
    constructor(parcel: Parcel) : this(
        range = parcel.readInt()..parcel.readInt(),
        value = parcel.readInt()
    )

    init {
        require(value in range) { "Dice value must be in range $range" }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(range.first)
        parcel.writeInt(range.last)
        parcel.writeInt(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Dice> {
        override fun createFromParcel(parcel: Parcel): Dice {
            return Dice(parcel)
        }

        override fun newArray(size: Int): Array<Dice?> {
            return arrayOfNulls(size)
        }
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
