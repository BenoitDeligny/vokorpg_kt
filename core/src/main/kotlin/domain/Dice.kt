package vokorpg.domain

import kotlin.random.Random

object Dice {
    fun sixSidedRoll(): Int = roll(from = 1, until = 6)
    private fun roll(from: Int, until: Int): Int = Random.nextInt(from = from, until = until + 1)
}