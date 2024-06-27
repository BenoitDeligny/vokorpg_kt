package vokorpg.newDomain

import vokorpg.newDomain.Abilities.Companion.randomAbilities
import vokorpg.newDomain.Armor.Companion.NONE
import vokorpg.newDomain.Identity.Companion.withRandomAge
import vokorpg.newDomain.Might.Companion.initialized

data class Hero(
    private val identity: Identity,
    private val abilities: Abilities,
    private val armor: Armor = NONE,
    private val might: Might = initialized(abilities.sum() + armor.mightBonus)
) {
    companion object {
        fun random(name: String) = Hero(
            identity = withRandomAge(name),
            abilities = randomAbilities(),
        )
    }

    init {
        require(might.level == (abilities.sum() + armor.mightBonus)) { "Might level should always be the sum of abilities. Level = ${might.level}. Sum = ${abilities.sum()}" }
    }

    infix fun takes(damages: Int) = copy(might = might.damaged(damages))
    infix fun heals(heal: Int) = copy(might = might.healed(heal))
    infix fun wears(armor: Armor) = copy(armor = armor, might = might.increasedLevel(armor.mightBonus))
    infix fun removes(armor: Armor) = copy(armor = NONE, might = might.decreasedLevel(armor.mightBonus))

    // TODO: add combatDicePool
    // TODO: replace Armor by Gear and add everything

}

@JvmInline
value class Armor(val mightBonus: Int) {
    companion object {
        val NONE = Armor(0)
    }
}