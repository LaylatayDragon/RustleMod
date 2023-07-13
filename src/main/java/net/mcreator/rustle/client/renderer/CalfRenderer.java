
package net.mcreator.rustle.client.renderer;

import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.rustle.configuration.RustleConfigConfiguration;
import net.mcreator.rustle.procedures.CalfModelProcedure;
import net.mcreator.rustle.entity.CalfEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class CalfRenderer extends GeoEntityRenderer<CalfEntity> {
	public CalfRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new CalfModelProcedure());
		this.shadowRadius = 0.5f;
	}

	@Override
	public RenderType getRenderType(CalfEntity animatable, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer,
			VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {

		float age = animatable.getPercentAdult()*2;
		stack.scale(1.0F, 1.0F, 1.0F);
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}
}
