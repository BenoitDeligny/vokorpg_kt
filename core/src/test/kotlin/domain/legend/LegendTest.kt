package domain.legend

import domain.Damages.Companion.damages
import domain.GameMode.EASY
import domain.GameMode.NORMAL
import domain.Might
import domain.legend.Legend.Factory.create
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.ranges.shouldBeIn
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import vokorpg.domain.legend.model.Gear.Factory.standardGear

class LegendTest {

    @Nested
    inner class NormalMode {
        @RepeatedTest(300)
        fun `Should create a Legend in normal mode`() {
            create(
                gameMode = NORMAL,
                name = "MyHero"
            )
                .apply {
                    name() shouldBe "MyHero"
                    age() shouldBeIn (15..20)

                    strength() shouldBeIn (2..7)
                    agility() shouldBeIn (2..7)
                    perception() shouldBeIn (2..7)

                    mightLevel() shouldBeIn (6..21)
                    remainingMight() shouldBe mightLevel()

                    combatDiceCount() shouldBeIn (1..2)
                }
        }

        @Test
        fun `Should takes damages`() {
            // given
            // when
            val legend = create(
                gameMode = NORMAL,
                name = "MyHero"
            )
                .copy(might = Might(level = 15))
                .run {
                    this takes 5.damages
                }

            // then
            legend.remainingMight() shouldBe 10
        }
    }

    @Nested
    inner class EasyMode {
        @RepeatedTest(300)
        fun `Should create a Legend in easy mode`() {
            create(
                gameMode = EASY,
                name = "MyHero"
            )
                .apply {
                    name() shouldBe "MyHero"
                    age() shouldBeIn (15..20)

                    strength() shouldBeIn (5..10)
                    agility() shouldBeIn (5..10)
                    perception() shouldBeIn (5..10)

                    mightLevel() shouldBeIn (15..30)
                    remainingMight() shouldBe mightLevel()

                    combatDiceCount() shouldBeIn (1..2)
                }
        }
    }

    @Nested
    inner class Fails {
        @Test
        fun `Should throw bad name exception`() {
            shouldThrow<IllegalArgumentException> {
                create(
                    gameMode = NORMAL,
                    name = "MyHer0"
                )
            }
                .apply { message shouldBe "Name must contains only letters." }

        }

        @Test
        fun `Should throw strength exception`() {
            shouldThrow<IllegalArgumentException> {
                Legend(
                    Identity.create("MyHero"),
                    Strength(0),
                    Agility.create(NORMAL),
                    Perception.create(NORMAL),
                    gear = standardGear()
                )
            }
                .apply { message shouldBe "Strength should be positive. Value = 0." }
        }

        @Test
        fun `Should throw agility exception`() {
            shouldThrow<IllegalArgumentException> {
                Legend(
                    Identity.create("MyHero"),
                    Strength.create(NORMAL),
                    Agility(0),
                    Perception.create(NORMAL),
                    gear = standardGear()
                )
            }
                .apply { message shouldBe "Agility should be positive. Value = 0." }
        }

        @Test
        fun `Should throw perception exception`() {
            shouldThrow<IllegalArgumentException> {
                Legend(
                    Identity.create("MyHero"),
                    Strength.create(NORMAL),
                    Agility.create(NORMAL),
                    Perception(0),
                    gear = standardGear()
                )
            }
                .apply { message shouldBe "Perception should be positive. Value = 0." }

        }
    }

    private fun Legend.name() = identity.name
    private fun Legend.age() = identity.age
    private fun Legend.strength() = strength.value
    private fun Legend.agility() = agility.value
    private fun Legend.perception() = perception.value
    private fun Legend.mightLevel() = might.level
    private fun Legend.remainingMight() = might.remaining
    private fun Legend.combatDiceCount() = combatDicePool.dice.size
}