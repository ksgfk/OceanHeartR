package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.common.block.*;
import cn.com.breakdawn.mc.util.RegBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * 初始化所有方块的类
 * KSGFK 创建于 2019/1/26
 */
public final class OHRBlocks {
    @RegBlock(value = "nature_ore", oreDict = "oreNature", className = "BlockNatureOre", isRegisterItemBlock = false, isRegisterMultiTextureBlock = true)
    public static final Block NATURE_ORE = new BlockNatureOre();

    @RegBlock(value = "strange_ore", oreDict = "oreStrange", className = "BlockStrangeOre", isRegisterItemBlock = false, isRegisterMultiTextureBlock = true)
    public static final Block STRANGE_ORE = new BlockStrangeOre();

    @RegBlock(value = "sapling",
            className = "BlockOHRSapling",
            isRegisterItemBlock = false,
            isRegisterMultiTextureBlock = true,
            enumPath = "cn.com.breakdawn.mc.common.block.BlockOHRPlanks$EnumType")
    public static final Block SAPLING = new BlockOHRSapling();

    @RegBlock("decoration_black")
    public static final Block DECORATION_BLACK = new BlockDecoration();

    @RegBlock("decoration_blue")
    public static final Block DECORATION_BLUE = new BlockDecoration();

    @RegBlock("decoration_black_blue")
    public static final Block DECORATION_BLACK_BLUE = new BlockDecoration();

    @RegBlock("decoration_mac_ir")
    public static final Block DECORATION_MAC_IR = new BlockBase(Material.IRON);

    @RegBlock("decoration_mac_st")
    public static final Block DECORATION_MAC_ST = new BlockBase(Material.IRON);

    @RegBlock("decoration_mac")
    public static final Block DECORATION_MAC = new BlockBase(Material.IRON);

    @RegBlock("decoration_ore_nouse")
    public static final Block DECORATION_ORE_NOUSE = new BlockBase(Material.IRON);
}
