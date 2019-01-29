package cn.com.breakdawn.mc.world.gen;

import cn.com.breakdawn.mc.common.init.OHRBlocks;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 在地图上生成本Mod所有矿物的类
 * KSGFK 创建于2019/1/26
 */
public class WorldGenOHROres {
    private static WorldGenerator nature_ore = new WorldGenMinable(OHRBlocks.NATURE_ORE.getDefaultState(), 3);

    @SubscribeEvent
    public static void onGenerateMinable(OreGenEvent.GenerateMinable event) {
        if (event.getType() != OreGenEvent.GenerateMinable.EventType.DIAMOND)
            return;
        if (!TerrainGen.generateOre(event.getWorld(), event.getRand(), nature_ore, event.getPos(), OreGenEvent.GenerateMinable.EventType.CUSTOM))
            return;
        GenCommonOres.overWorld(event, nature_ore, 8, 5, 30);
    }
    /*
    @SubscribeEvent
    public static void onGenerateDIM1(OreGenEvent.GenerateMinable event) {
        if (event.getType() == OreGenEvent.GenerateMinable.EventType.QUARTZ) {
            //OceanHeartR.getLogger().info("生成石英");
        }
    }
    */
}
