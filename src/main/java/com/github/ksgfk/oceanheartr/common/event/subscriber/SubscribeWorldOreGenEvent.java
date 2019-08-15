package com.github.ksgfk.oceanheartr.common.event.subscriber;

import com.github.ksgfk.oceanheartr.annotation.OreGenBusSubscriber;
import com.github.ksgfk.oceanheartr.common.init.OHRBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeOcean;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

/**
 * @author KSGFK create in 2019/8/13
 */
@OreGenBusSubscriber
public final class SubscribeWorldOreGenEvent {
    @SubscribeEvent
    public static void onOceanSoulOreGen(OreGenEvent.Pre event) {
        World worldIn = event.getWorld();
        if (worldIn.isRemote) {
            return;
        }
        BlockPos position = event.getPos();
        if (worldIn.getBiome(position).getBiomeClass() != BiomeOcean.class) {
            return;
        }
        Random rand = event.getRand();

        WorldGenerator g = new WorldGenMinable(OHRBlocks.OceanSoulOre.getDefaultState(), 12, (block) -> {
            if (block == null) {
                return false;
            }
            return block.getBlock() == Blocks.GRAVEL
                    || block.getMaterial() == Material.ROCK
                    || block.getMaterial() == Material.SAND;
        });
        for (int i = 0; i < 4; i++) {
            int x = position.getX() + rand.nextInt(16);
            int y = 15;
            int z = position.getZ() + rand.nextInt(16);
            BlockPos pos = new BlockPos(x, y, z);
            while (worldIn.getBlockState(pos).getMaterial() != Material.WATER) {
                if (pos.getY() > 128) {
                    break;
                }
                pos = pos.up();
            }
            g.generate(worldIn, rand, pos);
        }
    }
}
