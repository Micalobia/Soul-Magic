package micalobia.soul_magic;

import micalobia.soul_magic.enchants.SoulReaper;
import micalobia.soul_magic.enchants.SoulCollector;
import micalobia.soul_magic.enchants.SoulSpite;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.network.MessageType;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoulMagic implements ModInitializer {

	public static Enchantment SOUL_COLLECTOR = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("soul_magic","soul_collector"),
			new SoulCollector()
	);

	public static Enchantment SOUL_REAPER = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("soul_magic", "reaper"),
			new SoulReaper()
	);

	private static Enchantment SOUL_SPITE = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("soul_magic", "soul_spite"),
			new SoulSpite()
	);

	public static void DebugMessage(String message) {
		MinecraftClient mc = MinecraftClient.getInstance();
		mc.inGameHud.addChatMessage(MessageType.CHAT, new LiteralText(message), mc.player.getUuid());
	}

	@Override
	public void onInitialize() {
		SoulCounts.addVanilla();
	}
}
