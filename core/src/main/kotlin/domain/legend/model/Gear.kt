package vokorpg.domain.legend.model

import vokorpg.domain.Item
import vokorpg.domain.Item.Factory.basicArmor
import vokorpg.domain.Item.Factory.basicBelt
import vokorpg.domain.Item.Factory.basicBoots
import vokorpg.domain.Item.Factory.basicCloak
import vokorpg.domain.Item.Factory.basicCostume
import vokorpg.domain.Item.Factory.basicGloves
import vokorpg.domain.Item.Factory.basicHelmet
import vokorpg.domain.Item.Factory.basicMask
import vokorpg.domain.Item.Factory.basicNecklace
import vokorpg.domain.Item.Factory.basicRing
import vokorpg.domain.Item.Factory.basicShield
import vokorpg.domain.Item.Factory.basicWeapon
import vokorpg.domain.Item.Factory.basicWristbands
import vokorpg.domain.Type.*

data class Gear(
    val helmet: Item,
    val mask: Item,
    val necklace: Item,
    val cloak: Item,
    val costume: Item,
    val armor: Item,
    val shield: Item,
    val primaryWeapon: Item,
    val secondaryWeapon: Item,
    val wristbands: Item,
    val gloves: Item,
    val ring: Item,
    val belt: Item,
    val boots: Item,
    val consumables: MutableList<Item>,
) {
    companion object Factory {
        fun standardGear(): Gear = Gear(
            helmet = basicHelmet(),
            mask = basicMask(),
            necklace = basicNecklace(),
            cloak = basicCloak(),
            costume = basicCostume(),
            armor = basicArmor(),
            shield = basicShield(),
            primaryWeapon = basicWeapon(),
            secondaryWeapon = basicWeapon(),
            wristbands = basicWristbands(),
            gloves = basicGloves(),
            ring = basicRing(),
            belt = basicBelt(),
            boots = basicBoots(),
            consumables = mutableListOf()
        )
    }

    init {
        require(helmet.type == HELMET) { "You should have a Helmet on your head." }
        require(mask.type == MASK) { "You should have a Mask on your face." }
        require(necklace.type == NECKLACE) { "You should have a Necklace on your neck." }
        require(cloak.type == CLOAK) { "You should have a Cloak on your back." }
        require(costume.type == COSTUME) { "You should have a Costume on your body." }
        require(armor.type == ARMOR) { "YYou should have a Armor on your chest." }
        require(shield.type == SHIELD) { "You should have a Shield on your second hand." }
        require(primaryWeapon.type == WEAPON) { "You should have a Weapon on your primary hand." }
        require(secondaryWeapon.type == WEAPON) { "You should have a Weapon on your backpack" }
        require(wristbands.type == WRISTBANDS) { "You should have Wristbands on your wrist." }
        require(gloves.type == GLOVES) { "You should have gloves on your hands." }
        require(ring.type == RING) { "You should have a Ring on your finger." }
        require(belt.type == BELT) { "You should have a Belt on your body." }
        require(boots.type == BOOTS) { "You should have Boots on your feet." }
        consumables.forEach { require(it.type == CONSUMABLE) { { "You should have a consumable in your backpack." } } }
    }
}


