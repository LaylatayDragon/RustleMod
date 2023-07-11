package net.mcreator.rustle.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class BreedAnimalProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.isBaby() : false) == false) {
			return true;
		}
		return false;
	}
}
