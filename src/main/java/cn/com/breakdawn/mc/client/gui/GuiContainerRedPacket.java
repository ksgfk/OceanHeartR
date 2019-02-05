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
package cn.com.breakdawn.mc.client.gui;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.inventory.ContainerRedPacket;
import cn.com.breakdawn.mc.network.RedPacketMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

@SideOnly(Side.CLIENT)
public class GuiContainerRedPacket extends GuiContainer {


    private GuiTextField wishTextBox;
    private GuiTextField receiverTextBox;
    private GuiButton buttonSend;

    private int wishColor = Integer.MAX_VALUE;
    private String sender = "", wish = I18n.format("gui.redPacket.wish");
    private int currentItem;
    private EntityPlayer player;

    public GuiContainerRedPacket(EntityPlayer player) {
        super(new ContainerRedPacket(player));
        this.player = player;
        ItemStack itemStack = player.getHeldItemMainhand();
        currentItem = player.inventory.currentItem;
        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("redpacket")) {
            NBTTagCompound redpacket = itemStack.getTagCompound().getCompoundTag("redpacket");
            sender = redpacket.getString("sender");
            wish = redpacket.getString("wish");
        }
        if (wish == null || wish.isEmpty()) wish = I18n.format("gui.redPacket.wish");
    }

    @Override
    public void initGui() {
        this.buttonList.clear();
        Keyboard.enableRepeatEvents(true);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        wishTextBox = new GuiTextField(0, Minecraft.getMinecraft().fontRenderer, this.xSize / 2 - 80, 65, 160, 16);
        wishTextBox.setFocused(true);
        wishTextBox.setMaxStringLength(64);
        wishTextBox.setText(wish);
        receiverTextBox = new GuiTextField(1, Minecraft.getMinecraft().fontRenderer, this.xSize / 2 - 166, 65, 64, 16);
        receiverTextBox.setMaxStringLength(32);
        this.buttonList.add(this.buttonSend = new GuiButton(0, k + this.xSize / 2 - 166, l + 89, 64, 20,
                I18n.format("gui.redPacket.send")));
        buttonSend.enabled = false;
        updateButton();
        super.initGui();
    }

    @Override
    public boolean doesGuiPauseGame() { // 让GUI在单人模式下不会暂停游戏保存存档
        return false;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (sender == null || sender.isEmpty() || sender.equalsIgnoreCase(player.getDisplayName().getFormattedText())) {
            wishTextBox.drawTextBox();
            receiverTextBox.drawTextBox();
            String head = I18n.format("gui.redPacket.title");
            this.fontRenderer.drawString(head, this.xSize / 2 - this.fontRenderer.getStringWidth(head) / 2, 5,
                    Integer.MAX_VALUE);
        } else {
            receiverTextBox.setEnabled(false);
            receiverTextBox.setVisible(false);
            wishTextBox.setEnabled(false);
            wishTextBox.setVisible(false);
            buttonSend.visible = false;
            this.fontRenderer.drawString(getWish(), this.xSize / 2 - this.fontRenderer.getStringWidth(getWish()) / 2, 68,
                    Integer.MAX_VALUE);
            String head = I18n.format("gui.redPacket.from", sender);
            this.fontRenderer.drawString(head, this.xSize / 2 - this.fontRenderer.getStringWidth(head) / 2, 5,
                    Integer.MAX_VALUE);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        this.mc.getTextureManager().bindTexture(new ResourceLocation(OceanHeartR.MODID, "textures/gui/red_packet.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected boolean checkHotbarKeys(int p_146983_1_) {
        if (p_146983_1_ == currentItem + 2)
            return false;
        else
            return super.checkHotbarKeys(p_146983_1_);
    }

    @Override
    protected void keyTyped(char par1, int par2) {
        wishTextBox.textboxKeyTyped(par1, par2);
        receiverTextBox.textboxKeyTyped(par1, par2);
        if (par1 == '\r') {
            if (wishTextBox.isFocused()) receiverTextBox.setFocused(true);
        }
        if (par2 == 1) {
            player.closeScreen();
            sendRedPacketToServer(false);
        }

        updateButton();
    }

    public String getWish() {
        return wishTextBox.getText();
    }

    public String getReceiver() {
        return receiverTextBox.getText();
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        super.onGuiClosed();
    }

    @SideOnly(Side.CLIENT)
    private void sendRedPacketToServer(boolean isSend) {
        OceanHeartR.getNetwork().sendToServer(new RedPacketMessage(isSend ? player.getName() : sender, getWish(), receiverTextBox.getText(), isSend));
    }

    @Override
    protected void mouseClicked(int par1, int par2, int par3) throws IOException {
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        wishTextBox.mouseClicked(par1 - k, par2 - l, par3);
        receiverTextBox.mouseClicked(par1 - k, par2 - l, par3);
        super.mouseClicked(par1, par2, par3);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (!button.enabled) return;
        switch (button.id) {
            case 0: {
                sendRedPacketToServer(true);
                player.closeScreen();
                break;
            }
            default:
                break;
        }
    }

    private void updateButton() {
        buttonSend.enabled = receiverTextBox.getText() != null && !receiverTextBox.getText().isEmpty();
    }
}
