package domain

data class Might(
    val level: Int,
    val remaining: Int = level
) {
    companion object {
        val Int.might get() = Might(this)
        // TODO: is it needed ?
        fun total(vararg abilities: Int): Might = abilities.sum().might

        // TODO: to have in mind
//        fun total(vararg abilities: Int): Might = abilities.sum().might()
//        fun Int.might() = Might(this)
    }

    init {
        require(remaining <= level) { "Remaining might cannot be above might level. Remaining = $remaining." }
    }

    operator fun minus(other: Damages) = this.copy(remaining = remaining - other.value)
}

// TODO: move out this class ?
@JvmInline
value class Damages(val value: Int) {
    companion object {
        val Int.damages get() = Damages(this)
    }
}