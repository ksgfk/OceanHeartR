package cn.com.breakdawn.mc.client.render;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.entity.EntityGodBrick;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

/**
 * @author KSGFK create in 2019/5/2
 */
public class EntityGodBrickRender extends Render<EntityGodBrick> {
    public static final Factory FACTORY = new EntityGodBrickRender.Factory();
    public static double[] size = {
            0.8, // 点弹 0
            0.8, // 小玉 1
            0.8, // 中玉 2
            1.2, // 大玉 3
            0.4, // 椭弹 4
            0.4, // 心弹 5
            0.3, // 星弹 6
            0.3, // 札弹 7
            0.3  // 环玉 8
    };

    public EntityGodBrickRender(RenderManager renderManager) {
        super(renderManager);
    }

    //感谢酒石酸（TartaricAcid）的开源代码
    //https://github.com/TartaricAcid/BakaInTouhou/blob/master/src/main/java/com/github/tartaricacid/bakaintouhou/client/render/danmaku/EntityNormalDanmakuRender.java
    @Override
    public void doRender(EntityGodBrick entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.disableLighting();
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
                GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0f);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);

        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float) (this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX,
                1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(180F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(180F, 0.0F, 0.0F, 1.0F);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufbuilder = tessellator.getBuffer();

        bufbuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        this.renderManager.renderEngine.bindTexture(getEntityTexture(entity));

        bufbuilder.pos(-1, 1, 0).tex(0, 1).endVertex();
        bufbuilder.pos(-1, -1, 0).tex(0, 1).endVertex();
        bufbuilder.pos(1, -1, 0).tex(1, 0).endVertex();
        bufbuilder.pos(1, 1, 0).tex(1, 1).endVertex();
        tessellator.draw();

        GlStateManager.disableBlend();
        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.enableLighting();
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityGodBrick entity) {
        return new ResourceLocation(OceanHeartR.MODID, "textures/items/god_brick.png");
    }

    public static class Factory implements IRenderFactory<EntityGodBrick> {
        @SideOnly(Side.CLIENT)
        @Override
        public Render<? super EntityGodBrick> createRenderFor(RenderManager manager) {
            return new EntityGodBrickRender(manager);
        }
    }
}
