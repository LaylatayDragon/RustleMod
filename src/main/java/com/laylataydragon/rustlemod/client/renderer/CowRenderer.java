
package com.laylataydragon.rustlemod.client.renderer;

import software.bernie.example.client.renderer.entity.ExampleGeoRenderer;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import com.laylataydragon.rustlemod.entity.model.CowModel;
import com.laylataydragon.rustlemod.client.renderer.Layers.CowLayer;
import com.laylataydragon.rustlemod.entity.CowEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class CowRenderer extends GeoEntityRenderer<CowEntity> {
	public CowRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new CowModel());
		this.shadowRadius = 0.5f;
		addLayer(new CowLayer(this));
		
		
	}

	@Override
	public RenderType getRenderType(CowEntity entity, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
		stack.scale(1f, 1f, 1f);
		return RenderType.entityTranslucent(getTextureLocation(entity));
		
	}
}
