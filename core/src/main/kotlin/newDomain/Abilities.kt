package vokorpg.newDomain

import vokorpg.newDomain.Ability.Companion.randomAbility

data class Abilities(
    private val strength: Ability,
    private val agility: Ability,
    private val perception: Ability
) {
    companion object {
        fun randomAbilities() = Abilities(
            strength = randomAbility,
            agility = randomAbility,
            perception = randomAbility
        )
    }

    // TODO: override operator plus ?
    fun sum() = strength.value + agility.value + perception.value
}

@JvmInline
value class Ability(val value: Int) {
    companion object {
        private val dice = Dice
        val randomAbility = Ability(dice.roll(from = 1, until = 6))
    }

    init {
        require(value > 0) { "An ability should be positive. Value = $value." }
    }
}