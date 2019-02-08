package cn.com.breakdawn.mc.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * @auther KSGFK
 */
public class DynNatureMsg implements IMessage {
    private int energy;

    public DynNatureMsg() {
    }

    public DynNatureMsg(int energy) {
        this.energy = energy;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        energy = ByteBufUtils.readVarInt(buf, Integer.MAX_VALUE);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeVarInt(buf, energy, Integer.MAX_VALUE);
    }

    public int getEnergy() {
        return energy;
    }

    public static class Handler implements IMessageHandler<DynNatureMsg, IMessage> {

        @Override
        public IMessage onMessage(DynNatureMsg message, MessageContext ctx) {
            return null;
        }
    }
}
