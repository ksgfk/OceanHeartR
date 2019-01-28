package cn.com.breakdawn.mc.util;

import net.minecraft.block.Block;

/**
 * 拥有多Metadata的方块必须实现此接口
 */
public interface IMetaBlockRender {
    /**
     * 渲染模型
     *
     * @param block 需要渲染的方块
     */
    void renderModel(Block block);
}
