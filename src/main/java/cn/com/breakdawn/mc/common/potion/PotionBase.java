package cn.com.breakdawn.mc.common.potion;

import cn.com.breakdawn.mc.OceanHeartR;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author KSGFK create in 2019/2/22
 */
public abstract class PotionBase extends Potion {
    private int textureX;
    private int textureY;
    static final ResourceLocation TEXTURE = new ResourceLocation(OceanHeartR.MODID, "textures/gui/potions.png");

    public PotionBase(boolean isBadEffectIn, int liquidColorIn, String name, int x, int y) {
        super(isBadEffectIn, liquidColorIn);
        this.setPotionName("effect.oceanheartr." + name);
        this.setRegistryName(OceanHeartR.MODID, name);
        this.textureX = x;
        this.textureY = y;
    }

    @Override
    public abstract void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier);

    @Override
    public abstract boolean isReady(int duration, int amplifier);

    @SideOnly(Side.CLIENT)
    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        mc.getTextureManager().bindTexture(TEXTURE);
        mc.currentScreen.drawTexturedModalRect(x + 6, y + 7, textureX, textureY, 18, 18);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
        mc.getTextureManager().bindTexture(TEXTURE);
        mc.ingameGUI.drawTexturedModalRect(x + 3, y + 3, textureX, textureY, 18, 18);
    }
}
