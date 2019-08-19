package com.github.ksgfk.oceanheartr.api.event;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

/**
 * @author KSGFK create in 2019/8/19
 */
public class BreakOHRBlock extends BlockEvent {
    public BreakOHRBlock(World world, BlockPos pos, IBlockState state) {
        super(world, pos, state);
    }
}
