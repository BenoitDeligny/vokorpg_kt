package newDomain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.ranges.shouldBeIn
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import vokorpg.newDomain.Abilities
import vokorpg.newDomain.Ability
import vokorpg.newDomain.Hero
import vokorpg.newDomain.Identity

class HeroTest {

    // Property Based Testing
    @RepeatedTest(300)
    fun `Create new Hero with random attributes`() {
        // given
        // when
        // then
        Hero.random("MyHero")
            .apply {
                // TODO: remove getters ?
                identity.name shouldBe "MyHero"
                identity.age.value shouldBeIn 15..20
                abilities.strength.value shouldBeIn 1..6
                abilities.agility.value shouldBeIn 1..6
                abilities.perception.value shouldBeIn 1..6
                might().level shouldBeIn 3..18
            }

    }

    @Nested
    inner class Fails {
        @Test
        fun `Should throw bad name exception`() {
            shouldThrow<IllegalArgumentException> {
                Hero.random("MyHer0")
            }
                .apply { message shouldBe "Name must contains only letters." }

        }

        @Test
        fun `Should throw strength exception`() {
            shouldThrow<IllegalArgumentException> {
                Hero(
                    identity = Identity.withRandomAge("MyHero"),
                    abilities = Abilities(
                        strength = Ability(0),
                        agility = Ability(1),
                        perception = Ability(1)
                    )
                )
            }
                .apply { message shouldBe "An ability should be positive. Value = 0." }
        }

        @Test
        fun `Should throw agility exception`() {
            shouldThrow<IllegalArgumentException> {
                Hero(
                    identity = Identity.withRandomAge("MyHero"),
                    abilities = Abilities(
                        strength = Ability(0),
                        agility = Ability(1),
                        perception = Ability(1)
                    )
                )
            }
                .apply { message shouldBe "An ability should be positive. Value = 0." }
        }

        @Test
        fun `Should throw perception exception`() {
            shouldThrow<IllegalArgumentException> {
                Hero(
                    identity = Identity.withRandomAge("MyHero"),
                    abilities = Abilities(
                        strength = Ability(0),
                        agility = Ability(1),
                        perception = Ability(1)
                    )
                )
            }
                .apply { message shouldBe "An ability should be positive. Value = 0." }

        }
    }
}