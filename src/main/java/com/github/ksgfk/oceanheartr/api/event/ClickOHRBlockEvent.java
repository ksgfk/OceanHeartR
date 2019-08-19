package com.github.ksgfk.oceanheartr.api.event;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

/**
 * @author KSGFK create in 2019/8/15
 */
public class ClickOHRBlockEvent extends BlockEvent {
    private final EntityPlayer clicker;

    public ClickOHRBlockEvent(World world, BlockPos pos, IBlockState state, EntityPlayer clicker) {
        super(world, pos, state);
        this.clicker = clicker;
    }

    public EntityPlayer getClicker() {
        return clicker;
    }
}
