package micalobia.soul_magic;

import micalobia.soul_magic.enchants.SoulCollector;
import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoulMagic implements ModInitializer {

	private static Enchantment SOUL_COLLECTOR = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("soul_magic","soul_collector"),
			new SoulCollector()
	);

	@Override
	public void onInitialize() {
	}
}
