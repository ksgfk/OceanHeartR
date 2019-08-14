package com.github.ksgfk.oceanheartr.client;

import com.github.ksgfk.oceanheartr.OceanHeartR;
import com.github.ksgfk.oceanheartr.annotation.EntityModelRegistry;
import com.github.ksgfk.oceanheartr.common.CommonProxy;
import com.github.ksgfk.oceanheartr.common.manager.RegisterManager;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.InvocationTargetException;

/**
 * @author KSGFK create in 2019/8/12
 */
@Mod.EventBusSubscriber
public class ClientProxy extends CommonProxy {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerItemModels(ModelRegistryEvent event) {
        RegisterManager rm = checkRegisterManager();
        for (Item registerItem : rm.getItems()) {
            registerModel(registerItem, registerItem.getRegistryName());
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerBlockModels(ModelRegistryEvent event) {
        RegisterManager rm = checkRegisterManager();
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

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    @SubscribeEvent
    public static void bindEntityRenderer(ModelRegistryEvent event) {
        RegisterManager rm = checkRegisterManager();
        rm.getOhrEntityModels().forEach((asmData -> {
            try {
                Class<?> renderClass = Class.forName(asmData.getClassName());
                EntityModelRegistry annotation = renderClass.getAnnotation(EntityModelRegistry.class);
                RenderingRegistry.registerEntityRenderingHandler(annotation.entityClass(), manager -> {
                    try {
                        return (Render<? super Entity>) renderClass.getConstructor(RenderManager.class).newInstance(manager);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    throw new IllegalArgumentException("无法实例化Render:" + renderClass.getName());
                });
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }));
    }
}
