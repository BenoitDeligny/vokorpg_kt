package domain.monster

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import vokorpg.domain.sharedkernel.Might.Companion.mights

class MonsterTest {
    @Test
    fun `Should takes damages`() {
        // Given
        // When
        val monster = Monster(
            name = "Boss",
            might = 15.mights
        )
            .takes(5)

        // Then
        monster.remainingMight() shouldBe 10
    }

    private fun Monster.remainingMight() = might.remaining
}