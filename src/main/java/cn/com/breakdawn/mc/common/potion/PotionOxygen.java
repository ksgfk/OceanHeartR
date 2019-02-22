package cn.com.breakdawn.mc.common.potion;

import net.minecraft.entity.EntityLivingBase;

/**
 * @author KSGFK create in 2019/2/22
 */
public class PotionOxygen extends PotionBase {
    public PotionOxygen() {
        super(false, 0x87CEEB, "oxygen", 36, 0);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        if (!entityLivingBaseIn.world.isRemote) {
            int air = entityLivingBaseIn.getAir();
            if (air < 300) {
                int willModify = 300 - air;
                if (willModify > 150) {
                    entityLivingBaseIn.setAir(entityLivingBaseIn.getAir() + 150);
                } else {
                    entityLivingBaseIn.setAir(entityLivingBaseIn.getAir() + willModify);
                }
            }
            if (entityLivingBaseIn.getHealth() < entityLivingBaseIn.getMaxHealth() && entityLivingBaseIn.isInWater()) {
                entityLivingBaseIn.heal(5);
                entityLivingBaseIn.setAir(entityLivingBaseIn.getAir() - 150);
            }
        }
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        int a = 200 >> amplifier;
        if (a > 0) {
            return duration % a == 0;
        } else {
            return true;
        }
    }
}
