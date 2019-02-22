package cn.com.breakdawn.mc.world.gen;

import cn.com.breakdawn.mc.common.block.BlockNatureOre;
import cn.com.breakdawn.mc.common.block.BlockStrangeOre;
import cn.com.breakdawn.mc.common.init.OHRBlocks;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * 在地图上生成本Mod所有矿物的类
 * KSGFK 创建于2019/1/26
 */
public class WorldGenOHROres implements IWorldGenerator {
    private static WorldGenerator nature_ore_ow;
    private static WorldGenerator nature_ore_nether;

    private static WorldGenerator strange_gold;
    private static WorldGenerator strange_leve;
    private static WorldGenerator strange_ocean;
    private static WorldGenerator strange_end;

    public WorldGenOHROres() {
        //blockCount最低只能填3...再低根本就不生成了
        nature_ore_ow = new WorldGenMinable(OHRBlocks.NATURE_ORE.getDefaultState().withProperty(BlockNatureOre.VARIANT, BlockNatureOre.EnumType.OVERWORLD), 3, BlockMatcher.forBlock(Blocks.STONE));
        nature_ore_nether = new WorldGenMinable(OHRBlocks.NATURE_ORE.getDefaultState().withProperty(BlockNatureOre.VARIANT, BlockNatureOre.EnumType.NETHER), 3, BlockMatcher.forBlock(Blocks.NETHERRACK));

        strange_gold = new WorldGenMinable(OHRBlocks.STRANGE_ORE.getDefaultState().withProperty(BlockStrangeOre.VARIANT, BlockStrangeOre.EnumType.GOLD), 3, BlockMatcher.forBlock(Blocks.STONE));
        strange_leve = new WorldGenMinable(OHRBlocks.STRANGE_ORE.getDefaultState().withProperty(BlockStrangeOre.VARIANT, BlockStrangeOre.EnumType.LEVE_SOUL), 3, BlockMatcher.forBlock(Blocks.NETHERRACK));
        strange_ocean = new WorldGenMinable(OHRBlocks.STRANGE_ORE.getDefaultState().withProperty(BlockStrangeOre.VARIANT, BlockStrangeOre.EnumType.OCEAN_SOUL), 10, BlockMatcher.forBlock(Blocks.STONE));
        strange_end = new WorldGenMinable(OHRBlocks.STRANGE_ORE.getDefaultState().withProperty(BlockStrangeOre.VARIANT, BlockStrangeOre.EnumType.END), 3, BlockMatcher.forBlock(Blocks.END_STONE));
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (!world.isRemote) {
            switch (world.provider.getDimensionType()) {
                case NETHER:
                    GenCommonOres.gen(nature_ore_nether, world, random, chunkX, chunkZ, 64, 5, 255);
                    GenCommonOres.gen(strange_leve, world, random, chunkX, chunkZ, 16, 5, 255);
                    break;
                case OVERWORLD:
                    GenCommonOres.gen(nature_ore_ow, world, random, chunkX, chunkZ, 12, 5, 50);
                    GenCommonOres.gen(strange_gold, world, random, chunkX, chunkZ, 1, 5, 30);
                    GenCommonOres.genInOcean(strange_ocean, world, random, chunkX, chunkZ, 1, 35, 70);
                    break;
                case THE_END:
                    GenCommonOres.gen(strange_end, world, random, chunkX, chunkZ, 4, 0, 255);
                    break;
            }
        }
    }
}
