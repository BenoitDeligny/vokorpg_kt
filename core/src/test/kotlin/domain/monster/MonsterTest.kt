package domain.monster

import domain.Dice
import domain.GameMode
import domain.Might
import domain.Might.Companion.mightLevels
import domain.legend.Legend
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MonsterTest {
    @Test
    fun `Should takes damages`() {
        // Given
        // When
        val monster = Monster(
            name = "Boss",
            might = 15.mightLevels
        )
            .takes(5)

        // Then
        monster.remainingMight() shouldBe 10
    }

    private fun Monster.remainingMight() = might.remaining
}