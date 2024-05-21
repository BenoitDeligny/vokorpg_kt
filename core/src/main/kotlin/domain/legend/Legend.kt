package domain.legend

import domain.legend.Identity.Factory.create
import ulid.ULID
import vokorpg.domain.gear.Gear
import vokorpg.domain.gear.Gear.Companion.standardGear
import vokorpg.domain.gear.Item
import vokorpg.domain.legend.LegendCombatChart.Companion.combatDicePoolFor
import vokorpg.domain.sharedkernel.CombatDicePool
import vokorpg.domain.sharedkernel.Damages
import vokorpg.domain.sharedkernel.Dice.Companion.aSixSidedDice
import vokorpg.domain.sharedkernel.GameMode
import vokorpg.domain.sharedkernel.Might
import vokorpg.domain.sharedkernel.Might.Companion.might
import vokorpg.domain.sharedkernel.Might.Companion.mights
import vokorpg.domain.sharedkernel.Might.Companion.total

data class Legend(
    val identity: Identity,
    val strength: Strength,
    val agility: Agility,
    val perception: Perception,
    var might: Might,
    val gear: Gear,
) {
    val combatDicePool get() = combatDicePoolFor(might)

    companion object {
        fun create(gameMode: GameMode, name: String): Legend {
            val identity = create(name)
            val strength = Strength.create(gameMode)
            val agility = Agility.create(gameMode)
            val perception = Perception.create(gameMode)
            val gear = standardGear()
            val might = total(strength.value, agility.value, perception.value, gear.mightBonus()).mights

            return Legend(
                identity = identity,
                strength = strength,
                agility = agility,
                perception = perception,
                might = might,
                gear = gear,
            )
        }
    }

    init {
        require(might.level == total(strength.value, agility.value, perception.value, gear.mightBonus())) { "Might level should always be the sum of stats and gear bonus." }
        might = might.copy(level = total(strength.value, agility.value, perception.value, gear.mightBonus()))
    }

    // TODO: take bonus in account
//    fun attack(): Int = combatDicePool.roll()
    infix fun takes(damages: Damages): Legend = copy(might = might - damages)
//    fun canRunAway(monsterAntiRunAwayRoll: Int): Boolean = runAway() > monsterAntiRunAwayRoll
//    fun isDead(): Boolean = might.remaining <= 0

    fun putArmor(armor: Item.Armor) = copy(gear = gear.copy(armor = armor))

//    private fun runAway(): Int = agility.value + rollSixSidedDice(2)
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