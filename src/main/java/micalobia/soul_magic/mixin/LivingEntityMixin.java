package micalobia.soul_magic.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    private int soulDropCount;

    public int getSoulDropCount() { return soulDropCount; }

    public void setSoulDropCount(int value) { soulDropCount = value; }
}
