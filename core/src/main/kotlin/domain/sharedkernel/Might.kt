package vokorpg.domain.sharedkernel

data class Might(
    val level: Int,
    val remaining: Int = level
) {
    companion object {
        val ZERO get() = Might(0)
        val Int.might get() = Might(this)
        val Int.mights get() = Might(this)
        fun total(vararg bonus: Int): Int = bonus.sum()
    }

    init {
        require(remaining <= level) { "Remaining might cannot be above might level. Remaining = $remaining." }
    }

    operator fun minus(other: Damages) = this.copy(remaining = remaining - other.value)
}