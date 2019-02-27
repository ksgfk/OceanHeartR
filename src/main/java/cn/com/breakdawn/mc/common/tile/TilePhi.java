package cn.com.breakdawn.mc.common.tile;

import cn.com.breakdawn.mc.common.init.OHRItems;
import cn.com.breakdawn.mc.config.OHRConfig;
import cn.com.breakdawn.mc.util.RedStoneEnergy;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

/**
 * @author KSGFK create in 2019/2/27
 */
public class TilePhi extends TileEntity implements ITickable {
    private RedStoneEnergy storage = new RedStoneEnergy(OHRConfig.general.dynNatureMaxEnergy);
    private ItemStackHandler items = new ItemStackHandler(6);
    private SlotItemHandler slot0 = new SlotItemHandler(items, 0, 28, 13) {
        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem().equals(OHRItems.NATURE_MARROW) && super.isItemValid(stack);
        }
    };
    private SlotItemHandler slot1 = new SlotItemHandler(items, 0, 76, 13) {
        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem().equals(OHRItems.NATURE_MARROW) && super.isItemValid(stack);
        }
    };
    private SlotItemHandler slot2 = new SlotItemHandler(items, 0, 28, 61) {
        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem().equals(OHRItems.NATURE_MARROW) && super.isItemValid(stack);
        }
    };
    private SlotItemHandler slot3 = new SlotItemHandler(items, 0, 76, 61) {
        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem().equals(OHRItems.NATURE_MARROW) && super.isItemValid(stack);
        }
    };
    private SlotItemHandler slot4 = new SlotItemHandler(items, 0, 52, 37) {
        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem().equals(Item.getItemFromBlock(Blocks.SAPLING)) && super.isItemValid(stack);
        }
    };
    private SlotItemHandler out = new SlotItemHandler(items, 0, 109, 37) {
        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return false;
        }
    };

    private EntityPlayerMP player;
    private boolean isOpenGui;

    public TilePhi() {

    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        storage.readFromNBT(compound);
        items.deserializeNBT(compound.getCompoundTag("items"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        storage.writeToNBT(compound);
        compound.setTag("items", items.serializeNBT());
        return super.writeToNBT(compound);
    }

    @Override
    public void update() {

    }

    public SlotItemHandler getSlot0() {
        return slot0;
    }

    public SlotItemHandler getSlot1() {
        return slot1;
    }

    public SlotItemHandler getSlot2() {
        return slot2;
    }

    public SlotItemHandler getSlot3() {
        return slot3;
    }

    public SlotItemHandler getSlot4() {
        return slot4;
    }

    public SlotItemHandler getOut() {
        return out;
    }

    public void setOpenGui(boolean openGui) {
        isOpenGui = openGui;
    }

    public void setPlayer(EntityPlayerMP player) {
        this.player = player;
    }
}
