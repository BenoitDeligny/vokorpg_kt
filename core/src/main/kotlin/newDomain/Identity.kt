package vokorpg.newDomain

data class Identity(
    private val name: String,
    private val age: Age,
) {
    companion object {
        fun withRandomAge(name: String) = Identity(name = name, age = Age.random)
    }

    init {
        require(name.all { it.isLetter() }) { "Name must contains only letters." }
        require(age in 15..20) { "Age must be between 15 and 20. Age = $age." }
    }

    operator fun IntRange.contains(age: Age): Boolean {
        return age.value in this
    }
}

@JvmInline
value class Age(val value: Int) {
    companion object {
        private val dice = Dice
        val random = Age(dice.roll(from = 15, until = 20))
    }
}