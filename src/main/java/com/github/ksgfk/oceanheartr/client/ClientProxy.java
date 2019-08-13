package com.github.ksgfk.oceanheartr.client;

import com.github.ksgfk.oceanheartr.OceanHeartR;
import com.github.ksgfk.oceanheartr.common.CommonProxy;
import com.github.ksgfk.oceanheartr.common.manager.RegisterManager;
import net.minecraft.block.Block;
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
            registerModel(registerItem, registerItem.getRegistryName());
        }
    }

    @SubscribeEvent
    public static void registerBlockModels(ModelRegistryEvent event) {
        RegisterManager rm = Optional.ofNullable(RegisterManager.getInstance()).orElseThrow(NullPointerException::new);
        for (Block registerBlock : rm.getBlocks()) {
            registerModel(Item.getItemFromBlock(registerBlock), registerBlock.getRegistryName());
        }
    }

    private static void registerModel(Item item, ResourceLocation rl) {
        if (rl == null) {
            OceanHeartR.logger.error("null ResourceLocation:" + item);
            throw new IllegalArgumentException("null ResourceLocation:" + item);
        }
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(rl, "inventory"));
    }
}
