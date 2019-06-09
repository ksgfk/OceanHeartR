package cn.com.breakdawn.mc.client.model;

import cn.com.breakdawn.mc.OceanHeartR;
import codechicken.lib.render.CCModel;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.OBJParser;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

/**
 * @author KSGFK create in 2019/5/11
 */
public class R_78_2_Chest extends ModelBiped {
    private static final ResourceLocation MODEL = new ResourceLocation(OceanHeartR.MODID, "textures/armors/r_78_2_chestplate.obj");
    private CCModel chest;
    private CCRenderState chestRender;
    private CCModel left;
    private CCRenderState leftRender;
    private CCModel right;
    private CCRenderState rightRender;

    public R_78_2_Chest() {
        this.bipedBody = new ModelRenderer(this, 0, 0);
        this.bipedBody.addBox(-4, -8, -4, 8, 8, 8, 0);
        this.bipedBody.setRotationPoint(0.0F, 0.0F, 6.0F);
        chest = OBJParser.parseModels(MODEL).get("Box007");
        chestRender = CCRenderState.instance();
        left = OBJParser.parseModels(MODEL).get("b001");
        leftRender = CCRenderState.instance();
        right = OBJParser.parseModels(MODEL).get("b002");
        rightRender = CCRenderState.instance();
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        GlStateManager.pushMatrix();
        if (entityIn.isSneaking()) {
            GlStateManager.translate(0.0F, 0.225F, 0.0F);
            renderCore();
        } else renderCore();
        GlStateManager.popMatrix();
    }

    private void renderCore() {
        GlStateManager.pushMatrix();
        GlStateManager.translate(0, -0.001F, 0);
        GlStateManager.scale(0.0135F, 0.0135F, 0.0135F);
        GlStateManager.rotate(180f, 1f, 0, 0);
        chestRender.reset();
        chestRender.startDrawing(4, DefaultVertexFormats.ITEM);
        chest.render(chestRender);
        chestRender.draw();
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();

        GlStateManager.rotate(180f, 1f, 0, 0);
        GlStateManager.scale(0.015F, 0.015F, 0.015F);
        if (bipedLeftArm.rotateAngleZ != 0.0F) {
            GlStateManager.rotate(bipedLeftArm.rotateAngleZ * (180F / (float) Math.PI), 0.0F, 0.0F, 1.0F);
        }
        if (bipedLeftArm.rotateAngleY != 0.0F) {
            GlStateManager.rotate(-bipedLeftArm.rotateAngleY * (180F / (float) Math.PI), 0.0F, 1.0F, 0.0F);
        }
        if (bipedLeftArm.rotateAngleX != 0.0F) {
            GlStateManager.rotate(bipedLeftArm.rotateAngleX * (180F / (float) Math.PI), 1.0F, 0.0F, 0.0F);
        }
        leftRender.reset();
        leftRender.startDrawing(4, DefaultVertexFormats.ITEM);
        left.render(leftRender);
        leftRender.draw();
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.rotate(180f, 1f, 0, 0);
        GlStateManager.scale(0.015F, 0.015F, 0.015F);
        if (bipedRightArm.rotateAngleZ != 0.0F) {
            GlStateManager.rotate(bipedRightArm.rotateAngleZ * (180F / (float) Math.PI), 0.0F, 0.0F, 1.0F);
        }
        if (bipedRightArm.rotateAngleY != 0.0F) {
            GlStateManager.rotate(-bipedRightArm.rotateAngleY * (180F / (float) Math.PI), 0.0F, 1.0F, 0.0F);
        }
        if (bipedRightArm.rotateAngleX != 0.0F) {
            GlStateManager.rotate(bipedRightArm.rotateAngleX * (180F / (float) Math.PI), 1.0F, 0.0F, 0.0F);
        }
        rightRender.reset();
        rightRender.startDrawing(4, DefaultVertexFormats.ITEM);
        right.render(rightRender);
        rightRender.draw();
        GlStateManager.popMatrix();
    }
}
