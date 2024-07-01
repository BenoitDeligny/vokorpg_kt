package vokorpg.domain.hero

import vokorpg.domain.Might

// TODO: Temporary as i don't have database for now
enum class HeroCombatChart(
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

    companion object {
        fun chartBy(might: Might): HeroCombatChart =
            entries.find { might.level in it.minMightLevel..it.maxMightLevel } ?: ZERO
    }
}