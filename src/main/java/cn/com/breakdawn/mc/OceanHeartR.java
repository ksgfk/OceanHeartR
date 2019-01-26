/*
  命名规范:
  所有包名全部小写
  所有类名全部驼峰式
  所有方法名 第一个词语首字母小写,第二个以后词语的首字母大写
  所有类中的全局静态变量全部大写

  所有物品类都以Item+物品名作为名字
  所有方块类都以Block+方块名作为名字
 */
package cn.com.breakdawn.mc;

import cn.com.breakdawn.mc.common.CommonProxy;
import cn.com.breakdawn.mc.world.gen.WorldGenOHROres;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = OceanHeartR.MODID, name = OceanHeartR.NAME, version = OceanHeartR.VERSION)
public class OceanHeartR {
    public static final String MODID = "oceanheartr";
    public static final String NAME = "Ocean Heart R";
    public static final String VERSION = "@version@";

    public static final String CLIENT = "cn.com.breakdawn.mc.client.ClientProxy";
    public static final String COMMON = "cn.com.breakdawn.mc.common.CommonProxy";

    private static Logger logger = LogManager.getLogger("OceanHeartR");

    @SidedProxy(clientSide = OceanHeartR.CLIENT, serverSide = OceanHeartR.COMMON, modId = OceanHeartR.MODID)
    public static CommonProxy proxy;

    public WorldGenOHROres oresGen;

    public static Logger getLogger() {
        return logger;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(proxy);
        oresGen = new WorldGenOHROres();
        GameRegistry.registerWorldGenerator(oresGen, 0);
    }
}
