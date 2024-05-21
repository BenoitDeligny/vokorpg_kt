package vokorpg.domain.gear

import vokorpg.domain.gear.ItemIdentity.Companion.NONE
import vokorpg.domain.gear.Perk.*

sealed class Item(open val itemIdentity: ItemIdentity, val perk: Perk) {
    data class Armor(
        override val itemIdentity: ItemIdentity = NONE,
        val modifier: Int = 0
    ) : Item(itemIdentity = itemIdentity, perk = Might)

//    data class Belt(
//        override val itemIdentity: ItemIdentity = NONE,
//        val mightPerk: Perk = ZERO_MIGHT
//    ) : Item(itemIdentity = itemIdentity)
//
//    data class Boots(
//        override val itemIdentity: ItemIdentity = NONE,
//        val mightPerk: Perk = ZERO_MIGHT
//    ) : Item(itemIdentity = itemIdentity)
//
//    data class Cloak(
//        override val itemIdentity: ItemIdentity = NONE,
//        val mightPerk: Perk = ZERO_MIGHT
//    ) : Item(itemIdentity = itemIdentity)
//
//    data class Costume(
//        override val itemIdentity: ItemIdentity = NONE,
//        val healPerk: Perk = ZERO_HEAL,
//        val damagePerk: Perk = ZERO_DAMAGE,
//        val mightPerk: Perk = ZERO_MIGHT
//    ) : Item(itemIdentity = itemIdentity)
//
//    data class Gloves(
//        override val itemIdentity: ItemIdentity = NONE,
//        val mightPerk: Perk = ZERO_MIGHT
//    ) : Item(itemIdentity = itemIdentity)
//
//    data class Helmet(
//        override val itemIdentity: ItemIdentity = NONE,
//        val mightPerk: Perk = ZERO_MIGHT
//    ) : Item(itemIdentity = itemIdentity)
//
//    data class Mask(
//        override val itemIdentity: ItemIdentity = NONE,
//        val mightPerk: Perk = ZERO_MIGHT
//    ) : Item(itemIdentity = itemIdentity)
//
//    data class Necklace(
//        override val itemIdentity: ItemIdentity = NONE,
//        val healPerk: Perk = ZERO_HEAL,
//        val damagePerk: Perk = ZERO_DAMAGE,
//        val mightPerk: Perk = ZERO_MIGHT
//    ) : Item(itemIdentity = itemIdentity)
//
//    data class Ring(
//        override val itemIdentity: ItemIdentity = NONE,
//        val healPerk: Perk = ZERO_HEAL,
//        val damagePerk: Perk = ZERO_DAMAGE,
//        val mightPerk: Perk = ZERO_MIGHT
//    ) : Item(itemIdentity = itemIdentity)
//
//    data class Shield(
//        override val itemIdentity: ItemIdentity = NONE,
//        val healPerk: Perk = ZERO_HEAL,
//        val mightPerk: Perk = ZERO_MIGHT
//    ) : Item(itemIdentity = itemIdentity)
//
//    data class Weapon(
//        override val itemIdentity: ItemIdentity = NONE,
//        val damagePerk: Perk = ZERO_DAMAGE,
//        val mightPerk: Perk = ZERO_MIGHT
//    ) : Item(itemIdentity = itemIdentity)
//
//    data class Wristbands(
//        override val itemIdentity: ItemIdentity = NONE,
//        val mightPerk: Perk = ZERO_MIGHT
//    ) : Item(itemIdentity = itemIdentity)
//
//    data class Consumable(
//        override val itemIdentity: ItemIdentity = NONE,
//        val healPerk: Perk = ZERO_HEAL,
//        val damagePerk: Perk = ZERO_DAMAGE,
//        val mightPerk: Perk = ZERO_MIGHT
//    ) : Item(itemIdentity = itemIdentity)
}

data class ItemIdentity(
    val name: String = "", val description: String = "", val isRelic: Boolean = false
) {
    companion object {
        val NONE get() = ItemIdentity()
    }
}

// TODO - but very later: think about prefix and suffix style
sealed interface Perk {
    @JvmInline
    value class Might(val value: Int) : Perk {
        companion object : Perk {
            val ZERO_MIGHT get() = Might(0)
            val Int.might get() = Might(this)
            val Int.mights get() = Might(this)
        }
    }

    @JvmInline
    value class Damages(val value: Int) : Perk {
        companion object {
            val ZERO_DAMAGE get() = Damages(0)
            val Int.damage get() = Damages(this)
            val Int.damages get() = Damages(this)
        }
    }

    @JvmInline
    value class Heals(val value: Int) : Perk {
        companion object {
            val ZERO_HEAL get() = Heals(0)
            val Int.heal get() = Heals(this)
            val Int.heals get() = Heals(this)
        }
    }
}