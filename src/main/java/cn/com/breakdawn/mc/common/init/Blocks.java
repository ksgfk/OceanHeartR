package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.common.block.FirstBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * 注册方块
 * KSGFK 创建于 2019/1/26
 */
public final class Blocks {
    public static final Block FIRST_BLOCK = new FirstBlock(Material.ROCK).setRegistryName("first_block");
}
