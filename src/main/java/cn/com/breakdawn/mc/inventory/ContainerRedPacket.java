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
package cn.com.breakdawn.mc.inventory;

import cn.com.breakdawn.mc.common.init.OHRItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;

public class ContainerRedPacket extends Container {

    private ItemStack itemStack;

    public ContainerRedPacket(EntityPlayer player) {
        this.itemStack = player.getHeldItemMainhand();
        Slot slot0 = new Slot(new InventoryBasic("redpacket", false, 1), 0, 80, 25);
        this.addSlotToContainer(slot0);

        load(player);

        int var3;
        for (var3 = 0; var3 < 3; ++var3) {
            for (int var4 = 0; var4 < 9; ++var4) {
                this.addSlotToContainer(new Slot(player.inventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3) {
            if (var3 == player.inventory.currentItem) {
                this.addSlotToContainer(new Slot(player.inventory, var3, 8 + var3 * 18, 142) {
                    @Override
                    public boolean canTakeStack(EntityPlayer p_82869_1_) {
                        return false;
                    }

                    @Override
                    public boolean isItemValid(ItemStack p_75214_1_) {
                        return false;
                    }
                });
            } else {
                this.addSlotToContainer(new Slot(player.inventory, var3, 8 + var3 * 18, 142));
            }
        }
    }

    private void load(EntityPlayer player) {
        //不知道有没有用的防刷物品措施
        if (player.getEntityWorld().isRemote) return;

        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("item")) {
            NBTTagCompound itemnbt = itemStack.getTagCompound().getCompoundTag("item");
            //不知道有没有用的防刷物品措施
            itemStack.getTagCompound().setTag("item", new NBTTagCompound());
            ItemStack item = new ItemStack(itemnbt);
            if (item != null && item.getItem() == Items.TNT_MINECART) {
                //炸弹自爆
                player.world.createExplosion(player, player.posX, player.posY, player.posZ, 1.5F, true);
                getSlot(0).putStack(null);
            } else
                getSlot(0).putStack(item);
        }
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
        if (playerIn.getHeldItemMainhand() == ItemStack.EMPTY || !playerIn.getHeldItemMainhand().getItem().equals(OHRItems.RED_PACKET))
            return;

        NBTTagCompound nbtitem = new NBTTagCompound();
        if (getSlot(0).getStack() != ItemStack.EMPTY) getSlot(0).getStack().writeToNBT(nbtitem);
        playerIn.getHeldItemMainhand().setTagInfo("item", nbtitem);
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = ItemStack.EMPTY;
        Slot var4 = this.inventorySlots.get(par2);
        if (var4 != null && var4.getHasStack()) {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();
            // 点击到Slot的ID为0的时候，将物品送回玩家的背包中
            if (par2 == 0) {
                if (!this.mergeItemStack(var5, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
                var4.onSlotChange(var5, var3);
            }
            // 点击到玩家的背包的时候将物品送到玩家的快捷栏中
            else if (par2 > 0 && par2 < 28) {
                if (!this.mergeItemStack(var5, 28, 37, false)) {
                    return ItemStack.EMPTY;
                }
            }
            // 点击到玩家的快捷栏的时候将物品送到背包中
            else if (par2 >= 28 && par2 < 37) {
                if (!this.mergeItemStack(var5, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
            }
            if (var5.getMaxStackSize() == 0) {
                var4.putStack(ItemStack.EMPTY);
            } else {
                var4.onSlotChanged();
            }
            if (var5.getMaxStackSize() == var3.getMaxStackSize()) {
                return ItemStack.EMPTY;
            }
            var4.onTake(par1EntityPlayer, var5);
        }

        return var3;
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        //return true;
        return playerIn.getHeldItemMainhand().getItem().equals(OHRItems.RED_PACKET);
    }
}
