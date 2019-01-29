/*
  OceanHeartR Copyright (C) 2019 KSGFK
  This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
  This is free software, and you are welcome to redistribute it
  nder certain conditions; type `show c' for details.

  The hypothetical commands `show w' and `show c' should show the appropriate
  parts of the General Public License.  Of course, your program's commands
  might be different; for a GUI interface, you would use an "about box".

  You should also get your employer (if you work as a programmer) or school,
  if any, to sign a "copyright disclaimer" for the program, if necessary.
  For more information on this, and how to apply and follow the GNU GPL, see
  <https://www.gnu.org/licenses/>.

  The GNU General Public License does not permit incorporating your program
  into proprietary programs.  If your program is a subroutine library, you
  may consider it more useful to permit linking proprietary applications with
  the library.  If this is what you want to do, use the GNU Lesser General
  Public License instead of this License.  But first, please read
  <https://www.gnu.org/licenses/why-not-lgpl.html>.
 */
package cn.com.breakdawn.mc;

import cn.com.breakdawn.mc.common.CommonProxy;
import cn.com.breakdawn.mc.world.gen.WorldGenOHROres;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
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

    public static Logger getLogger() {
        return logger;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(proxy);
        MinecraftForge.ORE_GEN_BUS.register(WorldGenOHROres.class);
    }
}
