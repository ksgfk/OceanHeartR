package com.github.ksgfk.oceanheartr.common;

import com.github.ksgfk.oceanheartr.OceanHeartR;
import com.github.ksgfk.oceanheartr.common.manager.RegisterManager;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author KSGFK create in 2019/8/12
 */
@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) throws ClassNotFoundException, IllegalAccessException {
        RegisterManager rm = Optional.ofNullable(RegisterManager.getInstance()).orElseThrow(NullPointerException::new);
        rm.register(event.getAsmData());
    }

    public void init(FMLInitializationEvent event) {
        RegisterManager rm = Optional.ofNullable(RegisterManager.getInstance()).orElseThrow(NullPointerException::new);
        rm.getOreDict().forEach(RegisterManager::registerOreDict);
    }

    public void loadComplete(FMLLoadCompleteEvent event) {
        RegisterManager.dispose();
        OceanHeartR.logger.info("加载完毕，清理资源");
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        RegisterManager rm = Optional.ofNullable(RegisterManager.getInstance()).orElseThrow(NullPointerException::new);
        event.getRegistry().registerAll(rm.getItems());
        event.getRegistry().registerAll(Arrays.stream(rm.getBlocks())
                .map(block -> {
                    ItemBlock i = new ItemBlock(block);
                    ResourceLocation rl = block.getRegistryName();
                    if (rl == null) {
                        OceanHeartR.logger.error("null ResourceLocation:" + block);
                        return null;
                    }
                    i.setRegistryName(rl);
                    return i;
                })
                .toArray(Item[]::new));
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        RegisterManager rm = Optional.ofNullable(RegisterManager.getInstance()).orElseThrow(NullPointerException::new);
        event.getRegistry().registerAll(rm.getBlocks());
    }
}
