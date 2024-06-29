package vokorpg.domain

import vokorpg.domain.Item.*

data class Gear(
    val armor: Armor = Armor.NONE,
    val belt: Belt = Belt.NONE,
    val boots: Boots = Boots.NONE,
    val cloak: Cloak = Cloak.NONE,
    val consumables: List<Consumable> = Consumable.NONE,
    val costume: Costume = Costume.NONE,
    val gloves: Gloves = Gloves.NONE,
    val helmet: Helmet = Helmet.NONE,
    val mask: Mask = Mask.NONE,
    val necklace: Necklace = Necklace.NONE,
    val weapon: Weapon = Weapon.NONE,
    val ring: Ring = Ring.NONE,
    val shield: Shield = Shield.NONE,
    val wristbands: Wristbands = Wristbands.NONE,
    // TODO: how to add multiple similar items ?
//    val rightRing: Ring = Ring.NONE,
//    val secondaryWeapon: Weapon = Weapon.NONE,
) {
    companion object {
        val NONE = Gear()

        // TODO: naming
        val NORMAL_MODE_STARTING_PACK = Gear(
            armor = Armor(1),
            weapon = Weapon(1)
        )

        // TODO: naming
        val EASY_MODE_STARTING_PACK = Gear(
            armor = Armor(1),
            boots = Boots(1),
            weapon = Weapon(1)
        )
    }

    // TODO: how to make modifiers works ?
    // Ideas:
    // 1: Make a Modifier -> value + type
    // 2: Filtering it directly in methods totalMightBonus and so on
    fun totalMightBonus() = armor.modifier +
            belt.modifier +
            boots.modifier +
            cloak.modifier +
            costume.modifier +
            gloves.modifier +
            helmet.modifier +
            mask.modifier +
            necklace.modifier +
            weapon.modifier +
            ring.modifier +
            shield.modifier +
            wristbands.modifier
//            rightRing.mightBonus +
//            secondaryWeapon.mightBonus +

    fun removes(item: Item): Gear = when (item) {
        is Armor -> copy(armor = Armor.NONE)
        is Belt -> copy(belt = Belt.NONE)
        is Boots -> copy(boots = Boots.NONE)
        is Cloak -> copy(cloak = Cloak.NONE)
        is Consumable -> copy(consumables = Consumable.NONE)
        is Costume -> copy(costume = Costume.NONE)
        is Gloves -> copy(gloves = Gloves.NONE)
        is Helmet -> copy(helmet = Helmet.NONE)
        is Mask -> copy(mask = Mask.NONE)
        is Necklace -> copy(necklace = Necklace.NONE)
        is Ring -> copy(ring = Ring.NONE)
        is Shield -> copy(shield = Shield.NONE)
        is Weapon -> copy(weapon = Weapon.NONE)
        is Wristbands -> copy(wristbands = Wristbands.NONE)
//        is Ring -> copy(rightRing = Ring.NONE)
//        is Weapon -> copy(secondaryWeapon = Weapon.NONE)
    }

    fun wears(item: Item): Gear = when (item) {
        is Armor -> copy(armor = item)
        is Belt -> copy(belt = item)
        is Boots -> copy(boots = item)
        is Cloak -> copy(cloak = item)
        is Consumable -> copy(consumables = Consumable.NONE)
        is Costume -> copy(costume = item)
        is Gloves -> copy(gloves = item)
        is Helmet -> copy(helmet = item)
        is Mask -> copy(mask = item)
        is Necklace -> copy(necklace = item)
        is Ring -> copy(ring = item)
        is Shield -> copy(shield = item)
        is Weapon -> copy(weapon = item)
        is Wristbands -> copy(wristbands = item)
//        is Ring -> copy(rightRing = Ring.NONE)
//        is Weapon -> copy(secondaryWeapon = Weapon.NONE)
    }
}

sealed interface Item {
    // TODO: naming
    val modifier: Int

    @JvmInline
    value class Armor(override val modifier: Int) : Item {
        companion object {
            val NONE = Armor(0)
        }
    }

    @JvmInline
    value class Belt(override val modifier: Int) : Item {
        companion object {
            val NONE = Belt(0)
        }
    }

    @JvmInline
    value class Boots(override val modifier: Int) : Item {
        companion object {
            val NONE = Boots(0)
        }
    }

    @JvmInline
    value class Cloak(override val modifier: Int) : Item {
        companion object {
            val NONE = Cloak(0)
        }
    }

    @JvmInline
    value class Consumable(override val modifier: Int) : Item {
        companion object {
            val NONE = emptyList<Consumable>()
        }
    }

    @JvmInline
    value class Costume(override val modifier: Int) : Item {
        companion object {
            val NONE = Costume(0)
        }
    }

    @JvmInline
    value class Gloves(override val modifier: Int) : Item {
        companion object {
            val NONE = Gloves(0)
        }
    }

    @JvmInline
    value class Helmet(override val modifier: Int) : Item {
        companion object {
            val NONE = Helmet(0)
        }
    }

    @JvmInline
    value class Mask(override val modifier: Int) : Item {
        companion object {
            val NONE = Mask(0)
        }
    }

    @JvmInline
    value class Necklace(override val modifier: Int) : Item {
        companion object {
            val NONE = Necklace(0)
        }
    }

    @JvmInline
    value class Weapon(override val modifier: Int) : Item {
        companion object {
            val NONE = Weapon(0)
        }
    }

    @JvmInline
    value class Ring(override val modifier: Int) : Item {
        companion object {
            val NONE = Ring(0)
        }
    }

    @JvmInline
    value class Shield(override val modifier: Int) : Item {
        companion object {
            val NONE = Shield(0)
        }
    }

    @JvmInline
    value class Wristbands(override val modifier: Int) : Item {
        companion object {
            val NONE = Wristbands(0)
        }
    }
}