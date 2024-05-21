package vokorpg.domain.sharedkernel

@JvmInline
value class Damages(val value: Int) {
    companion object {
        val ZERO_DAMAGE get() = Damages(0)
        val Int.damage get() = Damages(this)
        val Int.damages get() = Damages(this)
    }
}