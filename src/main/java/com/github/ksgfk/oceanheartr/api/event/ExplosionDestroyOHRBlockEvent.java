package com.github.ksgfk.oceanheartr.api.event;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

/**
 * @author KSGFK create in 2019/8/16
 */
public class ExplosionDestroyOHRBlockEvent extends BlockEvent {
    private final Explosion explosion;

    public ExplosionDestroyOHRBlockEvent(World world, BlockPos pos, IBlockState state, Explosion explosion) {
        super(world, pos, state);
        this.explosion = explosion;
    }

    public Explosion getExplosion() {
        return explosion;
    }
}
