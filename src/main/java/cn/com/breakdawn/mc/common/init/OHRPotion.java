package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.common.potion.PotionBleed;
import cn.com.breakdawn.mc.common.potion.PotionFeed;
import cn.com.breakdawn.mc.common.potion.PotionOxygen;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

/**
 * @author KSGFK create in 2019/2/20
 */
public class OHRPotion {
    public static final Potion BLEED = new PotionBleed();
    public static final Potion FEED = new PotionFeed();
    public static final Potion OXYGEN = new PotionOxygen();

    public static void init() {
        ForgeRegistries.POTIONS.register(BLEED);
        ForgeRegistries.POTIONS.register(FEED);
        ForgeRegistries.POTIONS.register(OXYGEN);
    }
}
