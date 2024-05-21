package domain.monster

import ulid.ULID
import vokorpg.domain.sharedkernel.Might

data class Monster(
    val id: ULID = ULID.nextULID(),
    val name: String,
    val might: Might
) {
    //    fun deals(): Int = rollSixSidedDice(combatDiceCount(might.level))
    fun takes(damages: Int): Monster = this.copy(might = might.copy(remaining = might.remaining - damages))
}