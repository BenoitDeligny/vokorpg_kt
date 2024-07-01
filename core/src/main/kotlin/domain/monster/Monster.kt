package vokorpg.domain.monster

import vokorpg.domain.Gear
import vokorpg.domain.Might


data class Monster(
    val name: String,
    val might: Might,
    val gear: Gear
) {
}