package micalobia.soul_magic.enchants;

import micalobia.soul_magic.PlayerEntityExtension;
import micalobia.soul_magic.SoulMagic;
import net.minecraft.enchantment.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;

import java.util.Map;
import java.util.Random;

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
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        if (shouldDamageAttacker(level, user.getRandom())) {
            if (attacker != null) {
                attacker.damage(DamageSource.thorns(user), (float)getDamageAmount(level));
                ((PlayerEntityExtension)(Object)user).takeSouls(level);
            }
        }
    }

    private int getDamageAmount(int level) {
        return level + 1;
    }

    private boolean shouldDamageAttacker(int level, Random rng) { return rng.nextFloat() < level * .15F; }

}
