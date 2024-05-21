package vokorpg.domain.gear

import vokorpg.domain.gear.Item.*

data class Gear(
    val armor: Armor,
//    val belt: Belt,
//    val boots: Boots,
//    val cloak: Cloak,
//    val consumables: List<Consumable>,
//    val costume: Costume,
//    val gloves: Gloves,
//    val helmet: Helmet,
//    val mask: Mask,
//    val necklace: Necklace,
//    val primaryWeapon: Weapon,
//    val secondaryWeapon: Weapon,
//    val leftRing: Ring,
//    val rightRing: Ring,
//    val shield: Shield,
//    val wristbands: Wristbands,
) {
    companion object {
        fun standardGear() = Gear(
            armor = Armor(),
//            belt = Belt(),
//            boots = Boots(),
//            cloak = Cloak(),
//            consumables = listOf(),
//            costume = Costume(),
//            gloves = Gloves(),
//            helmet = Helmet(),
//            mask = Mask(),
//            necklace = Necklace(),
//            primaryWeapon = Weapon(),
//            secondaryWeapon = Weapon(),
//            leftRing = Ring(),
//            rightRing = Ring(),
//            shield = Shield(),
//            wristbands = Wristbands()
        )
    }

    fun mightBonus() = armor.modifier

    // TODO: refacto this PLEASE !!!
    // Option 1: Reflection
    // Option 2: Take each item separately
    // Option 3: Use a Collection in Gear and then iterate
    // Option 4: Something with interface or sealed ? To make sure, if new item appear, we can't forget it
//    fun totalMightBonus() = armor.mightPerk.value +
//            belt.mightPerk.value +
//            boots.mightPerk.value +
//            cloak.mightPerk.value +
//            costume.mightPerk.value +
//            gloves.mightPerk.value +
//            helmet.mightPerk.value +
//            mask.mightPerk.value +
//            necklace.mightPerk.value +
//            primaryWeapon.mightPerk.value +
//            secondaryWeapon.mightPerk.value +
//            leftRing.mightPerk.value +
//            rightRing.mightPerk.value +
//            shield.mightPerk.value +
//            wristbands.mightPerk.value
//
//    fun totalDamagesBonus() = costume.damagePerk.value +
//            necklace.damagePerk.value +
//            leftRing.damagePerk.value +
//            rightRing.damagePerk.value +
//            primaryWeapon.damagePerk.value +
//            secondaryWeapon.damagePerk.value
//
//    fun totalHealBonus() = costume.healPerk.value +
//            necklace.healPerk.value +
//            leftRing.healPerk.value +
//            rightRing.healPerk.value +
//            shield.healPerk.value
}