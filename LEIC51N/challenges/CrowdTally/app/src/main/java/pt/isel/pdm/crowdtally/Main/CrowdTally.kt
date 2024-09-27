package pt.isel.pdm.crowdtally.Main

data class CrowdTally(
    val currentCrowd: Int,
    val maxCrowd: Int
) {
    val isFull
        get() = currentCrowd >= maxCrowd
    val isEmpty
        get() = currentCrowd <= 0

    companion object {
        val default = CrowdTally(0, 10)
    }
}

fun CrowdTally.increment() = CrowdTally(currentCrowd + 1, maxCrowd)
fun CrowdTally.decrement() = CrowdTally(currentCrowd - 1, maxCrowd)
fun CrowdTally.changeMaxCapacity(cap: Int) = CrowdTally(currentCrowd, cap)



