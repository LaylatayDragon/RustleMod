
package net.mcreator.rustle.client.renderer;

import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.rustle.procedures.SheepModelProcedure;
import net.mcreator.rustle.entity.SheepEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class SheepRenderer extends GeoEntityRenderer<SheepEntity> {
	public SheepRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new SheepModelProcedure());
		this.shadowRadius = 0.5f;
	}

	@Override
	public RenderType getRenderType(SheepEntity animatable, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer,
			VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
		stack.scale(1.0F, 1.0F, 1.0F);
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
