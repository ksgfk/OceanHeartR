package com.github.ksgfk.oceanheartr.client;

import com.github.ksgfk.oceanheartr.OceanHeartR;
import com.github.ksgfk.oceanheartr.common.CommonProxy;
import com.github.ksgfk.oceanheartr.common.manager.RegisterManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Optional;

/**
 * @author KSGFK create in 2019/8/12
 */
@Mod.EventBusSubscriber
public class ClientProxy extends CommonProxy {
    @SubscribeEvent
    public static void registerItemModels(ModelRegistryEvent event) {
        RegisterManager rm = Optional.ofNullable(RegisterManager.getInstance()).orElseThrow(NullPointerException::new);
        for (Item registerItem : rm.getItems()) {
            ResourceLocation rl = registerItem.getRegistryName();
            if (rl == null) {
                OceanHeartR.logger.error("null ResourceLocation" + registerItem);
                continue;
            }
            ModelLoader.setCustomModelResourceLocation(registerItem, 0, new ModelResourceLocation(rl, "inventory"));
        }
    }
}
