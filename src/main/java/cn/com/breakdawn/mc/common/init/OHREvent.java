package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.OceanHeartR;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = OceanHeartR.MODID)
public class OHREvent {
    @SubscribeEvent
    public static void canPutInAnvil(AnvilUpdateEvent event) {
        if (event.getLeft().getItem().equals(OHRItems.VLAD_III)) {
            event.setCanceled(true);
        }
    }
}
