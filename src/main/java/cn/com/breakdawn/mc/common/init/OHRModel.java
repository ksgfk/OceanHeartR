package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.client.model.NatureChest;
import cn.com.breakdawn.mc.client.model.NatureHeltmet;
import net.minecraft.util.ResourceLocation;

/**
 * @author KSGFK create in 2019/2/21
 */
public class OHRModel {
    public static ResourceLocation LEGS_TEXTURE;
    public static ResourceLocation HEAD_TEXTURE;
    public static ResourceLocation CHEST_TEXTURE;
    public static NatureHeltmet HEAD_MODEL;
    public static NatureChest CHEST_MODEL;

    public static void init() {
        LEGS_TEXTURE = new ResourceLocation(OceanHeartR.MODID, "textures/armors/nature_leggings.png");
        HEAD_TEXTURE = new ResourceLocation(OceanHeartR.MODID, "textures/armors/nature_helmet.png");
        CHEST_TEXTURE = new ResourceLocation(OceanHeartR.MODID,"textures/armors/nature_chest.png");
        HEAD_MODEL = new NatureHeltmet();
        CHEST_MODEL = new NatureChest();
        OceanHeartR.getLogger().info("Model loaded successfully");
    }
}
