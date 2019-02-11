package cn.com.breakdawn.mc.common.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * 创造模式物品栏
 * KSGFK 创建于 2019/1/26
 */
public class CreativeTabsOHR {
    public static CreativeTabs tabsOceanHeart = new CreativeTabs("oceanheartr") {
        @Override
        public ItemStack getTabIconItem() {
            ItemStack stack = new ItemStack(OHRItems.STRANGE_INGOT);
            stack.setItemDamage(2);
            return stack;
        }
    };
}
