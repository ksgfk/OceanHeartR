package com.github.ksgfk.oceanheartr.api.event;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

/**
 * @author KSGFK create in 2019/8/15
 */
@Cancelable
public class OHRBlockActiveEvent extends BlockEvent {
    private final EntityPlayer player;
    private final EnumHand hand;
    private final EnumFacing facing;
    private final float hitX;
    private final float hitY;
    private final float hitZ;

    public OHRBlockActiveEvent(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        super(world, pos, state);
        this.player = player;
        this.hand = hand;
        this.facing = facing;
        this.hitX = hitX;
        this.hitY = hitY;
        this.hitZ = hitZ;
    }

    public EntityPlayer getPlayer() {
        return player;
    }

    public EnumHand getHand() {
        return hand;
    }

    public EnumFacing getFacing() {
        return facing;
    }

    public float getHitX() {
        return hitX;
    }

    public float getHitY() {
        return hitY;
    }

    public float getHitZ() {
        return hitZ;
    }
}
