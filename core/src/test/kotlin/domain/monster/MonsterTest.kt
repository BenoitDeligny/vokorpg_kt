package domain.monster

import domain.Dice
import domain.GameMode
import domain.Might
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
            might = Might(level = 15, remaining = 15)
        )
            .takes(5)

        // Then
        monster.remainingMight() shouldBe 10
    }

    private fun Monster.remainingMight() = might.remaining
}