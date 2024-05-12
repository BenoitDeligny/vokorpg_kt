package domain.legend.model

enum class LegendCombatChart(
    val numberOfDice: Int,
    val minMightLevel: Int,
    val maxMightLevel: Int
) {
    ZERO(0, 1, 5),
    ONE(1, 6, 18),
    TWO(2, 19, 30),
    THREE(3, 31, 54),
    FOUR(4, 55, 78),
    FIVE(5, 79, 114),
    SIX(6, 115, 150),
    SEVEN(7, 151, 192),
    EIGHT(8, 193, 234),
    NINE(9, 235, 282),
    TEN(10, 283, Int.MAX_VALUE);

    // TODO: who should have the ownership of this ? Tell don't ask
    companion object {
        // TODO: should be Might instead of Int
        fun combatDiceCount(level: Int): Int = combatChart(level).numberOfDice

        // TODO: rename it maybe
        private fun combatChart(level: Int): LegendCombatChart = entries.find { level in it.minMightLevel..it.maxMightLevel } ?: ZERO
    }
}