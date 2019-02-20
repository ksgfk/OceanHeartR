package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.common.potion.PotionBleed;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

/**
 * @author KSGFK create in 2019/2/20
 */
public class OHRPotion {
    public static final Potion BLEED = new PotionBleed();

    public static void init() {
        ForgeRegistries.POTIONS.register(BLEED);
    }
}
