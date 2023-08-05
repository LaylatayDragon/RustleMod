package com.laylataydragon.rustlemod.entity.model;

import software.bernie.geckolib3.model.AnimatedGeoModel;

import net.minecraft.resources.ResourceLocation;

import com.laylataydragon.rustlemod.entity.LlamaEntity;

public class LlamaModel extends AnimatedGeoModel<LlamaEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(LlamaEntity entity) {
		return new ResourceLocation("rustle", "animations/llama.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(LlamaEntity entity) {
		return new ResourceLocation("rustle", "geo/llama.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(LlamaEntity entity) {
		return new ResourceLocation("rustle", "textures/entities/" + entity.getTexture() + ".png");
	}

}
