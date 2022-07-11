package com.frostmourneee.minecartt.client.renderer;

import com.frostmourneee.minecartt.client.renderer.model.WagonEntityModel;
import com.frostmourneee.minecartt.common.entity.WagonEntity;
import com.frostmourneee.minecartt.minecartt;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class WagonEntityRenderer extends EntityRenderer<WagonEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(minecartt.MOD_ID, "textures/entities/wagon_red.png");
    public WagonEntityModel model;

    public WagonEntityRenderer(EntityRendererProvider.Context p_174300_) {
        super(p_174300_);
        this.shadowRadius = 0.7F;
        this.model = new WagonEntityModel<>(p_174300_.bakeLayer(WagonEntityModel.LAYER_LOCATION));
    }

    public void render(WagonEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
        pMatrixStack.pushPose();
        long i = (long)pEntity.getId() * 493286711L;
        i = i * i * 4392167121L + i * 98761L;
        float f = (((float)(i >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float f1 = (((float)(i >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float f2 = (((float)(i >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        pMatrixStack.translate((double)f, (double)f1, (double)f2);
        double d0 = Mth.lerp((double)pPartialTicks, pEntity.xOld, pEntity.getX());
        double d1 = Mth.lerp((double)pPartialTicks, pEntity.yOld, pEntity.getY());
        double d2 = Mth.lerp((double)pPartialTicks, pEntity.zOld, pEntity.getZ());
        double d3 = (double)0.3F;
        Vec3 vec3 = pEntity.getPos(d0, d1, d2);
        float f3 = Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot());
        if (vec3 != null) {
            Vec3 vec31 = pEntity.getPosOffs(d0, d1, d2, (double)0.3F);
            Vec3 vec32 = pEntity.getPosOffs(d0, d1, d2, (double)-0.3F);
            if (vec31 == null) {
                vec31 = vec3;
            }

            if (vec32 == null) {
                vec32 = vec3;
            }

            pMatrixStack.translate(vec3.x - d0, (vec31.y + vec32.y) / 2.0D - d1, vec3.z - d2);
            Vec3 vec33 = vec32.add(-vec31.x, -vec31.y, -vec31.z);
            if (vec33.length() != 0.0D) {
                vec33 = vec33.normalize();
                pEntityYaw = (float)(Math.atan2(vec33.z, vec33.x) * 180.0D / Math.PI);
                f3 = (float)(Math.atan(vec33.y) * 73.0D);
            }
        }

        pMatrixStack.translate(0.0D, 0.375D, 0.0D);

        /*
        My code starts
         */
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
        /*
        My code ends
         */

        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - pEntityYaw));
        pMatrixStack.mulPose(Vector3f.ZP.rotationDegrees(-f3));
        float f5 = (float)pEntity.getHurtTime() - pPartialTicks;
        float f6 = pEntity.getDamage() - pPartialTicks;
        if (f6 < 0.0F) {
            f6 = 0.0F;
        }

        if (f5 > 0.0F) {
            pMatrixStack.mulPose(Vector3f.XP.rotationDegrees(Mth.sin(f5) * f5 * f6 / 10.0F * (float)pEntity.getHurtDir()));
        }

        int j = pEntity.getDisplayOffset();
        BlockState blockstate = pEntity.getDisplayBlockState();
        if (blockstate.getRenderShape() != RenderShape.INVISIBLE) {
            pMatrixStack.pushPose();
            float f4 = 0.75F;
            pMatrixStack.scale(0.75F, 0.75F, 0.75F);
            pMatrixStack.translate(-0.5D, (double)((float)(j - 8) / 16.0F), 0.5D);
            pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
            this.renderMinecartContents(pEntity, pPartialTicks, blockstate, pMatrixStack, pBuffer, pPackedLight);
            pMatrixStack.popPose();
        }

        pMatrixStack.scale(-1.0F, -1.0F, 1.0F);
        this.model.setupAnim(pEntity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(this.model.renderType(this.getTextureLocation(pEntity)));
        this.model.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pMatrixStack.popPose();
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(WagonEntity pEntity) {
        return TEXTURE;
    }

    protected void renderMinecartContents(WagonEntity pEntity, float pPartialTicks, BlockState pState, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(pState, pMatrixStack, pBuffer, pPackedLight, OverlayTexture.NO_OVERLAY);
    }
}