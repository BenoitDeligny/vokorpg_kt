package vokorpg.domain.hero

import vokorpg.domain.Dice
import vokorpg.domain.Gear
import vokorpg.domain.Gear.Companion.EASY_MODE_STARTING_PACK
import vokorpg.domain.Gear.Companion.NONE
import vokorpg.domain.Gear.Companion.NORMAL_MODE_STARTING_PACK
import vokorpg.domain.Item
import vokorpg.domain.Might
import vokorpg.domain.Might.Companion.initialized
import vokorpg.domain.hero.Abilities.Companion.randomInEasyMode
import vokorpg.domain.hero.Abilities.Companion.randomInNormalMode
import vokorpg.domain.hero.HeroCombatChart.Companion.chartBy
import vokorpg.domain.hero.Identity.Companion.withRandomAge

data class Hero(
    val identity: Identity,
    val abilities: Abilities,
    val gear: Gear = NONE,
    val might: Might = initialized(abilities.sum() + gear.totalMightBonus())
) {
    companion object {
        // TODO: naming
        fun randomInNormalMode(name: String) = Hero(
            identity = withRandomAge(name),
            abilities = randomInNormalMode(),
            gear = NORMAL_MODE_STARTING_PACK
        )

        // TODO: naming
        fun randomInEasyMode(name: String) = Hero(
            identity = withRandomAge(name),
            abilities = randomInEasyMode(),
            gear = EASY_MODE_STARTING_PACK
        )
    }

    init {
        val theoreticalMaxMight = abilities.sum() + gear.totalMightBonus()
        require(might.level == theoreticalMaxMight) { "Might level should always be the sum of abilities. Level = ${might.level}. Sum = $theoreticalMaxMight" }
    }

    infix fun damagedBy(damages: Int) = copy(might = might.decreasedLifePoints(damages))
    infix fun healedBy(heal: Int) = copy(might = might.increasedLifePoints(heal))
    fun attacks() = combatDicePool().sumOf { dice -> dice.sixSidedRoll() } + gear.totalDamagesBonus()

    // TODO: i would like to make this private --> outsiders must not call this
    // TODO: also it should be "automatic" when the hero comes to 0 life points
    fun dead() = might.lifePoints == 0

    // TODO: naming --> builders (return something) are nouns // manipulators (return void) are verbs
    infix fun putOn(item: Item) = copy(
        gear = gear.wears(item = item),
        might = might.increasedLevel(item.mightModifier)
    )

    infix fun takesOut(item: Item) = copy(
        gear = gear.removes(item = item),
        might = might.decreasedLevel(item.mightModifier)
    )

    // TODO: private fun runAway(): Int = agility.value + rollSixSidedDice(2)

    private fun combatDicePool(): List<Dice> = List(chartBy(might).numberOfDice) { Dice }
}