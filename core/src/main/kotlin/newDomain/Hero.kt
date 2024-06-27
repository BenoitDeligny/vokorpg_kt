package vokorpg.newDomain

// TODO: what about NewHero class only for the creation of the character at the start of the game ?
data class Hero(
    val identity: Identity,
    val abilities: Abilities,
) {
    companion object {
        fun random(name: String) = Hero(
            identity = Identity.withRandomAge(name),
            abilities = Abilities.random()
        )
    }

    fun might() = Might(abilities.sum())
}

data class Identity(
    val name: String,
    val age: Age,
) {
    companion object {
        fun withRandomAge(name: String) = Identity(name = name, age = Age.random)
    }

    init {
        require(name.all { it.isLetter() }) { "Name must contains only letters." }
        require(age in 15..20) { "Age must be between 15 and 20. Age = $age." }
    }

    operator fun IntRange.contains(age: Age): Boolean {
        return age.value in this
    }
}

@JvmInline
value class Age(val value: Int) {
    companion object {
        private val dice = Dice
        val random = Age(dice.roll(from = 15, until = 20))
    }
}

data class Abilities(
    val strength: Ability,
    val agility: Ability,
    val perception: Ability
) {
    companion object {
        fun random() = Abilities(
            strength = Ability.random,
            agility = Ability.random,
            perception = Ability.random
        )
    }

    // TODO: override operator plus
    fun sum() = strength.value + agility.value + perception.value
}

@JvmInline
value class Ability(val value: Int) {
    companion object {
        private val dice = Dice
        val random = Ability(dice.roll(from = 1, until = 6))
    }

    init {
        require(value > 0) { "An ability should be positive. Value = $value." }
    }
}

data class Might(
    val level: Int
) {
}