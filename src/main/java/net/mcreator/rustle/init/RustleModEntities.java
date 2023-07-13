
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.rustle.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import net.mcreator.rustle.entity.SheepEntity;
import net.mcreator.rustle.entity.CowEntity;
import net.mcreator.rustle.entity.CalfEntity;
import net.mcreator.rustle.RustleMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RustleModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITIES, RustleMod.MODID);
	public static final RegistryObject<EntityType<CowEntity>> COW = register("cow",
			EntityType.Builder.<CowEntity>of(CowEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(3).setCustomClientFactory(CowEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<SheepEntity>> SHEEP = register("sheep",
			EntityType.Builder.<SheepEntity>of(SheepEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(3).setCustomClientFactory(SheepEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<CalfEntity>> CALF = register("calf",
			EntityType.Builder.<CalfEntity>of(CalfEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(3).setCustomClientFactory(CalfEntity::new)

					.sized(0.6f, 1.8f));

	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			CowEntity.init();
			SheepEntity.init();
			CalfEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(COW.get(), CowEntity.createAttributes().build());
		event.put(SHEEP.get(), SheepEntity.createAttributes().build());
		event.put(CALF.get(), CalfEntity.createAttributes().build());
	}
}
