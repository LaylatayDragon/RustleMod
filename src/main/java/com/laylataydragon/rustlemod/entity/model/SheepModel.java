package com.laylataydragon.rustlemod.entity.model;

import software.bernie.geckolib3.model.AnimatedGeoModel;

import net.minecraft.resources.ResourceLocation;

import com.laylataydragon.rustlemod.entity.SheepEntity;

public class SheepModel extends AnimatedGeoModel<SheepEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(SheepEntity entity) {
		return new ResourceLocation("rustle", "animations/sheep.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(SheepEntity entity) {
		return new ResourceLocation("rustle", "geo/sheep.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(SheepEntity entity) {
		return new ResourceLocation("rustle", "textures/entities/" + entity.getTexture() + ".png");
	}

}
