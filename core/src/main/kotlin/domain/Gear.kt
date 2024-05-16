package vokorpg.domain

import vokorpg.domain.ItemIdentity.Companion.NONE

data class Gear(
    val armor: Armor,
    val belt: Belt,
    val boots: Boots,
    val cloak: Cloak,
    val consumables: List<Consumable>,
    val costume: Costume,
    val gloves: Gloves,
    val helmet: Helmet,
    val mask: Mask,
    val necklace: Necklace,
    val primaryWeapon: Weapon,
    val secondaryWeapon: Weapon,
    val leftRing: Ring,
    val rightRing: Ring,
    val shield: Shield,
    val wristbands: Wristbands,
) {
    companion object {
        fun standardGear() = Gear(
            armor = Armor(),
            belt = Belt(),
            boots = Boots(),
            cloak = Cloak(),
            consumables = listOf(),
            costume = Costume(),
            gloves = Gloves(),
            helmet = Helmet(),
            mask = Mask(),
            necklace = Necklace(),
            primaryWeapon = Weapon(),
            secondaryWeapon = Weapon(),
            leftRing = Ring(),
            rightRing = Ring(),
            shield = Shield(),
            wristbands = Wristbands()
        )
    }

    // TODO: add total might bonus
        // Option 1: Reflection
        // Option 2: Take each item separately
        // Option 3: Use a Collection in Gear and then iterate
        // Option 4: Something with interface or sealed ? To make sure, if new item appear, we can't forget it
    // TODO: add total damages bonus
    // TODO: add total heal bonus
}

data class Armor(
    val itemIdentity: ItemIdentity = NONE,
    val mightPerk: MightPerk = MightPerk.ZERO
)

data class Belt(
    val itemIdentity: ItemIdentity = NONE,
    val mightPerk: MightPerk = MightPerk.ZERO
)

data class Boots(
    val itemIdentity: ItemIdentity = NONE,
    val mightPerk: MightPerk = MightPerk.ZERO
)

data class Cloak(
    val itemIdentity: ItemIdentity = NONE,
    val mightPerk: MightPerk = MightPerk.ZERO
)

data class Costume(
    val itemIdentity: ItemIdentity = NONE,
    val healPerk: HealPerk = HealPerk.ZERO,
    val damagePerk: DamagePerk = DamagePerk.ZERO,
    val mightPerk: MightPerk = MightPerk.ZERO
)

data class Gloves(
    val itemIdentity: ItemIdentity = NONE,
    val mightPerk: MightPerk = MightPerk.ZERO
)

data class Helmet(
    val itemIdentity: ItemIdentity = NONE,
    val mightPerk: MightPerk = MightPerk.ZERO
)

data class Mask(
    val itemIdentity: ItemIdentity = NONE,
    val mightPerk: MightPerk = MightPerk.ZERO
)

data class Necklace(
    val itemIdentity: ItemIdentity = NONE,
    val healPerk: HealPerk = HealPerk.ZERO,
    val damagePerk: DamagePerk = DamagePerk.ZERO,
    val mightPerk: MightPerk = MightPerk.ZERO
)

data class Ring(
    val itemIdentity: ItemIdentity = NONE,
    val healPerk: HealPerk = HealPerk.ZERO,
    val damagePerk: DamagePerk = DamagePerk.ZERO,
    val mightPerk: MightPerk = MightPerk.ZERO
)

data class Shield(
    val itemIdentity: ItemIdentity = NONE,
    val healPerk: HealPerk = HealPerk.ZERO,
    val mightPerk: MightPerk = MightPerk.ZERO
)

data class Weapon(
    val itemIdentity: ItemIdentity = NONE,
    val damagePerk: DamagePerk = DamagePerk.ZERO,
    val mightPerk: MightPerk = MightPerk.ZERO
)

data class Wristbands(
    val itemIdentity: ItemIdentity = NONE,
    val mightPerk: MightPerk = MightPerk.ZERO
)

data class Consumable(
    val itemIdentity: ItemIdentity = NONE,
    val healPerk: HealPerk = HealPerk.ZERO,
    val damagePerk: DamagePerk = DamagePerk.ZERO,
    val mightPerk: MightPerk = MightPerk.ZERO
)

data class ItemIdentity(
    val name: String = "",
    val description: String = "",
    val isRelic: Boolean = false
) {
    companion object {
        val NONE get() = ItemIdentity()
    }
}

// TODO - but very later: think about prefix and suffix style
@JvmInline
value class DamagePerk(val value: Int) {
    companion object {
        val ZERO get() = DamagePerk(0)
    }
}

@JvmInline
value class HealPerk(val value: Int) {
    companion object {
        val ZERO get() = HealPerk(0)
    }
}

@JvmInline
value class MightPerk(val value: Int) {
    companion object {
        val ZERO get() = MightPerk(0)
    }
}
