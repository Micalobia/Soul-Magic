package micalobia.soul_magic.enchants;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class Soulbound  extends Enchantment {

    public Soulbound() {
        super(
                Rarity.VERY_RARE,
                EnchantmentTarget.BREAKABLE,
                new EquipmentSlot[] {
                    EquipmentSlot.FEET,
                    EquipmentSlot.MAINHAND,
                    EquipmentSlot.CHEST,
                    EquipmentSlot.HEAD,
                    EquipmentSlot.LEGS,
                    EquipmentSlot.OFFHAND
                }
            );
    }


}
