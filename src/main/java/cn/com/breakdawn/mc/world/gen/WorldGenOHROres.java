package cn.com.breakdawn.mc.world.gen;

import cn.com.breakdawn.mc.common.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

/**
 * 在地图上生成本Mod所有矿物的类
 * KSGFK 创建于2019/1/26
 */
public class WorldGenOHROres {
    private static WorldGenerator nature_ore = new WorldGenMinable(Blocks.NATURE_ORE.getDefaultState(), 3);

    public WorldGenOHROres() {
        //nature_ore = new WorldGenMinable(Blocks.NATURE_ORE.getDefaultState(), 3);
    }

    @SubscribeEvent
    public static void onGenerateMinable(OreGenEvent.GenerateMinable event) {
        if (event.getType() != OreGenEvent.GenerateMinable.EventType.DIAMOND)
            return;
        if (!TerrainGen.generateOre(event.getWorld(), event.getRand(), nature_ore, event.getPos(), OreGenEvent.GenerateMinable.EventType.CUSTOM))
            return;
        GenCommonOres.overWorld(event, nature_ore, 8);
    }

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
