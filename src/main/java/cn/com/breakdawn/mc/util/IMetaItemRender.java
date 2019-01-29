package cn.com.breakdawn.mc.util;

import net.minecraft.item.Item;

/**
 * 拥有多Metadata的物品必须实现此接口
 * KSGFK 创建于 2019/1/29
 */
public interface IMetaItemRender {
    /**
     * 渲染模型
     *
     * @param item 需要渲染的物品
     */
    void renderModel(Item item);
}
