
package com.laylataydragon.rustlemod.client.renderer;

import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import com.laylataydragon.rustlemod.entity.model.CalfModel;
import com.laylataydragon.rustlemod.entity.CalfEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class CalfRenderer extends GeoEntityRenderer<CalfEntity> {
	public CalfRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new CalfModel());
		this.shadowRadius = 0.5f;
	}

	@Override
	public RenderType getRenderType(CalfEntity entity, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
		float age = entity.getPercentAdult()*2;
		stack.scale(1.0F+age, 1.0F+age, 1.0F+age);
		return RenderType.entityTranslucent(getTextureLocation(entity));
	}
}
