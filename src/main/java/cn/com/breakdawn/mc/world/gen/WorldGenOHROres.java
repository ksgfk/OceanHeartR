package cn.com.breakdawn.mc.world.gen;

import cn.com.breakdawn.mc.common.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenOHROres implements IWorldGenerator {
    private WorldGenerator nature_ore;

    public WorldGenOHROres() {
        nature_ore = new WorldGenMinable(Blocks.NATURE_ORE.getDefaultState(), 3);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
            case 0:
                GenCommonOres.overWorld(nature_ore, world, random, chunkX, chunkZ, 8, 6, 30);
                break;
            case -1:
                break;
            case 1:
                break;
        }
    }
}
