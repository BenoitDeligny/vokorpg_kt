package newDomain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.ranges.shouldBeIn
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import vokorpg.newDomain.*
import vokorpg.newDomain.Identity.Companion.withRandomAge

class HeroTest {


    @Nested
    inner class HeroCreation {
        // Property Based Testing
        @RepeatedTest(300)
        fun `create new Hero with random attributes`() {
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
                    might.level shouldBeIn 3..18
                    might.lifePoints shouldBeEqual might.level
                }

        }
    }

    @Nested
    inner class HeroTakesDamaged {
        @Test
        fun `hero takes damages`() {
            // given
            // when
            val hero = Hero(
                identity = withRandomAge(name = "MyHero"),
                abilities = Abilities(
                    strength = Ability(5),
                    agility = Ability(5),
                    perception = Ability(5)
                )
            )
                .run { this takes 5 }

            // then
            hero.might.level shouldBe 15
            hero.might.lifePoints shouldBe 10
        }

        @Test
        fun `hero can't be take more damages than remaining might`() {
            // given
            // when
            val hero = Hero(
                identity = withRandomAge(name = "MyHero"),
                abilities = Abilities(
                    strength = Ability(5),
                    agility = Ability(5),
                    perception = Ability(5)
                )
            )
                .run { this takes 25 }

            // then
            hero.might.level shouldBe 15
            hero.might.lifePoints shouldBe 0
        }
    }

    @Nested
    inner class HeroIsHealed {
        @Test
        fun `hero is healed`() {
            // given
            // when
            val hero = Hero(
                identity = withRandomAge(name = "MyHero"),
                abilities = Abilities(
                    strength = Ability(5),
                    agility = Ability(5),
                    perception = Ability(5)
                ),
                might = Might(level = 15, lifePoints = 5)
            )
                .run { this heals 5 }

            // then
            hero.might.level shouldBe 15
            hero.might.lifePoints shouldBe 10
        }

        @Test
        fun `hero cannot be heal more than might level`() {
            // given
            // when
            val hero = Hero(
                identity = withRandomAge(name = "MyHero"),
                abilities = Abilities(
                    strength = Ability(5),
                    agility = Ability(5),
                    perception = Ability(5)
                )
            )
                .run { this heals 5 }

            // then
            hero.might.level shouldBe 15
            hero.might.lifePoints shouldBe 15
        }
    }

    @Nested
    inner class HeroIsStuffed {
        @Test
        fun `hero wears an armor`() {
            // given
            val armor = Armor(5)

            // when
            val hero = Hero(
                identity = withRandomAge(name = "MyHero"),
                abilities = Abilities(
                    strength = Ability(5),
                    agility = Ability(5),
                    perception = Ability(5)
                )
            )
                .run { this wears armor }

            // then
            hero.might.level shouldBe 20
            hero.might.lifePoints shouldBe 20
        }

        @Test
        fun `hero removes an armor`() {
            // given
            val armor = Armor(5)

            // when
            val hero = Hero(
                identity = withRandomAge(name = "MyHero"),
                abilities = Abilities(
                    strength = Ability(5),
                    agility = Ability(5),
                    perception = Ability(5)
                ),
                armor = armor
            )
                .run { this removes armor }

            // then
            hero.might.level shouldBe 15
            hero.might.lifePoints shouldBe 15
        }

        @Test
        fun `hero don't die when removes an armor`() {
            // given
            val armor = Armor(15)

            // when
            val hero = Hero(
                identity = withRandomAge(name = "MyHero"),
                abilities = Abilities(
                    strength = Ability(5),
                    agility = Ability(5),
                    perception = Ability(5)
                ),
                armor = armor
            )
                .run { this takes 20 }
                .run { this removes armor }

            // then
            hero.might.level shouldBe 15
            hero.might.lifePoints shouldBe 1
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
                    identity = withRandomAge("MyHero"),
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
                    identity = withRandomAge("MyHero"),
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
                    identity = withRandomAge("MyHero"),
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