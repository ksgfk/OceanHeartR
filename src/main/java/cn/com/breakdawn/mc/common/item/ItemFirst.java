package cn.com.breakdawn.mc.common.item;

import cn.com.breakdawn.mc.client.CreativeTabsOHR;
import net.minecraft.item.Item;

/**
 * 第一个物品,没啥用
 * KSGFK 创建于 2019/1/25
 */
public class ItemFirst extends Item {
    public ItemFirst(String name) {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
    }
}
