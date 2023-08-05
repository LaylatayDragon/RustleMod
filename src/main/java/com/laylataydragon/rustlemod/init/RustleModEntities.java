
 
package com.laylataydragon.rustlemod.init;



import com.laylataydragon.rustlemod.Rustle;
import com.laylataydragon.rustlemod.entity.CalfEntity;
import com.laylataydragon.rustlemod.entity.CowEntity;
import com.laylataydragon.rustlemod.entity.LlamaEntity;
import com.laylataydragon.rustlemod.entity.SheepEntity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.resources.ResourceLocation;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RustleModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITIES, Rustle.MODID);
	public static final RegistryObject<EntityType<CalfEntity>> CALF = register("calf",
			EntityType.Builder.<CalfEntity>of(CalfEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CalfEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<CowEntity>> COW = register("cow",
			EntityType.Builder.<CowEntity>of(CowEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CowEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<SheepEntity>> SHEEP = register("sheep",
			EntityType.Builder.<SheepEntity>of(SheepEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(SheepEntity::new)

					.sized(0.6f, 1.8f));
	public static final RegistryObject<EntityType<LlamaEntity>> LLAMA = register("llama",
			EntityType.Builder.<LlamaEntity>of(LlamaEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(LlamaEntity::new)

					.sized(0.6f, 1.8f));

	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			CalfEntity.init();
			CowEntity.init();
			SheepEntity.init();
			LlamaEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(CALF.get(), CalfEntity.createAttributes().build());
		event.put(COW.get(), CowEntity.createAttributes().build());
		event.put(SHEEP.get(), SheepEntity.createAttributes().build());
		event.put(LLAMA.get(), LlamaEntity.createAttributes().build());
	}

}
