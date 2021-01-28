package micalobia.soul_magic.enchants;

import micalobia.soul_magic.PlayerEntityExtension;
import micalobia.soul_magic.mixin.PlayerEntityMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.MessageType;
import net.minecraft.text.LiteralText;

public class SoulCollector extends Enchantment {
    public SoulCollector()
    {
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
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(user instanceof PlayerEntity && target instanceof LivingEntity) {
            LivingEntity victim = (LivingEntity)target;
            PlayerEntityExtension player = (PlayerEntityExtension) user;
            player.addSouls(10);
            MinecraftClient mc = MinecraftClient.getInstance();
            mc.inGameHud.addChatMessage(MessageType.CHAT, new LiteralText("Collected 10 souls, you have: " + player.getSoulCount()), mc.player.getUuid());
            if (victim.getHealth() <= 0)
                mc.inGameHud.addChatMessage(MessageType.CHAT, new LiteralText("GAMER"), mc.player.getUuid());

        }
    }
}
