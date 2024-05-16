package domain.legend

import domain.CombatDicePool
import domain.Damages
import domain.Dice.Companion.aSixSidedDice
import domain.Dice.Companion.rollSixSidedDice
import domain.GameMode
import domain.Might
import domain.Might.Companion.total
import domain.legend.Identity.Factory.create
import domain.legend.model.LegendCombatChart.Companion.combatDicePoolFor
import ulid.ULID
import vokorpg.domain.Gear
import vokorpg.domain.Gear.Companion.standardGear

// Private constructor not ideal - this.copy() can bypass it
data class Legend private constructor(
    val identity: Identity,
    val strength: Strength,
    val agility: Agility,
    val perception: Perception,
    val might: Might = total(strength.value, agility.value, perception.value),
    val gear: Gear,
    val combatDicePool: CombatDicePool = combatDicePoolFor(might)
) {
    companion object {
        fun create(gameMode: GameMode, name: String): Legend = Legend(
            identity = create(name),
            strength = Strength.create(gameMode),
            agility = Agility.create(gameMode),
            perception = Perception.create(gameMode),
            gear = standardGear(),
        )
    }

    // TODO: take bonus in account
    fun attack(): Int = combatDicePool.roll()
    infix fun takes(damages: Damages): Legend = this.copy(might = might - damages)
    fun canRunAway(monsterAntiRunAwayRoll: Int): Boolean = runAway() > monsterAntiRunAwayRoll
    fun isDead(): Boolean = might.remaining <= 0

    private fun runAway(): Int = agility.value + rollSixSidedDice(2)
}

data class Identity(
    private val id: ULID = ULID.nextULID(),
    val name: String,
    val age: Int
) {
    companion object Factory {
        fun create(name: String): Identity = Identity(
            name = name,
            age = aSixSidedDice.roll() + 14
        )
    }

    init {
        require(name.all { it.isLetter() }) { "Name must contains only letters." }
        require(age in 15..20) { "Age must be between 15 and 20. Age = $age." }
    }
}

@JvmInline
value class Strength(val value: Int) {
    companion object Factory {
        val Int.strength get() = Strength(this)
        fun create(gameMode: GameMode): Strength = Strength(aSixSidedDice.roll(gameMode.abilityModifier))
    }

    init {
        require(value > 0) { "Strength should be positive. Value = $value." }
    }
}

@JvmInline
value class Agility(val value: Int) {
    companion object Factory {
        val Int.agility get() = Agility(this)
        fun create(gameMode: GameMode): Agility = Agility(aSixSidedDice.roll(gameMode.abilityModifier))
    }

    init {
        require(value > 0) { "Agility should be positive. Value = $value." }
    }
}

@JvmInline
value class Perception(val value: Int) {
    companion object Factory {
        val Int.perception get() = Perception(this)
        fun create(gameMode: GameMode): Perception = Perception(aSixSidedDice.roll(gameMode.abilityModifier))
    }

    init {
        require(value > 0) { "Perception should be positive. Value = $value." }
    }
}