package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.network.DynNatureMsg;
import cn.com.breakdawn.mc.network.PulMsg;
import cn.com.breakdawn.mc.network.RedPacketMessage;
import net.minecraftforge.fml.relauncher.Side;

public class OHRNetwork {
    public static void init() {
        OceanHeartR.getNetwork().registerMessage(RedPacketMessage.Handler.class, RedPacketMessage.class, 235, Side.CLIENT);
        OceanHeartR.getNetwork().registerMessage(RedPacketMessage.Handler.class, RedPacketMessage.class, 236, Side.SERVER);
        OceanHeartR.getNetwork().registerMessage(DynNatureMsg.Handler.class, DynNatureMsg.class, 237, Side.CLIENT);
        OceanHeartR.getNetwork().registerMessage(DynNatureMsg.Handler.class, DynNatureMsg.class, 238, Side.SERVER);
        OceanHeartR.getNetwork().registerMessage(PulMsg.Handler.class, PulMsg.class, 239, Side.CLIENT);
        OceanHeartR.getNetwork().registerMessage(PulMsg.Handler.class, PulMsg.class, 240, Side.SERVER);
    }
}
