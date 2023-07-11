
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.rustle.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.mcreator.rustle.client.renderer.SheepRenderer;
import net.mcreator.rustle.client.renderer.CowRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RustleModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(RustleModEntities.COW.get(), CowRenderer::new);
		event.registerEntityRenderer(RustleModEntities.SHEEP.get(), SheepRenderer::new);
	}
}
