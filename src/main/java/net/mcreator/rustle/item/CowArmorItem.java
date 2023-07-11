
package net.mcreator.rustle.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;

public class CowArmorItem extends Item {
	public CowArmorItem() {
		super(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).durability(10).rarity(Rarity.COMMON));
	}
}
