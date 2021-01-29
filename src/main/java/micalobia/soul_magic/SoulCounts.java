package micalobia.soul_magic;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.VillagerEntity;

import java.util.HashMap;
import java.util.Map;

public final class SoulCounts {
    private static Map<String, Integer> soulCounts = new HashMap<>();

    public static <T extends Entity> void addEntity(Class<T> klass, int soulCount) {
        soulCounts.put(klass.getSimpleName(), soulCount);
    }

    public static void addVanilla() {
        addEntity(SlimeEntity.class, 5);
        addEntity(ZombieEntity.class, 20);
        addEntity(VillagerEntity.class, 50);
    }

    public static int getSoulDrop(Entity target) {
        return soulCounts.getOrDefault(target.getClass().getSimpleName(), 0);
    }
}
