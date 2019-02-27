package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.common.enchantment.EnchSoulBond;
import net.minecraft.enchantment.Enchantment;

/**
 * @author KSGFK create in 2019/2/25
 */
public class OHREnch {
    public static final Enchantment SOUL_BOND = new EnchSoulBond();

    public static void init() {
        //CreativeTabsOHR.tabsOceanHeart.setRelevantEnchantmentTypes(SOUL_BOND.type);
    }
}
