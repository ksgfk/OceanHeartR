package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.common.item.FirstItem;
import net.minecraft.item.Item;

/**
 * 初始化所有物品的类
 * KSGFK 创建于 2019/1/25
 */
public final class Items {
    public static final Item FIRST_ITEM = new FirstItem().setRegistryName("first_item");
}
