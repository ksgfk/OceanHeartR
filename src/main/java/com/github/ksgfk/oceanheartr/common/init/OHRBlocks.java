package com.github.ksgfk.oceanheartr.common.init;

import com.github.ksgfk.oceanheartr.annotation.ModRegistry;
import com.github.ksgfk.oceanheartr.common.block.BlockOHRBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * @author KSGFK create in 2019/8/12
 */
@ModRegistry
public final class OHRBlocks {
    public static final Block OceanSoul = new BlockOHRBase("ocean_soul_block", Material.ICE);
}
