
package net.mcreator.rustle.entity;

import software.bernie.geckolib3.util.GeckoLibUtil;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.builder.ILoopType.EDefaultLoopTypes;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.IAnimatable;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.Difficulty;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.Packet;

import net.mcreator.rustle.init.RustleModEntities;
import java.util.Random;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.item.Items;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.*;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.mcreator.rustle.procedures.MilkCowProcedure;
import net.mcreator.rustle.procedures.DisplayGenomeProcedure;


import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.nbt.CompoundTag;
import javax.annotation.Nullable;
import org.apache.commons.compress.harmony.unpack200.bytecode.forms.NewClassRefForm;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraft.network.chat.TextComponent;


@Mod.EventBusSubscriber
public class CowEntity extends Animal implements IAnimatable {
	private AnimationFactory factory = GeckoLibUtil.createFactory(this);
	private boolean swinging;
	private long lastSwing;
	public String animationprocedure = "empty";

   private static String disposition;
   private static String[] dispos 	= {"MEEK", "REACTIVE","AGRESSIVE", "FEARFUL"};
   private static String[][] geno = new String[16][2];
   							
   									
   private static String[][] Genes = {
   									{"d", "L", "l"}, //hair length
   									{"c","B", "b"}, //Dun
   									{"d","R", "n"}, //Dom red
   									{"c","Dc", "Dh", "n"}, //Dilute
   									{"c","ED", "E+", "e"}, //Dom black/agouti
   									{"d","P", "H"}, //Polled
   									{"c","R", "r"}, //Roan
   									{"c","R", "r"}, //Riggit(Color sided)
   									{"d","B", "b"}, //Brocking
   									{"r","C", "c"},	//albinism
   									{"r","Sh","Sb", "s"}, //spotting
   									{"d","Bt", "n"}, //Belted
   									{"c","Bl", "n"}, //Blaze
   									{"r", "B", "b"}, //Brindle
   									{"c", "L", "l"}, //horn length
   									{"d", "T", "t"} //tx longhorn mutation
   									};
   private static boolean milkable;
   private static boolean sex;
	
	@SubscribeEvent
	public static void addLivingEntityToBiomes(BiomeLoadingEvent event) {
		event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(RustleModEntities.COW.get(), 20, 4, 4));
		
	}


	public CowEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(RustleModEntities.COW.get(), world);
	}

	public CowEntity(EntityType<CowEntity> type, Level world) {
		super(type, world);
		xpReward = 0;
		setNoAi(false);
	}

	public static void rollGenome(){
		String[][] other = new String[16][2];
		if(genoToString().equals(genoToString(other)))
		{
			return;
		}
		Random x = new Random();

		int i = 0;
		for(String[] gene : geno)
		{
			String a = Genes[i][x.nextInt(gene.length)+1];
			String b =	Genes[i][x.nextInt(gene.length)+1];
			geno[i][0]=a;
			geno[i][1]=b;
			i++;
		}
	}

	public void rollGenome(String[][] parent)
	{
		
	}

	public void rollGenome(String[][] a, String[][] b)
	{
		
	}

	public String dispoToString()
	{
		return disposition;
	}

	public static String genoToString(String[][] genotype)
	{
		String holder = "";
		for(int a = 0; a < genotype.length; a++)
		{
			holder += genotype[a][0];
			holder += genotype[a][1];
		}
		return holder;
	}

	public static String genoToString()
	{
		String holder = "";
		for(int a = 0; a < geno.length; a++)
		{
			holder += geno[a][0];
			holder += geno[a][1];
		}
		return holder;
	}
	
	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		Random x = new Random();
		int i = 0;
		for(String[] gene : geno)
		{
			String a = Genes[i][x.nextInt(gene.length)+1];
			String b =	Genes[i][x.nextInt(gene.length)+1];
			geno[i][0]=a;
			geno[i][1]=b;
			i++;
		}
       int ran = x.nextInt(4);
       disposition = dispos[ran];
		if(disposition.equals("FEARFUL"))
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, (float) 6, 1, 1.2));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false) {

			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return (double) (4.0 + entity.getBbWidth() * entity.getBbWidth());
			}
		});

		if(disposition.equals("MEEK"))
		this.goalSelector.addGoal(3, new PanicGoal(this, 1.2));
		this.goalSelector.addGoal(4, new BreedGoal(this, 1));
		this.goalSelector.addGoal(5, new TemptGoal(this, 1, Ingredient.of(Items.WHEAT), false));
		this.goalSelector.addGoal(6, new FollowMobGoal(this, (float) 1, 10, 5));
		this.goalSelector.addGoal(7, new RandomStrollGoal(this, 1));
		if(disposition.equals("REACTIVE"))
		this.targetSelector.addGoal(8, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(9, new EatBlockGoal(this));
		this.goalSelector.addGoal(10, new RemoveBlockGoal(Blocks.GRASS, this, 1, (int) 3));
		this.goalSelector.addGoal(11, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(12, new FloatGoal(this));
		if(disposition.equals("AGRESSIVE"))
		this.targetSelector.addGoal(13, new NearestAttackableTargetGoal(this, Player.class, true, true));
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
		CalfEntity retval = RustleModEntities.CALF.get().create(serverWorld);
		retval.finalizeSpawn(serverWorld, serverWorld.getCurrentDifficultyAt(retval.blockPosition()), MobSpawnType.BREEDING, null, null);
		return retval;
	}
	
	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
	}

	public static void init() {
		SpawnPlacements.register(RustleModEntities.COW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL
						&& Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 10);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		return builder;
	}

	private <E extends IAnimatable> PlayState movementPredicate(AnimationEvent<E> event) {
		//if (this.animationprocedure == "empty") {
			if (event.isMoving() || !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F)) {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", EDefaultLoopTypes.LOOP));
				return PlayState.CONTINUE;
			}
			if (this.isDeadOrDying()) {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("death", EDefaultLoopTypes.PLAY_ONCE));
				return PlayState.CONTINUE;
			}
			if (this.isInWaterOrBubble()) {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("swim", EDefaultLoopTypes.LOOP));
				return PlayState.CONTINUE;
			}
			if (this.isShiftKeyDown()) {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("sneak", EDefaultLoopTypes.LOOP));
				return PlayState.CONTINUE;
			} else if (this.isSprinting()) {
				event.getController().setAnimation(new AnimationBuilder().addAnimation("sprint", EDefaultLoopTypes.LOOP));
				return PlayState.CONTINUE;
			}
			event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", EDefaultLoopTypes.LOOP));
			return PlayState.CONTINUE;
		//}
