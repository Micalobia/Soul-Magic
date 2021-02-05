package micalobia.soul_magic.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

public class SoulLeach extends Enchantment
{
    public SoulLeach()
    {
        super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
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
