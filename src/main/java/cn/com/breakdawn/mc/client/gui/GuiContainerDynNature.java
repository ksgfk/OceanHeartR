package cn.com.breakdawn.mc.client.gui;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.inventory.ContainerDynamoNature;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 自然结晶发电机客户端GUI
 *
 * @author KSGFK
 */
@SideOnly(Side.CLIENT)
public class GuiContainerDynNature extends GuiContainer {
    private static final String TEXTURE_PATH = "textures/gui/4.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(OceanHeartR.MODID, TEXTURE_PATH);

    private ContainerDynamoNature dynamoNature;
    private int energy;
    private int maxEnergy;
    private int gening;
    private int maxPowerGen;

    public GuiContainerDynNature(ContainerDynamoNature inventorySlotsIn) {
        super(inventorySlotsIn);
        this.xSize = 176;
        this.ySize = 156;
        this.dynamoNature = inventorySlotsIn;

        maxEnergy = dynamoNature.getTileEntity().getMaxEnergyStored(null);
        maxPowerGen = dynamoNature.getTileEntity().getMaxPowerGen();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);

        int textureWidth = energy * 89 / maxEnergy;
        this.drawTexturedModalRect(offsetX + 43, offsetY + 55, 0, 156, textureWidth, 4);

        int wide = (maxPowerGen - gening) * 26 / maxPowerGen;
        if (gening == 0)
            this.drawTexturedModalRect(offsetX + 102, offsetY + 25, 89, 156, 0, 26);
        else
            this.drawTexturedModalRect(offsetX + 102, offsetY + 25, 89, 156, wide, 26);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String title = I18n.format("tile.dynamo_nature.name");
        this.fontRenderer.drawString(title, (this.xSize - this.fontRenderer.getStringWidth(title)) / 2, 6, 0x404040);

        String a = energy + "/" + maxEnergy;
        this.fontRenderer.drawString(a, 88 - this.fontRenderer.getStringWidth(a) / 2, 60, 0x404040);
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public void setGening(int gening) {
        this.gening = gening;
    }
}
