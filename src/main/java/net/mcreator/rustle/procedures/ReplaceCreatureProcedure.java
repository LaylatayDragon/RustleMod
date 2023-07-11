package net.mcreator.rustle.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.rustle.init.RustleModEntities;
import net.mcreator.rustle.entity.CowEntity;
import net.mcreator.rustle.configuration.RustleConfigConfiguration;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ReplaceCreatureProcedure {
	@SubscribeEvent
	public static void onEntitySpawned(EntityJoinWorldEvent event) {
		execute(event, event.getWorld(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (RustleConfigConfiguration.REPLACEMOBS.get()) {
			if (entity instanceof Cow) {
				if (world instanceof ServerLevel _level) {
					Entity entityToSpawn = new CowEntity(RustleModEntities.COW.get(), _level);
					entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof Mob _mobToSpawn)
						_mobToSpawn.finalizeSpawn(_level, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED,
								null, null);
					world.addFreshEntity(entityToSpawn);
				}
				{
					Entity _ent = entity;
					_ent.teleportTo(0, (-100), 0);
					if (_ent instanceof ServerPlayer _serverPlayer)
						_serverPlayer.connection.teleport(0, (-100), 0, _ent.getYRot(), _ent.getXRot());
				}
				if (entity instanceof LivingEntity _entity)
					_entity.setHealth(0);
			}
		}
	}
}
