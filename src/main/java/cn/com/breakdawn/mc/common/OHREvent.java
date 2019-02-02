package cn.com.breakdawn.mc.common;

import cn.com.breakdawn.mc.OceanHeartR;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

@EventBusSubscriber(modid = OceanHeartR.MODID)
public class OHREvent {
    public static final String angleSworldL1 = "item.item.angle_sword_l1";

    @SubscribeEvent
    public static void cancelPlayerDropAngleSword(PlayerDropsEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        List<EntityItem> items = event.getDrops();
    }
}
