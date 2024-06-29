package vokorpg.domain

// TODO: naming -> difficulty ?
enum class GameMode(val abilityModifier: Int) {
    NORMAL(1),
    EASY(4);
}