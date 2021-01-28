package micalobia.soul_magic.mixin;

import micalobia.soul_magic.PlayerEntityExtension;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerEntityExtension {

    private int soulCount;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    public int getSoulCount() {
        return soulCount;
    }

    public void addSouls(int value) {
        soulCount += value;
    }

    public void takeSouls(int value) {
        soulCount -= value;
        if (soulCount < 0) soulCount = 0;
    }
}
