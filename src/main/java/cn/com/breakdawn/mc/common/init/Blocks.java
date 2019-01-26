package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.common.block.BlockFirst;
import cn.com.breakdawn.mc.common.block.BlockNatureOre;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * 初始化所有方块的类
 * KSGFK 创建于 2019/1/26
 */
public final class Blocks {
    public static final Block FIRST_BLOCK = new BlockFirst(Material.ROCK, "first_block");
    public static final Block NATURE_ORE = new BlockNatureOre(Material.ROCK, "nature_ore");
}
