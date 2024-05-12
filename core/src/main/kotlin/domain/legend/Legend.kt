package domain.legend

import domain.Damages
import domain.Dice
import domain.Dice.Companion.aSixSidedDice
import domain.Dice.Companion.rollSixSidedDice
import domain.GameMode
import domain.Might
import domain.Might.Companion.total
import domain.legend.Identity.Factory.create
import domain.legend.model.LegendCombatChart.Companion.combatDiceCount
import ulid.ULID
import vokorpg.domain.legend.model.Gear
import vokorpg.domain.legend.model.Gear.Factory.standardGear

// TODO: Is it a good idea to make it private ?
data class Legend /* private constructor */(
    val identity: Identity,
    val strength: Strength,
    val agility: Agility,
    val perception: Perception,
    val might: Might = total(strength.value, agility.value, perception.value),
    val gear: Gear,
) {
    val combatDicePool = CombatDicePool(mutableListOf())

    companion object Factory {
        fun create(gameMode: GameMode, name: String): Legend = Legend(
            identity = create(name),
            strength = Strength.create(gameMode),
            agility = Agility.create(gameMode),
            perception = Perception.create(gameMode),
            gear = standardGear(),
        )
    }

    init {
        combatDicePool.addDice(combatDiceCount(might.level))
    }

    // TODO: should be the legend that roll the dice ?
    // TODO: with this came a question: do i need to have a Player ? The aggregate root could be a Player with, among other, a Legend
    // TODO: then, rolling should belong to Player and not Legend
    fun rolling(dice: Dice): Int = dice.roll()

    fun dealsDamages(): Int = rollSixSidedDice(combatDicePool.dice.size)

    // TODO: can we do something like this ? Tell don't ask ?
    // infix fun deals(damages: Damages): Int =
    fun runAway(): Int = agility.value + rollSixSidedDice(2)
    infix fun takes(damages: Damages): Legend = this.copy(might = might - damages)
    fun isDead(): Boolean = might.remaining <= 0
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
        fun create(gameMode: GameMode): Strength = Strength(aSixSidedDice.roll(gameMode.abilityModifier))
    }

    init {
        require(value > 0) { "Strength should be positive. Value = $value." }
    }
}

@JvmInline
value class Agility(val value: Int) {
    companion object Factory {
        fun create(gameMode: GameMode): Agility = Agility(aSixSidedDice.roll(gameMode.abilityModifier))
    }

    init {
        require(value > 0) { "Agility should be positive. Value = $value." }
    }
}

@JvmInline
value class Perception(val value: Int) {
    companion object Factory {
        fun create(gameMode: GameMode): Perception = Perception(aSixSidedDice.roll(gameMode.abilityModifier))
    }

    init {
        require(value > 0) { "Perception should be positive. Value = $value." }
    }
}

@JvmInline
value class CombatDicePool(val dice: MutableList<Dice>) {
    fun addDice(count: Int) = dice.addAll(List(count) { aSixSidedDice })
}