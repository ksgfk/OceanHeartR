package com.github.ksgfk.oceanheartr.common;

import com.github.ksgfk.oceanheartr.OceanHeartR;
import com.github.ksgfk.oceanheartr.client.ClientProxy;
import com.github.ksgfk.oceanheartr.common.manager.RegisterManager;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

import java.util.Optional;

/**
 * @author KSGFK create in 2019/8/12
 */
@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) throws ClassNotFoundException, IllegalAccessException {
        RegisterManager rm = checkRegisterManager();
        rm.register(event.getAsmData());
        rm.getOreGenSuber().forEach(MinecraftForge.ORE_GEN_BUS::register);
    }

    public void init(FMLInitializationEvent event) {
        RegisterManager rm = checkRegisterManager();
        rm.getOreDict().forEach(RegisterManager::registerOreDict);
        rm.getFurnace().forEach(RegisterManager::registerFurnaceSmelt);
    }

    public void loadComplete(FMLLoadCompleteEvent event) {
        RegisterManager.dispose();
        OceanHeartR.logger.info("加载完毕，清理资源");
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        RegisterManager rm = checkRegisterManager();
        event.getRegistry().registerAll(rm.getItems());
        event.getRegistry().registerAll(rm.getItemBlocks());
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        RegisterManager rm = checkRegisterManager();
        event.getRegistry().registerAll(rm.getBlocks());
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        RegisterManager rm = checkRegisterManager();
        event.getRegistry().registerAll(rm.getEntityEntries());
    }

    protected static RegisterManager checkRegisterManager() {
        return Optional.ofNullable(RegisterManager.getInstance()).orElseThrow(NullPointerException::new);
    }
}
