
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.laylataydragon.rustlemod.init;

import com.laylataydragon.rustlemod.Rustle;
import com.laylataydragon.rustlemod.client.renderer.*;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(modid = Rustle.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RustleModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
			event.registerEntityRenderer(RustleModEntities.CALF.get(), CalfRenderer::new);
			event.registerEntityRenderer(RustleModEntities.COW.get(), CowRenderer::new);
			event.registerEntityRenderer(RustleModEntities.SHEEP.get(), SheepRenderer::new);
			event.registerEntityRenderer(RustleModEntities.LLAMA.get(), LlamaRenderer::new);
	}
}
