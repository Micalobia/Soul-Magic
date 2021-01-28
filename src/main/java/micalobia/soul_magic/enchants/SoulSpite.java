package micalobia.soul_magic.enchants;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

public class SoulSpite extends Enchantment {
    public SoulSpite() {
        super(
                Rarity.VERY_RARE,
                EnchantmentTarget.ARMOR,
                new EquipmentSlot[]
                        {
                                EquipmentSlot.CHEST, EquipmentSlot.FEET, EquipmentSlot.HEAD, EquipmentSlot.LEGS
                        }
        );
    }

    @Override
    public int getMinPower(int level)
    {
        return 1;
    }

    @Override
    public int getMaxLevel()
    {
        return 3;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level)
    {
        // TODO: Do Lifesteal stuff
    }
}
