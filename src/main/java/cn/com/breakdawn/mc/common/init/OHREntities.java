package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.entity.EntityGodBrick;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

/**
 * @author KSGFK create in 2019/5/2
 */
public class OHREntities {
    public static void Init(RegistryEvent.Register<EntityEntry> event) {
        event.getRegistry().register(
                EntityEntryBuilder
                        .create()
                        .entity(EntityGodBrick.class)
                        .id(new ResourceLocation(OceanHeartR.MODID, "god_brick"), 16)
                        .name("godBrick")
                        .tracker(64, 3, true)
                        .build());
    }
}
