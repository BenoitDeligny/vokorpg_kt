package domain

data class Might(
    val level: Int,
    val remaining: Int = level
) {
    companion object {
        val Int.mightLevels get() = Might(this)
        fun total(vararg bonus: Int): Might = bonus.sum().mightLevels
    }

    init {
        require(remaining <= level) { "Remaining might cannot be above might level. Remaining = $remaining." }
    }

    operator fun minus(other: Damages) = this.copy(remaining = remaining - other.value)
}

@JvmInline
value class Damages(val value: Int) {
    companion object {
        val Int.damages get() = Damages(this)
    }
}