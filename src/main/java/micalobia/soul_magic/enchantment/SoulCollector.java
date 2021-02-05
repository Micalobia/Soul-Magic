package micalobia.soul_magic.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class SoulCollector extends Enchantment {
    public SoulCollector()
    {
        super(
                Rarity.COMMON,
                EnchantmentTarget.ARMOR,
                new EquipmentSlot[]
                {
                    EquipmentSlot.CHEST, EquipmentSlot.FEET, EquipmentSlot.HEAD, EquipmentSlot.LEGS
                }
            );
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
