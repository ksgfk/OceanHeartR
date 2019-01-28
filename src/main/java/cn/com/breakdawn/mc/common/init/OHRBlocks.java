package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.common.block.BlockFirst;
import cn.com.breakdawn.mc.common.block.BlockNatureOre;
import cn.com.breakdawn.mc.util.RegBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * 初始化所有方块的类
 * KSGFK 创建于 2019/1/26
 */
public final class OHRBlocks {
    @RegBlock("first_block")
    public static final Block FIRST_BLOCK = new BlockFirst(Material.ROCK).setHarvestLevelReturnBlock("pickaxe", 0).setSoundType(SoundType.STONE).setHardness(2.0F).setResistance(10.0F);

    @RegBlock(value = "nature_ore", oreDict = "oreNature", className = "BlockNatureOre", isRegisterItemBlock = false, isRegisterMultiTextureBlock = true)
    public static final Block NATURE_ORE = new BlockNatureOre().setRegistryName("nature_ore").setUnlocalizedName("nature_ore");
}
