package com.frostmourneee.minecartt.client.renderer.model;// Made with Blockbench 4.2.5
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.frostmourneee.minecartt.common.entity.WagonEntity;
import com.frostmourneee.minecartt.minecartt;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class WagonEntityModel<T extends WagonEntity> extends EntityModel<WagonEntity> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(minecartt.MOD_ID, "wagonentitymodel"), "main");
    private final ModelPart body;
    private final ModelPart hook;
    private final ModelPart hole;

    public WagonEntityModel(ModelPart root) {
        this.body = root.getChild("body");
        this.hook = root.getChild("hook");
        this.hole = root.getChild("hole");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -1.0F, -9.0F, 14.0F, 1.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(60, 20).addBox(-8.0F, -10.0F, -10.0F, 16.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(47, 0).addBox(-8.0F, -10.0F, 9.0F, 16.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(39, 20).addBox(-8.0F, -10.0F, -9.0F, 1.0F, 10.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(7.0F, -10.0F, -9.0F, 1.0F, 10.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(77, 48).addBox(-7.0F, -10.0F, 8.0F, 14.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 67).addBox(-7.0F, -10.0F, -9.0F, 14.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(58, 59).addBox(-7.0F, -10.0F, -8.0F, 1.0F, 9.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(39, 49).addBox(6.0F, -10.0F, -8.0F, 1.0F, 9.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 13).addBox(0.0F, -2.0F, -14.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-6.0F, -2.0F, -8.0F, 12.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 0.0F));

        PartDefinition hook = partdefinition.addOrReplaceChild("hook", CubeListBuilder.create().texOffs(0, 20).addBox(-1.0F, 0.0F, -14.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(10, 12).addBox(-1.0F, -1.0F, -12.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 5).addBox(-2.0F, -2.0F, -11.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(11, 8).addBox(-1.0F, -2.0F, -14.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.0F));

        PartDefinition hole = partdefinition.addOrReplaceChild("hole", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -4.0F, 11.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(11, 0).addBox(-2.0F, -3.0F, 12.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(11, 4).addBox(1.0F, -3.0F, 12.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 10).addBox(-2.0F, -3.0F, 14.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, -1.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(WagonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        hook.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        hole.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}