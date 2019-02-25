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
 * @author KSGFK create in 2019/2/25
 */
public class PurifyMsg implements IMessage {
    private int energy;
    private int maxEnergy;
    private int process;

    public PurifyMsg() {
    }

    public PurifyMsg(int energy, int maxEnergy, int process) {
        this.energy = energy;
        this.maxEnergy = maxEnergy;
        this.process = process;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        String e = ByteBufUtils.readUTF8String(buf);
        String m = ByteBufUtils.readUTF8String(buf);
        String p = ByteBufUtils.readUTF8String(buf);
        energy = Integer.parseInt(e);
        maxEnergy = Integer.parseInt(m);
        process = Integer.parseInt(p);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, String.valueOf(energy));
        ByteBufUtils.writeUTF8String(buf, String.valueOf(maxEnergy));
        ByteBufUtils.writeUTF8String(buf, String.valueOf(process));
    }

    public int getEnergy() {
        return energy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getProcess() {
        return process;
    }

    public static class Handler implements IMessageHandler<PurifyMsg, IMessage> {

        @Override
        public IMessage onMessage(PurifyMsg message, MessageContext ctx) {
            if (ctx.side == Side.CLIENT) {
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    OceanHeartR.getGui().getPurify().setEnergy(message.getEnergy());
                    OceanHeartR.getGui().getPurify().setMaxEnergy(message.getMaxEnergy());
                    OceanHeartR.getGui().getPurify().setProcess(message.getProcess());
                });
            }
            return null;
        }
    }
}
