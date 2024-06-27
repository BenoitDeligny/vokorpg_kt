package vokorpg.domain.sharedkernel

import vokorpg.domain.sharedkernel.Sides.Companion.sides
import kotlin.random.Random

data class Dice(val sideCount: Sides = 6.sides) {
    companion object {
        val aSixSidedDice: Dice = Dice()
    }

    init {
        require(sideCount > 1) { "Dice should have more than 1 side." }
    }

    fun roll(): Int = Random.nextInt(from = 1, until = sideCount + 1)
    fun rolls(modifier: Int = 0): Int = Random.nextInt(from = 1, until = sideCount + 1) + modifier
}

@JvmInline
value class Sides(private val value: Int) {
    companion object {
        val Int.sides get() = Sides(this)
    }

    operator fun compareTo(other: Int) = value.compareTo(other)
    operator fun plus(other: Int) = value.plus(other)
}

@JvmInline
value class CombatDicePool(val dice: List<Dice>) {
    fun roll(): Int = dice.sumOf { it.rolls() }
}