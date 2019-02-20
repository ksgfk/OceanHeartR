package cn.com.breakdawn.mc.common.potion;

import cn.com.breakdawn.mc.OceanHeartR;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author KSGFK create in 2019/2/20
 */
public class PotionBleed extends Potion {
    public static final ResourceLocation texture = new ResourceLocation(OceanHeartR.MODID, "textures/gui/potions.png");

    public PotionBleed() {
        super(true, 0xFF0000);
        this.setPotionName("effect.oceanheartr.bleed");
        this.setRegistryName(OceanHeartR.MODID, "bleed");
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        entityLivingBaseIn.attackEntityFrom(DamageSource.MAGIC, 1.0F);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        int a = 80 >> amplifier;
        if (a > 0) {
            return duration % a == 0;
        } else {
            return true;
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        mc.getTextureManager().bindTexture(texture);
        mc.currentScreen.drawTexturedModalRect(x + 6, y + 7, 0, 0, 18, 18);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
        mc.getTextureManager().bindTexture(texture);
        mc.ingameGUI.drawTexturedModalRect(x + 3, y + 3, 0, 0, 18, 18);
    }
}
