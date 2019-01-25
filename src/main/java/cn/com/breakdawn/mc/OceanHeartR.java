/*
  代码规范:
  所有包名全部小写
  所有类名全部驼峰式
  所有方法名 第一个词语首字母小写,第二个以后词语的首字母大写
  所有类中的全局静态变量全部大写
 */
package cn.com.breakdawn.mc;

import cn.com.breakdawn.mc.common.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = OceanHeartR.MODID, name = OceanHeartR.NAME, version = OceanHeartR.VERSION)
public class OceanHeartR {
    public static final String MODID = "oceanheartr";
    public static final String NAME = "Ocean Heart R";
    public static final String VERSION = "@version@";

    public static final String CLIENT = "cn.com.breakdawn.mc.client.ClientProxy";
    public static final String COMMON = "cn.com.breakdawn.mc.common.CommonProxy";

    private static Logger logger;

    @SidedProxy(clientSide = OceanHeartR.CLIENT, serverSide = OceanHeartR.COMMON, modId = OceanHeartR.MODID)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(proxy);
    }
}
