package com.laylataydragon.rustlemod.entity.model;

import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

import net.minecraft.resources.ResourceLocation;
import com.laylataydragon.rustlemod.entity.CowEntity;

public class CowModel extends AnimatedGeoModel<CowEntity> {
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
	}

	@Override
	public void setCustomAnimations(CowEntity animatable, int instanceId, AnimationEvent animationEvent) {
		super.setCustomAnimations(animatable, instanceId, animationEvent);/*
		IBone head = this.getAnimationProcessor().getBone("head");
		EntityModelData extraData = (EntityModelData) animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
		AnimationData manager = animatable.getFactory().getOrCreateAnimationData(instanceId);
		int unpausedMultiplier = !Minecraft.getInstance().isPaused() || manager.shouldPlayWhilePaused ? 1 : 0;
		head.setRotationX(head.getRotationX() + extraData.headPitch * ((float) Math.PI / 180F) * unpausedMultiplier);
		head.setRotationY(head.getRotationY() + extraData.netHeadYaw * ((float) Math.PI / 180F) * unpausedMultiplier);
	*/}
}
