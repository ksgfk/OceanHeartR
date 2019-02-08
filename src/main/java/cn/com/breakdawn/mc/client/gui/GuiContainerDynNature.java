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
 * @author KSGFK
 */
@SideOnly(Side.CLIENT)
public class GuiContainerDynNature extends GuiContainer {
    private static final String TEXTURE_PATH = "textures/gui/4.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(OceanHeartR.MODID, TEXTURE_PATH);

    private ContainerDynamoNature dynamoNature;
    private int energy;
    private int totalEnergy;

    public GuiContainerDynNature(ContainerDynamoNature inventorySlotsIn) {
        super(inventorySlotsIn);
        this.xSize = 176;
        this.ySize = 156;
        this.dynamoNature = inventorySlotsIn;
        this.energy = inventorySlotsIn.getEnergy();
        this.totalEnergy = inventorySlotsIn.getTotalEnergy();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);

        this.energy = dynamoNature.getEnergy();
        this.totalEnergy = dynamoNature.getTotalEnergy();
        OceanHeartR.getLogger().info(totalEnergy + "," + energy);
        int textureWidth = (int) Math.ceil(energy * 89 / totalEnergy);
        this.drawTexturedModalRect(offsetX + 43, offsetY + 55, 0, 156, textureWidth, 4);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String title = I18n.format("tile.dynamo_nature.name");
        this.fontRenderer.drawString(title, (this.xSize - this.fontRenderer.getStringWidth(title)) / 2, 6, 0x404040);
        String a = energy + "/" + totalEnergy;
        this.fontRenderer.drawString(a, 88 - this.fontRenderer.getStringWidth(a) / 2, 60, 0x404040);
    }
}
