package cn.com.breakdawn.mc.client.gui;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.inventory.ContainerPulverizer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

/**
 * @auther KSGFK
 */
public class GuiContainerPul extends GuiContainer {
    private static final String TEXTURE_PATH = "textures/gui/5.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(OceanHeartR.MODID, TEXTURE_PATH);

    private int energy;
    private int maxEnergy;
    private ContainerPulverizer pul;

    public GuiContainerPul(ContainerPulverizer inventorySlotsIn) {
        super(inventorySlotsIn);
        this.xSize = 176;
        this.ySize = 156;
        this.pul = inventorySlotsIn;
        maxEnergy = pul.getPul().getMaxEnergyStored(null);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);

        int textureWidth = energy * 90 / maxEnergy;
        this.drawTexturedModalRect(offsetX + 43, offsetY + 55, 24, 156, textureWidth, 4);
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }
}