//		return PlayState.STOP;
	}

	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
		ItemStack itemstack = sourceentity.getItemInHand(hand);
		InteractionResult retval = InteractionResult.sidedSuccess(this.level.isClientSide());
		super.mobInteract(sourceentity, hand);
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		CowEntity entity = this;
		Level world = this.level;
		/*List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(this, 0);
		Entity closest = null;
		Vec3 pos = new Vec3(0, 0, 0);
		for(Entity a : entities)
		{
			if(Math.abs(a.getX()-x)<Math.abs(pos.x-x))
			{}
		}*/
		milkable = MilkCowProcedure.execute(sourceentity, milkable);
		DisplayGenomeProcedure.execute(sourceentity, genoToString());

		
		return retval;
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason,
			@Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
		SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
		Random x = new Random();
       /*int ran = x.nextInt(4);
       disposition = dispos[ran];*/
       sex = x.nextBoolean();
       milkable = false;
		return retval;
	}

	
	public void setMilkable(boolean milk)
	{
		milkable = milk;
	}


	private <E extends IAnimatable> PlayState attackingPredicate(AnimationEvent<E> event) {
		double d1 = this.getX() - this.xOld;
		double d0 = this.getZ() - this.zOld;
		float velocity = (float) Math.sqrt(d1 * d1 + d0 * d0);
		if (getAttackAnim(event.getPartialTick()) > 0f && !this.swinging) {
			this.swinging = true;
			this.lastSwing = level.getGameTime();
		}
		if (this.swinging && this.lastSwing + 15L <= level.getGameTime()) {
			this.swinging = false;
		}
		if (this.swinging && event.getController().getAnimationState().equals(software.bernie.geckolib3.core.AnimationState.Stopped)) {
			event.getController().markNeedsReload();
			event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", EDefaultLoopTypes.PLAY_ONCE));
			return PlayState.CONTINUE;
		}
		return PlayState.CONTINUE;
	}

	private <E extends IAnimatable> PlayState procedurePredicate(AnimationEvent<E> event) {
		if (!(this.animationprocedure == "empty")
				&& event.getController().getAnimationState().equals(software.bernie.geckolib3.core.AnimationState.Stopped)) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation(this.animationprocedure, EDefaultLoopTypes.PLAY_ONCE));
			if (event.getController().getAnimationState().equals(software.bernie.geckolib3.core.AnimationState.Stopped)) {
				this.animationprocedure = "empty";
				event.getController().markNeedsReload();
			}
		}
		return PlayState.CONTINUE;
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 20) {
			this.remove(CowEntity.RemovalReason.KILLED);
			this.dropExperience();
		}
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<>(this, "movement", 4, this::movementPredicate));
		data.addAnimationController(new AnimationController<>(this, "attacking", 4, this::attackingPredicate));
		data.addAnimationController(new AnimationController<>(this, "procedure", 4, this::procedurePredicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}
}
