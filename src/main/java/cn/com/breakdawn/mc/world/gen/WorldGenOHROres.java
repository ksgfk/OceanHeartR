package cn.com.breakdawn.mc.world.gen;

import cn.com.breakdawn.mc.common.block.BlockNatureOre;
import cn.com.breakdawn.mc.common.init.OHRBlocks;
import com.google.common.base.Predicate;
import net.minecraft.block.state.IBlockState;
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

    public WorldGenOHROres() {
        //blockCount最低只能填3...再低根本就不生成了
        nature_ore_ow = new WorldGenMinable(OHRBlocks.NATURE_ORE.getDefaultState().withProperty(BlockNatureOre.VARIANT, BlockNatureOre.EnumType.OVERWORLD), 3, BlockMatcher.forBlock(Blocks.STONE));
        nature_ore_nether = new WorldGenMinable(OHRBlocks.NATURE_ORE.getDefaultState().withProperty(BlockNatureOre.VARIANT, BlockNatureOre.EnumType.NETHER), 3, new NetherPredicate());
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (!world.isRemote) {
            switch (world.provider.getDimensionType()) {
                case NETHER:
                    GenCommonOres.gen(nature_ore_nether, world, random, chunkX, chunkZ, 64, 5, 255);
                    break;
                case OVERWORLD:
                    GenCommonOres.gen(nature_ore_ow, world, random, chunkX, chunkZ, 24, 5, 50);
                    break;
                //case THE_END:
                //    GenCommonOres.gen(nature_ore_nether, world, random, chunkX, chunkZ, 10, 5, 30);
                //    break;
            }
        }
    }

    static class NetherPredicate implements Predicate<IBlockState> {
        private NetherPredicate() {
        }

        public boolean apply(IBlockState p_apply_1_) {
            return p_apply_1_ != null && p_apply_1_.getBlock() == Blocks.NETHERRACK;
        }
    }
}
