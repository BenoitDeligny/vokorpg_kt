package domain.monster

import domain.Dice.Companion.rollSixSidedDice
import domain.Might
import domain.monster.model.MonsterCombatChart
import ulid.ULID

data class Monster(
    val id: ULID = ULID.nextULID(),
    val name: String,
    val might: Might
) {
    fun deals(): Int = rollSixSidedDice(MonsterCombatChart.combatDiceCount(might.level))
    fun takes(damages: Int): Monster = this.copy(might = might.copy(remaining = might.remaining - damages))
}