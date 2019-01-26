package cn.com.breakdawn.mc.client;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.init.Items;
import net.minecraft.item.ItemStack;

/**
 * 创造模式物品栏
 * KSGFK 创建于 2019/1/26
 */
public class CreativeTabsOHR extends net.minecraft.creativetab.CreativeTabs {
    public static CreativeTabsOHR tabsOceanHeart = new CreativeTabsOHR();

    public CreativeTabsOHR() {
        super(OceanHeartR.MODID);
    }

    /**
     * 设置物品栏的图标,会使用返回物品的贴图
     *
     * @return 物品
     */
    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Items.NATURE_SWORD);
    }
}
