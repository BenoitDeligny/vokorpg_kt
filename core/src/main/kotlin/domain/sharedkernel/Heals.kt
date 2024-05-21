package vokorpg.domain.sharedkernel


@JvmInline
value class Heals(val value: Int) {
    companion object {
        val ZERO_HEAL get() = Heals(0)
        val Int.heal get() = Heals(this)
        val Int.heals get() = Heals(this)
    }
}