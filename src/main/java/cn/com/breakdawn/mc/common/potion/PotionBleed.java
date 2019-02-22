package cn.com.breakdawn.mc.common.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

/**
 * @author KSGFK create in 2019/2/20
 */
public class PotionBleed extends PotionBase {
    public PotionBleed() {
        super(true, 0xFF0000, "bleed", 0, 0);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        if (!entityLivingBaseIn.world.isRemote)
            entityLivingBaseIn.attackEntityFrom(DamageSource.MAGIC, 1.0F + amplifier);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        int a = 40 >> amplifier;
        if (a > 0) {
            return duration % a == 0;
        } else {
            return true;
        }
    }
}
