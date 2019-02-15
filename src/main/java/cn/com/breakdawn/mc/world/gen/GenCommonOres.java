package cn.com.breakdawn.mc.world.gen;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;

import java.util.Random;

/**
 * 生成普通矿石
 * KSGFK 创建于2019/1/26
 */
public class GenCommonOres {
    /**
     * 生成矿石
     *
     * @param event     触发的生成事件
     * @param generator 生成器
     * @param number    每个区块预期生成数量
     * @param minHeight 最小高度
     * @param maxHeight 最大高度
     */
    @Deprecated
    static void gen(OreGenEvent.GenerateMinable event, WorldGenerator generator, int number, int minHeight, int maxHeight) {
        for (int i = 0; i < number; i++) {
            int posX = event.getPos().getX() + event.getRand().nextInt(16);
            int posY = minHeight + event.getRand().nextInt(maxHeight);
            int posZ = event.getPos().getZ() + event.getRand().nextInt(16);
            BlockPos blockpos = new BlockPos(posX, posY, posZ);
            generator.generate(event.getWorld(), event.getRand(), blockpos);
        }
    }

    static void gen(WorldGenerator gen, World world, Random random, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight) {
        if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException("矿物生成超出世界边界");

        int x, y, z;

        int heightDiff = maxHeight - minHeight + 1;
        for (int a = 0; a < chance; a++) {
            x = chunkX * 16 + random.nextInt(16);
            y = minHeight + random.nextInt(heightDiff);
            z = chunkZ * 16 + random.nextInt(16);

            gen.generate(world, random, new BlockPos(x, y, z));
        }
    }
}
