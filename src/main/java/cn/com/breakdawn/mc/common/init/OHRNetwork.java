package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.network.*;
import net.minecraftforge.fml.relauncher.Side;

public class OHRNetwork {
    public static void init() {
        OceanHeartR.getNetwork().registerMessage(RedPacketMessage.Handler.class, RedPacketMessage.class, 235, Side.CLIENT);
        OceanHeartR.getNetwork().registerMessage(RedPacketMessage.Handler.class, RedPacketMessage.class, 236, Side.SERVER);
        OceanHeartR.getNetwork().registerMessage(DynNatureMsg.Handler.class, DynNatureMsg.class, 237, Side.CLIENT);
        OceanHeartR.getNetwork().registerMessage(DynNatureMsg.Handler.class, DynNatureMsg.class, 238, Side.SERVER);
        OceanHeartR.getNetwork().registerMessage(PulMsg.Handler.class, PulMsg.class, 239, Side.CLIENT);
        OceanHeartR.getNetwork().registerMessage(PulMsg.Handler.class, PulMsg.class, 240, Side.SERVER);
        OceanHeartR.getNetwork().registerMessage(PurifyMsg.Handler.class, PurifyMsg.class, 241, Side.CLIENT);
        OceanHeartR.getNetwork().registerMessage(PurifyMsg.Handler.class, PurifyMsg.class, 242, Side.SERVER);
        OceanHeartR.getNetwork().registerMessage(PhiMsg.Handler.class, PhiMsg.class, 243, Side.CLIENT);
        OceanHeartR.getNetwork().registerMessage(PhiMsg.Handler.class, PhiMsg.class, 244, Side.SERVER);
    }
}
