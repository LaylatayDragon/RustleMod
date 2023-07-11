package net.mcreator.rustle.init;

import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.mcreator.rustle.configuration.RustleConfigConfiguration;
import net.mcreator.rustle.RustleMod;

@Mod.EventBusSubscriber(modid = RustleMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RustleModConfigs {
	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		event.enqueueWork(() -> {
			ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RustleConfigConfiguration.SPEC, "RustleConfig.toml");
		});
	}
}
