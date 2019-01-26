package cn.com.breakdawn.mc.world.gen;

import cn.com.breakdawn.mc.OceanHeartR;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class GenCommonOres {
    static void overWorld(WorldGenerator gen, World world, Random random, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight) {
        if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException("矿物生成超出世界边界");

        int x, y, z;

        BlockPos genPos;

        int heightDiff = maxHeight - minHeight + 1;
        for (int a = 0; a < chance; a++) {
            x = chunkX * 16 + random.nextInt(16);
            y = minHeight + random.nextInt(heightDiff);
            z = chunkZ * 16 + random.nextInt(16);

            genPos = new BlockPos(x, y, z);
            gen.generate(world, random, genPos);
            OceanHeartR.getLogger().info("自然矿石生成坐标:" + genPos.toString());
        }
    }
}
