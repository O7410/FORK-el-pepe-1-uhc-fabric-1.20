package com.elpepe.uhc.entity.client;

import net.minecraft.entity.Entity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.ModelPart;

public class HarpoonProjectileModel extends EntityModel<Entity> {
   private final ModelPart bb_main;

   public HarpoonProjectileModel(ModelPart root) {
      this.bb_main = root.getChild("bb_main");
   }

   public static TexturedModelData getTexturedModelData() {
      ModelData modelData = new ModelData();
      ModelPartData modelPartData = modelData.getRoot();
      modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, 0.0F, -3.0F, 2.0F, 0.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
      return TexturedModelData.of(modelData, 16, 16);
   }

   public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }

   public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
      this.bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
   }
}
