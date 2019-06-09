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
public class R_78_2_Leggings extends ModelBiped {
    private static final ResourceLocation MODEL = new ResourceLocation(OceanHeartR.MODID, "textures/armors/r_78_2_leggings.obj");
    private CCModel left;
    private CCModel right;
    private CCRenderState leftRender;
    private CCRenderState rightRender;

    public R_78_2_Leggings() {
        this.bipedBody = new ModelRenderer(this, 0, 0);
        this.bipedBody.addBox(-4, -8, -4, 8, 8, 8, 0);
        this.bipedBody.setRotationPoint(0.0F, 0.0F, 6.0F);
        left = OBJParser.parseModels(MODEL).get("Box008");
        right = OBJParser.parseModels(MODEL).get("Box009");
        leftRender = CCRenderState.instance();
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
        GlStateManager.scale(0.013F, 0.013F, 0.013F);
        GlStateManager.rotate(180f, 1f, 0, 0);

        GlStateManager.pushMatrix();
        if (bipedLeftLeg.rotateAngleZ != 0.0F) {
            GlStateManager.rotate(bipedLeftLeg.rotateAngleZ * (180F / (float) Math.PI), 0.0F, 0.0F, 1.0F);
        }
        if (bipedLeftLeg.rotateAngleY != 0.0F) {
            GlStateManager.rotate(-bipedLeftLeg.rotateAngleY * (180F / (float) Math.PI), 0.0F, 1.0F, 0.0F);
        }
        if (bipedLeftLeg.rotateAngleX != 0.0F) {
            GlStateManager.rotate(bipedLeftLeg.rotateAngleX * (180F / (float) Math.PI), 1.0F, 0.0F, 0.0F);
        }
        leftRender.reset();
        leftRender.startDrawing(4, DefaultVertexFormats.ITEM);
        left.render(leftRender);
        leftRender.draw();
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        if (bipedRightLeg.rotateAngleZ != 0.0F) {
            GlStateManager.rotate(bipedRightLeg.rotateAngleZ * (180F / (float) Math.PI), 0.0F, 0.0F, 1.0F);
        }
        if (bipedRightLeg.rotateAngleY != 0.0F) {
            GlStateManager.rotate(-bipedRightLeg.rotateAngleY * (180F / (float) Math.PI), 0.0F, 1.0F, 0.0F);
        }
        if (bipedRightLeg.rotateAngleX != 0.0F) {
            GlStateManager.rotate(bipedRightLeg.rotateAngleX * (180F / (float) Math.PI), 1.0F, 0.0F, 0.0F);
        }
        rightRender.reset();
        rightRender.startDrawing(4, DefaultVertexFormats.ITEM);
        right.render(rightRender);
        rightRender.draw();
        GlStateManager.popMatrix();
    }
}
