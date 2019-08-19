package com.github.ksgfk.oceanheartr.common.event.subscriber;

import com.github.ksgfk.oceanheartr.annotation.OreGenBusSubscriber;
import com.github.ksgfk.oceanheartr.common.init.OHRBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
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
    private static WorldGenerator oceanSoul = new WorldGenMinable(OHRBlocks.OceanSoulOre.getDefaultState(),
            12,
            (block) -> {
                if (block == null) {
                    return false;
                }
                return block.getBlock() == Blocks.GRAVEL ||
                        block.getMaterial() == Material.ROCK ||
                        block.getMaterial() == Material.SAND;
            });
    private static WorldGenerator lavaSoul = new WorldGenMinable(OHRBlocks.LavaSoulOre.getDefaultState(),
            5,
            SubscribeWorldOreGenEvent::materialRock);
    private static WorldGenerator nature = new WorldGenMinable(OHRBlocks.NatureOre.getDefaultState(),
            3,
            SubscribeWorldOreGenEvent::stoneAndSand);
    private static WorldGenerator legend = new WorldGenMinable(OHRBlocks.LegendOre.getDefaultState(),
            6,
            SubscribeWorldOreGenEvent::stoneAndSand);
    private static WorldGenerator voidOre = new WorldGenMinable(OHRBlocks.VoidOre.getDefaultState(),
            3,
            SubscribeWorldOreGenEvent::endStone);

    @SubscribeEvent
    public static void onOceanSoulOreGen(OreGenEvent.Pre event) {
        World worldIn = event.getWorld();
        if (worldIn.isRemote) {
            return;
        }
        BlockPos position = event.getPos();
        ResourceLocation rl = worldIn.getBiome(position).getRegistryName();
        if (rl == null) {
            return;
        }
        if (!rl.getPath().contains("ocean")) {
            return;
        }
        Random rand = event.getRand();

        for (int i = 0; i < 2; i++) {
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
            oceanSoul.generate(worldIn, rand, pos);
        }
    }

    @SubscribeEvent
    public static void onLegendOreGen(OreGenEvent.Pre event) {
        World world = event.getWorld();
        if (world.isRemote) {
            return;
        }
        gen(legend, event.getWorld(), event.getRand(), event.getPos(), 5, 30, 1);
    }

    @SubscribeEvent
    public static void onNatureOreGen(OreGenEvent.Pre event) {
        World world = event.getWorld();
        if (world.isRemote) {
            return;
        }
        DimensionType type = world.provider.getDimensionType();
        if (type == DimensionType.NETHER || type == DimensionType.THE_END) {
            return;
        }
        gen(nature, event.getWorld(), event.getRand(), event.getPos(), 0, 255, 6);
    }

    @SubscribeEvent
    public static void onLavaSoulOreGen(OreGenEvent.Pre event) {
        World world = event.getWorld();
        if (world.isRemote) {
            return;
        }
        if (world.provider.getDimensionType() != DimensionType.NETHER) {
            return;
        }
        gen(lavaSoul, event.getWorld(), event.getRand(), event.getPos(), 0, 255, 4);
    }

    @SubscribeEvent
    public static void onVoidOreGen(OreGenEvent.Pre event) {
        World world = event.getWorld();
        if (world.isRemote) {
            return;
        }
        if (world.provider.getDimensionType() != DimensionType.THE_END) {
            return;
        }
        gen(voidOre, world, event.getRand(), event.getPos(), 5, 75, 8);
    }

    private static void gen(WorldGenerator gen, World world, Random random, BlockPos pos, int minHeight, int maxHeight, int chance) {
        if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256) {
            throw new IllegalArgumentException("矿物生成超出世界边界");
        }

        int heightDiff = maxHeight - minHeight + 1;
        for (int a = 0; a < chance; a++) {
            int x = pos.getX() + random.nextInt(16);
            int y = minHeight + random.nextInt(heightDiff);
            int z = pos.getZ() + random.nextInt(16);

            gen.generate(world, random, new BlockPos(x, y, z));
        }
    }

    private static boolean stoneAndSand(IBlockState block) {
        if (nullCheck(block)) return false;
        return block.getBlock() == Blocks.STONE || block.getMaterial() == Material.SAND;
    }

    private static boolean materialRock(IBlockState block) {
        if (nullCheck(block)) return false;
        return block.getMaterial() == Material.ROCK;
    }

    private static boolean endStone(IBlockState block) {
        if (nullCheck(block)) return false;
        return block.getBlock() == Blocks.END_STONE;
    }

    private static boolean nullCheck(IBlockState blockState) {//草，java这样写真难受
        return blockState == null;
    }
}
