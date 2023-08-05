package com.laylataydragon.rustlemod.init;


import com.laylataydragon.rustlemod.configuration.RustleConfigConfiguration;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.config.*;

@Mod.EventBusSubscriber(modid = "rustle", bus = Mod.EventBusSubscriber.Bus.MOD)
public class RustleModConfigs {
	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		event.enqueueWork(() -> {
			ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RustleConfigConfiguration.SPEC, "RustleConfig.toml");
		});
	}
}
