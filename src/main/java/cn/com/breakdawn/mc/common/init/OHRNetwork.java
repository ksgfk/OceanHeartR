package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.network.RedPacketMessage;
import net.minecraftforge.fml.relauncher.Side;

public class OHRNetwork {
    public static void init() {
        OceanHeartR.getNetwork().registerMessage(RedPacketMessage.Handler.class, RedPacketMessage.class, 235, Side.CLIENT);
        OceanHeartR.getNetwork().registerMessage(RedPacketMessage.Handler.class, RedPacketMessage.class, 236, Side.SERVER);
    }
}
