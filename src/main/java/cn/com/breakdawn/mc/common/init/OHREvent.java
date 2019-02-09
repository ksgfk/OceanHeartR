package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.OceanHeartR;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = OceanHeartR.MODID)
public class OHREvent {
    public static final String angleSworldL1 = "item.item.angle_sword_l1";
    /*
    @SubscribeEvent
    public static void cancelPlayerDropAngleSword(PlayerDropsEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        List<EntityItem> items = event.getDrops();
    }
    */
}
