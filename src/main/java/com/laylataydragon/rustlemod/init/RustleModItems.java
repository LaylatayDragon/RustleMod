
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.laylataydragon.rustlemod.init;

import com.laylataydragon.rustlemod.Rustle;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RustleModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Rustle.MODID);
//	public static final RegistryObject<Item> WHITE_AVERAGE_FLEECE = REGISTRY.register("white_average_fleece", () -> new AverageFleeceItem());
//	public static final RegistryObject<Item> COW_ARMOR = REGISTRY.register("cow_armor", () -> new CowArmorItem());
	public static final RegistryObject<Item> CALF_SPAWN_EGG = REGISTRY.register("calf_spawn_egg", () -> new ForgeSpawnEggItem(RustleModEntities.CALF, -1, -1, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> COW_SPAWN_EGG = REGISTRY.register("cow_spawn_egg", () -> new ForgeSpawnEggItem(RustleModEntities.COW, -1, -1, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> SHEEP_SPAWN_EGG = REGISTRY.register("sheep_spawn_egg", () -> new ForgeSpawnEggItem(RustleModEntities.SHEEP, -1, -1, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> LLAMA_SPAWN_EGG = REGISTRY.register("llama_spawn_egg", () -> new ForgeSpawnEggItem(RustleModEntities.LLAMA, -1, -1, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

}
