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
 * @author KSGFK create in 2019/2/20
 */
public class NatureHeltmet extends ModelBiped {
    private static final ResourceLocation MODEL = new ResourceLocation(OceanHeartR.MODID, "textures/armors/nature_helmet.obj");
    private CCModel model;

    public NatureHeltmet() {
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.addBox(-4, -8, -4, 8, 8, 8, 0);
        this.bipedHead.setRotationPoint(0.0F, 0.0F, 6.0F);
        model = OBJParser.parseModels(MODEL).get("Box003");
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
        GlStateManager.scale(0.015F, 0.015F, 0.015F);
        GlStateManager.rotate(180f, 1f, 0, 0);
        if (bipedHead.rotateAngleZ != 0.0F) {
            GlStateManager.rotate(bipedHead.rotateAngleZ * (180F / (float) Math.PI), 0.0F, 0.0F, 1.0F);
        }
        if (bipedHead.rotateAngleY != 0.0F) {
            GlStateManager.rotate(-bipedHead.rotateAngleY * (180F / (float) Math.PI), 0.0F, 1.0F, 0.0F);
        }
        if (bipedHead.rotateAngleX != 0.0F) {
            GlStateManager.rotate(bipedHead.rotateAngleX * (180F / (float) Math.PI), 1.0F, 0.0F, 0.0F);
        }
        CCRenderState ccrs = CCRenderState.instance();
        ccrs.reset();
        ccrs.startDrawing(4, DefaultVertexFormats.ITEM);
        model.render(ccrs);
        ccrs.draw();
    }
}
