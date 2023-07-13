
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.rustle.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;

import net.mcreator.rustle.item.CowArmorItem;
import net.mcreator.rustle.item.AverageFleeceItem;
import net.mcreator.rustle.RustleMod;

public class RustleModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, RustleMod.MODID);
	public static final RegistryObject<Item> COW = REGISTRY.register("cow_spawn_egg",
			() -> new ForgeSpawnEggItem(RustleModEntities.COW, -1, -1, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> SHEEP = REGISTRY.register("sheep_spawn_egg",
			() -> new ForgeSpawnEggItem(RustleModEntities.SHEEP, -1, -1, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final RegistryObject<Item> WHITE_AVERAGE_FLEECE = REGISTRY.register("white_average_fleece", () -> new AverageFleeceItem());
	public static final RegistryObject<Item> COW_ARMOR = REGISTRY.register("cow_armor", () -> new CowArmorItem());
	public static final RegistryObject<Item> CALF = REGISTRY.register("calf_spawn_egg",
			() -> new ForgeSpawnEggItem(RustleModEntities.CALF, -1, -1, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
}
