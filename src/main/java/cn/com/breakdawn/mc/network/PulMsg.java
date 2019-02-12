package cn.com.breakdawn.mc.network;

import cn.com.breakdawn.mc.OceanHeartR;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @auther KSGFK
 */
public class PulMsg implements IMessage {
    private int energy;
    private int maxEnergy;

    public PulMsg() {
    }

    public PulMsg(int energy, int maxEnergy) {
        this.energy = energy;
        this.maxEnergy = maxEnergy;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        String e = ByteBufUtils.readUTF8String(buf);
        String m = ByteBufUtils.readUTF8String(buf);
        energy = Integer.parseInt(e);
        maxEnergy = Integer.parseInt(m);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, String.valueOf(energy));
        ByteBufUtils.writeUTF8String(buf, String.valueOf(maxEnergy));
    }

    public int getEnergy() {
        return energy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public static class Handler implements IMessageHandler<PulMsg, IMessage> {

        @Override
        public IMessage onMessage(PulMsg message, MessageContext ctx) {
            if (ctx.side == Side.CLIENT) {
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    OceanHeartR.getGui().getPul().setEnergy(message.getEnergy());
                    OceanHeartR.getGui().getPul().setMaxEnergy(message.getMaxEnergy());
                });
            }
            return null;
        }
    }
}
