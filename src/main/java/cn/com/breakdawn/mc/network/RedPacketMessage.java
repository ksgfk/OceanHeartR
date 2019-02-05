/*
  本源代码从ChinaCraft2仓库复制,开源地址:https://github.com/UnknownStudio/ChinaCraft2
  ChinaCraft2 Copyright (C) 2017 Unknown Domain
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
package cn.com.breakdawn.mc.network;

import cn.com.breakdawn.mc.util.Util;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Red packet message class.
 * 红包信息类。
 */
public class RedPacketMessage implements IMessage {
    /**
     * Packet sender
     * 红包发送人
     */
    private String sender;

    /**
     * Packet wish message
     * 红包祝福语
     */
    private String wish;

    /**
     * Packet receiver
     * 红包接收者
     */
    private String receiver;

    /**
     * Packet send status
     * 红包发送状态
     */
    private boolean isSend;

    public RedPacketMessage() {
    }

    public RedPacketMessage(String sender, String wish, String receiver, boolean isSend) {
        this.sender = sender;
        this.wish = wish;
        this.receiver = receiver;
        this.isSend = isSend;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        sender = ByteBufUtils.readUTF8String(buf);
        wish = ByteBufUtils.readUTF8String(buf);
        receiver = ByteBufUtils.readUTF8String(buf);
        isSend = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, sender);
        ByteBufUtils.writeUTF8String(buf, wish);
        ByteBufUtils.writeUTF8String(buf, receiver);
        buf.writeBoolean(isSend);
    }

    public boolean isSend() {
        return isSend;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSender() {
        return sender;
    }

    public String getWish() {
        return wish;
    }

    public static class Handler implements IMessageHandler<RedPacketMessage, IMessage> {

        @Override
        public IMessage onMessage(RedPacketMessage message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().player;
            ItemStack itemStack = player.inventory.getCurrentItem();

//          FIXME  if (itemStack == null || itemStack.getItem() != ChinaCraft.redPacket)return null;

            player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Blocks.AIR));

            itemStack = itemStack.copy();
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setTag("wish", new NBTTagString(message.getWish()));
            nbt.setTag("sender", new NBTTagString(message.getSender()));
            itemStack.setTagInfo("redpacket", nbt);

            String receiver = message.getReceiver();
            if (!message.isSend() || receiver == null || receiver.isEmpty()) {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, itemStack);
                return null;
            }

            EntityPlayer reciverPlayer = Util.getPlayerByName(receiver);
            if (reciverPlayer == null) {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, itemStack);
                player.sendMessage(new TextComponentTranslation("redPacket.notFoundPlayer", receiver));
                return null;
            }

            if (reciverPlayer.inventory.addItemStackToInventory(itemStack)) {
                player.sendMessage(new TextComponentTranslation("redPacket.success", receiver));
                reciverPlayer.sendMessage(new TextComponentTranslation("redPacket.received", message.getSender()));
                return null;
            } else {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, itemStack);
                player.sendMessage(new TextComponentTranslation("redPacket.backpackFull", receiver));
                return null;
            }
        }
    }
}
