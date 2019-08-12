package com.github.ksgfk.oceanheartr;

import com.github.ksgfk.oceanheartr.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = OceanHeartR.MOD_ID,
        name = OceanHeartR.MOD_NAME,
        version = OceanHeartR.VERSION,
        acceptedMinecraftVersions = "1.12.2"
)
public enum OceanHeartR {
    INSTANCE;

    public static final String MOD_ID = "oceanheartr";
    public static final String MOD_NAME = "OceanHeartR";
    public static final String VERSION = "@version@";
    public static final String CLIENT = "com.github.ksgfk.oceanheartr.client.ClientProxy";
    public static final String COMMON = "com.github.ksgfk.oceanheartr.common.CommonProxy";

    @Mod.InstanceFactory
    public static OceanHeartR getInstance() {
        return INSTANCE;
    }

    @SidedProxy(clientSide = OceanHeartR.CLIENT, serverSide = OceanHeartR.COMMON, modId = OceanHeartR.MOD_ID)
    public static CommonProxy proxy;

    public static Logger logger = LogManager.getLogger(OceanHeartR.MOD_NAME);

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) throws ClassNotFoundException, IllegalAccessException {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
        proxy.loadComplete(event);
    }
}
