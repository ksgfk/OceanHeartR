package cn.com.breakdawn.mc.common.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author KSGFK create in 2019/2/22
 */
public class PotionFeed extends PotionBase {
    public PotionFeed() {
        super(false, 0x00FF00, "feed", 18, 0);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        if (!entityLivingBaseIn.world.isRemote && entityLivingBaseIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLivingBaseIn;
            player.getFoodStats().addStats(1, 1F + amplifier);
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        int a = 400 >> amplifier;
        if (a > 0) {
            return duration % a == 0;
        } else {
            return true;
        }
    }
}
