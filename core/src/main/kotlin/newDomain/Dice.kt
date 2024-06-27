package vokorpg.newDomain

import kotlin.random.Random

object Dice {
    fun roll(from: Int, until: Int): Int = Random.nextInt(from = from, until = until + 1)
}