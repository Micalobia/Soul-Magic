package micalobia.soul_magic.mixin;

import micalobia.soul_magic.PlayerEntityExtension;
import micalobia.soul_magic.SoulMagic;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerEntityExtension {

    @Shadow
    @Final
    public PlayerInventory inventory;
    private int soulCount;
    private float incomingSouls;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    public int getSoulCount() {
        return soulCount;
    }

    public void setSoulCount(int value) {
        soulCount = value;
    }

    public void addSouls(int value) {
        soulCount += value;
    }

    public void addSouls() {
        soulCount += (int) Math.ceil(incomingSouls);
        incomingSouls = 0;
    }

    public void addIncomingSouls(float value) {
        incomingSouls += value;
    }

    public void scaleIncomingSouls(float value) {
        incomingSouls = (float) Math.ceil(incomingSouls * value);
    }

    public void takeSouls(int value) {
        soulCount -= value;
        if (soulCount < 0) soulCount = 0;
    }

    private void calculateSouls(LivingEntity victim) {
        calculateSoulCollector();
        calculateSoulReaper();
        soulCount += (int) Math.ceil(incomingSouls);
        incomingSouls = 0;
    }

    private void calculateSoulCollector() {
        int chance = 0;

        for (ItemStack armor : inventory.armor)
            chance += EnchantmentHelper.getLevel(SoulMagic.SOUL_COLLECTOR, armor);
        if (this.getRandom().nextInt(4) < chance) this.addIncomingSouls(1);
    }

    private void calculateSoulReaper() {
        int lvl = EnchantmentHelper.getLevel(SoulMagic.SOUL_REAPER, inventory.getMainHandStack());
        if (lvl <= 0) return;
        int scale = getRandom().nextInt(lvl + 2) % (lvl + 1) + 1;
        scaleIncomingSouls(scale);
    }

    @Inject(method = "writeCustomDataToTag", at = @At("RETURN"))
    public void writeCustomDataToTag(CompoundTag tag, CallbackInfo info) {
        tag.putInt("Souls", getSoulCount());
    }

    @Inject(method = "readCustomDataFromTag", at = @At("RETURN"))
    public void readCustomDataFromTag(CompoundTag tag, CallbackInfo info) {
        setSoulCount(tag.getInt("Souls"));
    }

    @Inject(method = "onKilledOther", at = @At("HEAD"))
    public void onKilledOther(ServerWorld serverWorld, LivingEntity target, CallbackInfo info) {
        calculateSouls(target);
    }
}
