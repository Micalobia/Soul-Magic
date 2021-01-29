package micalobia.soul_magic.enchants;

import micalobia.soul_magic.PlayerEntityExtension;
import micalobia.soul_magic.SoulMagic;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

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
