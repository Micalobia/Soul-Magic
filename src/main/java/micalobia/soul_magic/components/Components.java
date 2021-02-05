package micalobia.soul_magic.components;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import micalobia.soul_magic.PlayerEntityExtension;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class Components implements EntityComponentInitializer {
    public static final ComponentKey<SoulComponent> SOULS =
            ComponentRegistry.getOrCreate(new Identifier("soul_magic","souls"), SoulComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(PlayerEntity.class, SOULS, SoulComponent::new);
    }

    public static int getSoulCount(PlayerEntityExtension player) {
        return SOULS.get(player).getSouls();
    }

    public static void setSoulCount(PlayerEntityExtension player, int value) {
        SOULS.get(player).setSouls(value);
    }
}
