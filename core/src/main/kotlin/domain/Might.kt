package vokorpg.domain

data class Might(
    val level: Int,
    val lifePoints: Int
) {
    companion object {
        fun initialized(sum: Int) = Might(level = sum, lifePoints = sum)
    }

    init {
        require(lifePoints <= level) { "Remaining might cannot be above might level. Level = $level. Remaining = $lifePoints." }
        require(level >= 0) { "Level might cannot be negative. Remaining = $level." }
        require(lifePoints >= 0) { "Remaining might cannot be negative. Remaining = $lifePoints." }
    }

    fun increasedLifePoints(healing: Int) = when {
        (lifePoints + healing) <= level -> copy(lifePoints = lifePoints + healing)
        else -> copy(lifePoints = level)
    }

    fun decreasedLifePoints(damages: Int): Might = when {
        (lifePoints - damages) >= 0 -> copy(lifePoints = lifePoints - damages)
        else -> copy(lifePoints = 0)
    }

    fun increasedLevel(increment: Int) = copy(
        level = level + increment,
        lifePoints = lifePoints + increment
    )

    fun decreasedLevel(decrement: Int) = copy(
        level = level - decrement,
        lifePoints = when((lifePoints - decrement) > 0) {
            true -> lifePoints - decrement
            false -> 1
        }
    )
}