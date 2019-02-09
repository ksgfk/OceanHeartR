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
 * 给客户端发送能量数据
 *
 * @auther KSGFK
 */
public class DynNatureMsg implements IMessage {
    private int energy;
    private int maxEnergy;

    public DynNatureMsg() {
    }

    public DynNatureMsg(int energy, int maxEnergy) {
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

    public static class Handler implements IMessageHandler<DynNatureMsg, IMessage> {

        @Override
        public IMessage onMessage(DynNatureMsg message, MessageContext ctx) {
            if (ctx.side == Side.CLIENT) {
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    OceanHeartR.getGui().getDynNature().setEnergy(message.getEnergy());
                    OceanHeartR.getGui().getDynNature().setMaxEnergy(message.getMaxEnergy());
                });
            }
            return null;
        }
    }
}
