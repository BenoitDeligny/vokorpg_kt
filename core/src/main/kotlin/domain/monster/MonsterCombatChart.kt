package vokorpg.domain.monster

import vokorpg.domain.Might

// TODO: Temporary as i don't have database for now
enum class MonsterCombatChart(
    val numberOfDice: Int,
    val minMightLevel: Int,
    val maxMightLevel: Int
) {
    ZERO(0, 1, 8),
    ONE(1, 9, 24),
    TWO(2, 25, 40),
    THREE(3, 41, 64),
    FOUR(4, 65, 88),
    FIVE(5, 89, 124),
    SIX(6, 125, 172),
    SEVEN(7, 173, 220),
    EIGHT(8, 221, 276),
    NINE(9, 277, 332),
    TEN(10, 333, Integer.MAX_VALUE);

    companion object {
        fun chartBy(might: Might): MonsterCombatChart =
            entries.find { might.level in it.minMightLevel..it.maxMightLevel } ?: ZERO
    }
}