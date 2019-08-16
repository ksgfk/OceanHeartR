package com.github.ksgfk.oceanheartr.common.init;

import com.github.ksgfk.oceanheartr.annotation.ModRegistry;
import com.github.ksgfk.oceanheartr.annotation.OreDict;
import com.github.ksgfk.oceanheartr.common.block.BlockOHRBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * @author KSGFK create in 2019/8/12
 */
@ModRegistry
public final class OHRBlocks {
    @OreDict(name = "blockOceanSoul")
    public static final Block OceanSoul = new BlockOHRBase("ocean_soul_block", Material.ICE);
    @OreDict(name = "oreOceanSoul")
    public static final Block OceanSoulOre = new BlockOHRBase("ocean_soul_ore", Material.ICE);
    @OreDict(name = "oreLavaSoul")
    public static final Block LavaSoulOre = new BlockOHRBase("lava_soul_ore", Material.LAVA);
    @OreDict(name = "oreLegend")
    public static final Block LegendOre = new BlockOHRBase("legend_ore", Material.ROCK);
    @OreDict(name = "oreVoid")
    public static final Block VoidOre = new BlockOHRBase("void_ore", Material.ROCK);
}
