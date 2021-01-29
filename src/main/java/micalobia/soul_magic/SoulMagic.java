package micalobia.soul_magic;

import micalobia.soul_magic.enchants.Reaper;
import micalobia.soul_magic.enchants.SoulCollector;
import micalobia.soul_magic.enchants.SoulSpite;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.MessageType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Objects;
import java.util.Random;

public class SoulMagic implements ModInitializer {

	public static Enchantment SOUL_COLLECTOR = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("soul_magic","soul_collector"),
			new SoulCollector()
	);

	public static Enchantment SOUL_REAPER = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("soul_magic", "reaper"),
			new Reaper()
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
