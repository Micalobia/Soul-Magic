package micalobia.soul_magic.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.registry.Registry;

public class Blocks {
    public static final Block GLOW_IRON;
    static {
        GLOW_IRON = Registry.register(Registry.BLOCK, "soul_magic:glow_iron", new Block(FabricBlockSettings.of(Material.METAL).strength(4.0f).luminance(15)));
    };
}
