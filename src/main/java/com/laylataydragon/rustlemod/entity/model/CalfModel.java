package com.laylataydragon.rustlemod.entity.model;

import software.bernie.geckolib3.model.AnimatedGeoModel;

import net.minecraft.resources.ResourceLocation;

import com.laylataydragon.rustlemod.entity.CalfEntity;

public class CalfModel extends AnimatedGeoModel<CalfEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(CalfEntity entity) {
		return new ResourceLocation("rustle", "animations/calf.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(CalfEntity entity) {
		return new ResourceLocation("rustle", "geo/calf.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(CalfEntity entity) {
		return new ResourceLocation("rustle", "textures/entities/" + entity.getTexture() + ".png");
	}

}
