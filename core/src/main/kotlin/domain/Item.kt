package vokorpg.domain

import vokorpg.domain.Item.Trait.DAMAGE
import vokorpg.domain.Type.*

data class Item(
    val name: String,
    val type: Type,
    val perk: Perk? = null,
    val description: String = "",
    val isRelic: Boolean = false
) {
    companion object Factory {
        fun basicHelmet(): Item = Item("Helmet", HELMET)
        fun basicMask(): Item = Item("Mask", MASK)
        fun basicNecklace(): Item = Item("Necklace", NECKLACE)
        fun basicCloak(): Item = Item("Cloak", CLOAK)
        fun basicCostume(): Item = Item("Costume", COSTUME)
        fun basicArmor(): Item = Item("Armor", ARMOR)
        fun basicShield(): Item = Item("Shield", SHIELD)
        fun basicWeapon(): Item = Item("Weapon", WEAPON)
        fun basicWristbands(): Item = Item("Wristbands", WRISTBANDS)
        fun basicGloves(): Item = Item("Gloves", GLOVES)
        fun basicRing(): Item = Item("Ring", RING)
        fun basicBelt(): Item = Item("Belt", BELT)
        fun basicBoots(): Item = Item("Boots", BOOTS)
    }

    data class Perk(
        val trait: Trait,
        val modifier: Int
    )

    enum class Trait {
        DAMAGE,
        HEAL,
        MIGHT
    }

    // TODO: think about it
//    fun bonusDamages(): Int = when(perk?.trait == DAMAGE) {
//        true -> perk!!.modifier
//        false -> 0
//    }
}

enum class Type {
    ARMOR,
    BELT,
    BOOTS,
    CLOAK,
    COSTUME,
    GLOVES,
    HELMET,
    MASK,
    NECKLACE,
    RING,
    SHIELD,
    WEAPON,
    WRISTBANDS,
    CONSUMABLE
}