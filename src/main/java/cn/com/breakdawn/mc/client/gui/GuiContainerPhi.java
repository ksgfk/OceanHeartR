package cn.com.breakdawn.mc.client.gui;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.inventory.ContainerPhi;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

/**
 * @author KSGFK create in 2019/2/27
 */
public class GuiContainerPhi extends GuiContainer {
    private static final String TEXTURE_PATH = "textures/gui/6.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(OceanHeartR.MODID, TEXTURE_PATH);

    public GuiContainerPhi(ContainerPhi inventorySlotsIn) {
        super(inventorySlotsIn);
        this.xSize = 176;
        this.ySize = 187;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);
    }
}
