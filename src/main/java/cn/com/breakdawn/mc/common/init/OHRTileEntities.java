package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.tile.TileDynamoNature;
import cn.com.breakdawn.mc.common.tile.TilePulverizer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class OHRTileEntities {
    public static void init() {
        GameRegistry.registerTileEntity(TileDynamoNature.class, new ResourceLocation(OceanHeartR.MODID + ":" + "dynamo_nature"));
        GameRegistry.registerTileEntity(TilePulverizer.class, new ResourceLocation(OceanHeartR.MODID + ":" + "pulverizer"));
    }
}
