package micalobia.soul_magic.mixin;

import micalobia.soul_magic.LivingEntityExtension;
import micalobia.soul_magic.SoulMagic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements LivingEntityExtension {

    @Shadow public abstract Optional<BlockPos> getClimbingPos();

    private int soulDropCount;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    public int getSoulDropCount() {
        return 0;
    }

    public void setSoulDropCount(int value) { soulDropCount = value; }

    @Inject(method = "writeCustomDataToTag", at = @At("RETURN"))
    public void writeCustomDataToTag(CompoundTag tag, CallbackInfo info) {
        tag.putInt("SoulDropCount", this.getSoulDropCount());
    }

    @Inject(method = "readCustomDataFromTag", at = @At("RETURN"))
    public void readCustomDataFromTag(CompoundTag tag, CallbackInfo info) {
        setSoulDropCount(tag.getInt("SoulDropCount"));
    }
}
