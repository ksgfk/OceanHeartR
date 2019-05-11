package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.client.model.*;
import net.minecraft.util.ResourceLocation;

/**
 * @author KSGFK create in 2019/2/21
 */
public class OHRModel {
    public static ResourceLocation LEGS_TEXTURE;
    public static ResourceLocation HEAD_TEXTURE;
    public static ResourceLocation CHEST_TEXTURE;
    public static ResourceLocation r_78_2_heltmet_texture;
    public static ResourceLocation r_78_2_chest_texture;
    public static ResourceLocation r_78_2_leggings_texture;
    public static NatureHeltmet HEAD_MODEL;
    public static NatureChest CHEST_MODEL;
    public static R_78_2_Heltmet r_78_2_heltmet;
    public static R_78_2_Chest r_78_2_chest;
    public static R_78_2_Leggings r_78_2_leggings;

    public static void init() {
        LEGS_TEXTURE = new ResourceLocation(OceanHeartR.MODID, "textures/armors/nature_leggings.png");
        HEAD_TEXTURE = new ResourceLocation(OceanHeartR.MODID, "textures/armors/nature_helmet.png");
        CHEST_TEXTURE = new ResourceLocation(OceanHeartR.MODID, "textures/armors/nature_chest.png");
        r_78_2_heltmet_texture = new ResourceLocation(OceanHeartR.MODID, "textures/armors/r_78_2_helmet.png");
        r_78_2_chest_texture = new ResourceLocation(OceanHeartR.MODID, "textures/armors/r_78_2_chestplate.png");
        r_78_2_leggings_texture = new ResourceLocation(OceanHeartR.MODID, "textures/armors/r_78_2_leggings.png");
        HEAD_MODEL = new NatureHeltmet();
        CHEST_MODEL = new NatureChest();
        r_78_2_heltmet = new R_78_2_Heltmet();
        r_78_2_chest = new R_78_2_Chest();
        r_78_2_leggings = new R_78_2_Leggings();
        OceanHeartR.getLogger().info("Model loaded successfully");
    }
}
