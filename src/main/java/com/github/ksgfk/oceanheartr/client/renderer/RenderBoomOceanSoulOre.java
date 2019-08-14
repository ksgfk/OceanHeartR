package com.github.ksgfk.oceanheartr.client.renderer;

import com.github.ksgfk.oceanheartr.OceanHeartR;
import com.github.ksgfk.oceanheartr.annotation.EntityModelRegistry;
import com.github.ksgfk.oceanheartr.client.model.ModelBoomOceanSoulOre;
import com.github.ksgfk.oceanheartr.common.entity.EntityBoomOceanSoulOre;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

/**
 * @author KSGFK create in 2019/8/13
 */
@EntityModelRegistry(entityClass = EntityBoomOceanSoulOre.class)
public class RenderBoomOceanSoulOre extends RenderLiving<EntityBoomOceanSoulOre> {
    private static ResourceLocation rl = new ResourceLocation(OceanHeartR.MOD_ID, "textures/entity/boom_ocean_soul_ore.png");

    public RenderBoomOceanSoulOre(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelBoomOceanSoulOre(), 0.25F);
    }

    public void doRender(EntityBoomOceanSoulOre entity, double x, double y, double z, float entityYaw, float partialTicks) {
//        BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
//        GlStateManager.pushMatrix();
//        GlStateManager.translate((float) x, (float) y + 0.5F, (float) z);

//        if ((float) entity.getFuse() - partialTicks + 1.0F < 10.0F) {
//            float f = 1.0F - ((float) entity.getFuse() - partialTicks + 1.0F) / 10.0F;
//            f = MathHelper.clamp(f, 0.0F, 1.0F);
//            f = f * f;
//            f = f * f;
//            float f1 = 1.0F + f * 0.3F;
//            GlStateManager.scale(f1, f1, f1);
//        }
//
//        float f2 = (1.0F - ((float) entity.getFuse() - partialTicks + 1.0F) / 100.0F) * 0.8F;
//        this.bindEntityTexture(entity);
//        GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
//        GlStateManager.translate(-0.5F, -0.5F, 0.5F);
//        blockrendererdispatcher.renderBlockBrightness(OHRBlocks.OceanSoulOre.getDefaultState(), entity.getBrightness());
//        GlStateManager.translate(0.0F, 0.0F, 1.0F);
//
//        if (this.renderOutlines) {
//            GlStateManager.enableColorMaterial();
//            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
//            blockrendererdispatcher.renderBlockBrightness(OHRBlocks.OceanSoulOre.getDefaultState(), 1.0F);
//            GlStateManager.disableOutlineMode();
//            GlStateManager.disableColorMaterial();
//        } else if (entity.getFuse() / 5 % 2 == 0) {
//            GlStateManager.disableTexture2D();
//            GlStateManager.disableLighting();
//            GlStateManager.enableBlend();
//            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.DST_ALPHA);
//            GlStateManager.color(1.0F, 1.0F, 1.0F, f2);
//            GlStateManager.doPolygonOffset(-3.0F, -3.0F);
//            GlStateManager.enablePolygonOffset();
//            blockrendererdispatcher.renderBlockBrightness(OHRBlocks.OceanSoulOre.getDefaultState(), 1.0F);
//            GlStateManager.doPolygonOffset(0.0F, 0.0F);
//            GlStateManager.disablePolygonOffset();
//            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//            GlStateManager.disableBlend();
//            GlStateManager.enableLighting();
//            GlStateManager.enableTexture2D();
//        }
//
//        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityBoomOceanSoulOre entity) {
        return rl;
    }
}
