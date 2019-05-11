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
    private CCModel model;
    private CCRenderState ccrs;

    public R_78_2_Chest() {
        this.bipedBody = new ModelRenderer(this, 0, 0);
        this.bipedBody.addBox(-4, -8, -4, 8, 8, 8, 0);
        this.bipedBody.setRotationPoint(0.0F, 0.0F, 6.0F);
        model = OBJParser.parseModels(MODEL).get("Box007");
        ccrs = CCRenderState.instance();
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
        GlStateManager.scale(0.0125F, 0.0125F, 0.0125F);
        GlStateManager.rotate(180f, 1f, 0, 0);
        ccrs.reset();
        ccrs.startDrawing(4, DefaultVertexFormats.ITEM);
        model.render(ccrs);
        ccrs.draw();
    }
}
