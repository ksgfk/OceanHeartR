package cn.com.breakdawn.mc.inventory;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.block.dynamo.TileDynamoNature;
import cn.com.breakdawn.mc.common.init.OHRItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author ksgfk
 */
public class ContainerDynamoNature extends Container {
    private Slot inputSlot;

    private TileDynamoNature dynNature;
    private int energy = 0;
    private int totalEnergy = 0;

    public ContainerDynamoNature(TileEntity tileEntity, EntityPlayer player) {
        super();
        this.dynNature = (TileDynamoNature) tileEntity;
        /*机器*/
        this.addSlotToContainer(inputSlot = new Slot(dynNature, 0, 80, 30) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return stack.getItem().equals(OHRItems.NATURE_INGOT) && super.isItemValid(stack);//只能放入自然结晶
            }

            @Override
            public int getSlotStackLimit() {
                return 64;
            }
        });
        /*玩家背包*/
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 74 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 132));
        }

        energy = dynNature.getEnergyStored(null);
        totalEnergy = dynNature.getMaxEnergyStored(null);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return playerIn.getDistanceSq(this.dynNature.getPos()) <= 64;
    }

    /*有bug,有时候会报空引用...来自4z教程...
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        Slot slot = inventorySlots.get(index);

        if (slot == null || !slot.getHasStack()) {
            return null;
        }

        ItemStack newStack = slot.getStack(), oldStack = newStack.copy();

        boolean isMerged = false;

        if (index == 0) {
            isMerged = mergeItemStack(newStack, 1, 37, true);
        } else if (index >= 1 && index < 28) {
            isMerged = !inputSlot.getHasStack() && newStack.getCount() <= 64 && mergeItemStack(newStack, 0, 1, false)
                    || mergeItemStack(newStack, 28, 37, false);
        } else if (index >= 29 && index < 36) {
            isMerged = !inputSlot.getHasStack() && newStack.getCount() <= 64 && mergeItemStack(newStack, 0, 1, false)
                    || mergeItemStack(newStack, 1, 28, false);
        }

        if (!isMerged) {
            return null;
        }

        if (newStack.getCount() == 0) {
            slot.putStack(ItemStack.EMPTY);
        } else {
            slot.onSlotChanged();
        }

        slot.onTake(playerIn, newStack);

        return oldStack;
    }
    */
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
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        this.energy = dynNature.getEnergyStored(null);
        this.totalEnergy = dynNature.getMaxEnergyStored(null);
        this.listeners.forEach(iContainerListener -> {
            iContainerListener.sendWindowProperty(this, 0, energy);
            iContainerListener.sendWindowProperty(this, 1, totalEnergy);
            iContainerListener.sendSlotContents(this, 0, inputSlot.getStack());
        });
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data) {
        super.updateProgressBar(id, data);

        switch (id) {
            case 0:
                this.energy = data;
                break;
            case 1:
                this.totalEnergy = data;
                break;
            default:
                OceanHeartR.getLogger().warn("no such change ID" + id);
                break;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setAll(List<ItemStack> stacks) {
        for (int i = 0; i < stacks.size(); ++i) {
            putStackInSlot(i, stacks.get(i));
        }
    }

    public int getEnergy() {
        return energy;
    }

    public int getTotalEnergy() {
        return totalEnergy;
    }
}
