package com.laylataydragon.rustlemod.init;

import com.laylataydragon.rustlemod.entity.CalfEntity;
import com.laylataydragon.rustlemod.entity.CowEntity;
import com.laylataydragon.rustlemod.entity.LlamaEntity;
import com.laylataydragon.rustlemod.entity.RustleEntity;
import com.laylataydragon.rustlemod.entity.SheepEntity;

import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EntityAnimationFactory {
	@SubscribeEvent
	public static void onEntityTick(LivingEvent.LivingUpdateEvent event) {
		if (event != null && event.getEntity() != null) {
			if (event.getEntity() instanceof CalfEntity syncable) {
				String animation = syncable.getSyncedAnimation();
				if (!animation.equals("undefined")) {
					syncable.setAnimation("undefined");
					syncable.animationprocedure = animation;
				}
			}
			if (event.getEntity() instanceof CowEntity syncable) {
				String animation = syncable.getSyncedAnimation();
				if (!animation.equals("undefined")) {
					syncable.setAnimation("undefined");
					syncable.animationprocedure = animation;
				}
			}
			if (event.getEntity() instanceof SheepEntity syncable) {
				String animation = syncable.getSyncedAnimation();
				if (!animation.equals("undefined")) {
					syncable.setAnimation("undefined");
					syncable.animationprocedure = animation;
				}
			}
			if (event.getEntity() instanceof LlamaEntity syncable) {
				String animation = syncable.getSyncedAnimation();
				if (!animation.equals("undefined")) {
					syncable.setAnimation("undefined");
					syncable.animationprocedure = animation;
				}
			}
		}
	}
}
