
package com.laylataydragon.rustlemod.client.renderer;

import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import com.laylataydragon.rustlemod.entity.model.SheepModel;
import com.laylataydragon.rustlemod.entity.SheepEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraftforge.client.event.EntityRenderersEvent.AddLayers;


public class SheepRenderer extends GeoEntityRenderer<SheepEntity> {
	public SheepRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new SheepModel());
		this.shadowRadius = 0.5f;
	}

	@Override
	public RenderType getRenderType(SheepEntity entity, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
		stack.scale(1f, 1f, 1f);
		return RenderType.entityTranslucent(getTextureLocation(entity));
	}
}
