package net.mcreator.rustle.procedures;

import software.bernie.geckolib3.model.AnimatedGeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.rustle.entity.CowEntity;

/**
public static void execute(
) {
execute(null);
}
private static void execute(
@Nullable Event event
) {
**/
public class CowModelProcedure extends AnimatedGeoModel<CowEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(CowEntity entity) {
		return new ResourceLocation("rustle", "animations/cow.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(CowEntity entity) {
		return new ResourceLocation("rustle", "geo/cow.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(CowEntity entity) {
		return new ResourceLocation("rustle", "textures/entities/cow.png");
		/**
		}
		@Override
		public void setCustomAnimations(CowEntity animatable, int instanceId, AnimationEvent animationEvent) {
		super.setCustomAnimations(animatable, instanceId, animationEvent);
		IBone head = this.getAnimationProcessor().getBone("head");
		EntityModelData extraData = (EntityModelData) animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
		AnimationData manager = animatable.getFactory().getOrCreateAnimationData(instanceId);
		int unpausedMultiplier = !Minecraft.getInstance().isPaused() || manager.shouldPlayWhilePaused ? 1 : 0;
		head.setRotationX(head.getRotationX() + extraData.headPitch * ((float) Math.PI / 180F) * unpausedMultiplier);
		head.setRotationY(head.getRotationY() + extraData.netHeadYaw * ((float) Math.PI / 180F) * unpausedMultiplier);
		/** **/
	}
}