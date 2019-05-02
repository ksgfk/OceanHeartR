package cn.com.breakdawn.mc.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

/**
 * @author yaossg
 */
public abstract class ContainerBase<T extends TileEntity> extends Container {
    public final T tileEntity;

    public ContainerBase(TileEntity tileEntity) {
        this.tileEntity = (T) tileEntity;
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        return playerIn.getDistanceSq(this.tileEntity.getPos()) <= 64.0D;
    }

    protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {
        boolean changed = false;
        int i = reverseDirection ? endIndex - 1 : startIndex;
        Slot slot;
        ItemStack itemstack;
        if (stack.isStackable()) {
            for(; !stack.isEmpty() && (!reverseDirection || i >= startIndex) && i < endIndex; i += reverseDirection ? -1 : 1) {
                slot = (Slot)this.inventorySlots.get(i);
                itemstack = slot.getStack();
                if (slot.isItemValid(itemstack) && !itemstack.isEmpty() && itemstack.getItem() == stack.getItem() && (!stack.getHasSubtypes() || stack.getMetadata() == itemstack.getMetadata()) && ItemStack.areItemStackTagsEqual(stack, itemstack)) {
                    int j = itemstack.getCount() + stack.getCount();
                    int maxSize = Math.min(slot.getSlotStackLimit(), stack.getMaxStackSize());
                    if (j <= maxSize) {
                        stack.setCount(0);
                        itemstack.setCount(j);
                        slot.onSlotChanged();
                        changed = true;
                    } else if (itemstack.getCount() < maxSize) {
                        stack.shrink(maxSize - itemstack.getCount());
                        itemstack.setCount(maxSize);
                        slot.onSlotChanged();
                        changed = true;
                    }
                }
            }
        }

        if (!stack.isEmpty()) {
            for(i = reverseDirection ? endIndex - 1 : startIndex; (!reverseDirection || i >= startIndex) && i < endIndex; i += reverseDirection ? -1 : 1) {
                slot = (Slot)this.inventorySlots.get(i);
                itemstack = slot.getStack();
                if (itemstack.isEmpty() && slot.isItemValid(stack)) {
                    if (stack.getCount() > slot.getSlotStackLimit()) {
                        slot.putStack(stack.splitStack(slot.getItemStackLimit(stack)));
                    } else {
                        slot.putStack(stack.splitStack(stack.getCount()));
                    }

                    slot.onSlotChanged();
                    changed = true;
                    break;
                }
            }
        }

        return changed;
    }

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        Slot slot = (Slot)this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack newStack = slot.getStack();
            ItemStack oldStack = newStack.copy();
            int length = this.inventorySlots.size() - 36;
            boolean isMerged;
            if (index < length) {
                isMerged = this.mergeItemStack(newStack, length, 36 + length, true);
            } else if (index < 27 + length) {
                isMerged = this.mergeItemStack(newStack, 0, length, false) || this.mergeItemStack(newStack, 27 + length, 36 + length, false);
            } else {
                isMerged = this.mergeItemStack(newStack, 0, length, false) || this.mergeItemStack(newStack, length, 27 + length, false);
            }

            if (!isMerged) {
                return ItemStack.EMPTY;
            } else {
                if (newStack.isEmpty()) {
                    slot.putStack(ItemStack.EMPTY);
                } else {
                    slot.onSlotChanged();
                }

                return oldStack;
            }
        } else {
            return ItemStack.EMPTY;
        }
    }
}
