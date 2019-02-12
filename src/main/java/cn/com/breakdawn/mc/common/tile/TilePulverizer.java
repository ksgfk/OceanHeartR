package cn.com.breakdawn.mc.common.tile;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.block.BlockNatureOre;
import cn.com.breakdawn.mc.common.init.OHRBlocks;
import cn.com.breakdawn.mc.common.init.OHRItems;
import cn.com.breakdawn.mc.network.PulMsg;
import cofh.redstoneflux.api.IEnergyReceiver;
import cofh.redstoneflux.impl.EnergyStorage;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

import javax.annotation.Nonnull;

/**
 * @auther KSGFK
 */
public class TilePulverizer extends TileEntity implements IEnergyReceiver, ISidedInventory, ITickable {
    private EnergyStorage storage = new EnergyStorage(320000).setMaxReceive(80);
    private NBTTagCompound nbtTagCompound;
    private EntityPlayerMP player;
    private boolean isOpenGui;

    private IBlockState block = OHRBlocks.NATURE_ORE.getDefaultState().withProperty(BlockNatureOre.VARIANT, BlockNatureOre.EnumType.OVERWORLD);

    private Slot inputSlot = new Slot(this, 0, 56, 30) {
        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem().equals(Item.getItemFromBlock(block.getBlock())) && super.isItemValid(stack);
        }

        @Override
        public int getSlotStackLimit() {
            return 64;
        }
    };
    private Slot outputSlot = new Slot(this, 1, 110, 30) {
        @Override
        public boolean isItemValid(ItemStack stack) {
            return false;
        }
    };

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        storage.readFromNBT(nbt.getCompoundTag("info"));
        //this.powerGening = nbt.getCompoundTag("info").getInteger("Gening");
        if (inputSlot != null) {
            ItemStack i = new ItemStack(block.getBlock());
            i.setCount(nbt.getCompoundTag("info").getShort("inCount"));
            i.setItemDamage(nbt.getCompoundTag("info").getShort("inMeta"));
            inputSlot.putStack(i);
        }
        if (inputSlot != null) {
            ItemStack s = new ItemStack(OHRItems.NATURE_POWER);
            s.setCount(nbt.getCompoundTag("info").getShort("outCount"));
            s.setItemDamage(nbt.getCompoundTag("info").getShort("outMeta"));
            outputSlot.putStack(s);
        }
        this.nbtTagCompound = nbt;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        if (nbt.hasKey("info")) {
            storage.writeToNBT(nbt.getCompoundTag("info"));
            nbt.getCompoundTag("info").setShort("inCount", (short) inputSlot.getStack().getCount());
            nbt.getCompoundTag("info").setShort("inMeta", (short) inputSlot.getStack().getMetadata());
            nbt.getCompoundTag("info").setShort("outCount", (short) outputSlot.getStack().getCount());
            nbt.getCompoundTag("info").setShort("outMeta", (short) outputSlot.getStack().getMetadata());
            //nbt.getCompoundTag("info").setInteger("Gening", this.powerGening);
        } else {
            NBTTagCompound n = new NBTTagCompound();
            storage.writeToNBT(n);
            //n.setInteger("Gening", powerGening);
            if (inputSlot != null) {
                n.setShort("inCount", (short) inputSlot.getStack().getCount());
                n.setShort("inMeta", (short) inputSlot.getStack().getMetadata());
            }
            if (outputSlot != null) {
                n.setShort("outCount", (short) outputSlot.getStack().getCount());
                n.setShort("outMeta", (short) outputSlot.getStack().getMetadata());
            }
            nbt.setTag("info", n);
        }
        this.nbtTagCompound = nbt;
        return nbt;
    }

    @Override
    public void update() {
        if (isOpenGui) {
            OceanHeartR.getNetwork().sendTo(new PulMsg(storage.getEnergyStored(), storage.getMaxEnergyStored()), player);
        }
    }

    /*储存*/
    private int[] a = new int[2];
    private ItemStack air = new ItemStack(Items.AIR);
    public ItemStack[] inventory = new ItemStack[]{air, air};

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return a;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return true;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return true;
    }

    @Override
    public int getSizeInventory() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : inventory) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return inventory[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (inventory[index].isEmpty()) {
            return ItemStack.EMPTY;
        }
        if (inventory[index].getCount() <= count) {
            count = inventory[index].getCount();
        }
        ItemStack stack = inventory[index].splitStack(count);

        if (inventory[index].getCount() <= 0) {
            inventory[index] = ItemStack.EMPTY;
        }
        return stack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        if (inventory[index].isEmpty()) {
            return ItemStack.EMPTY;
        }
        ItemStack stack = inventory[index];
        inventory[index] = ItemStack.EMPTY;
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        inventory[index] = stack;
        this.markDirty();
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return player.getDistanceSq(pos) <= 64D && world.getTileEntity(pos) == this;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {
    }

    @Override
    public int getFieldCount() {
        return 1;
    }

    @Override
    public void clear() {
        inventory[0] = new ItemStack(Items.AIR);

    }

    @Override
    public String getName() {
        return "pul";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    public boolean isOpenGui() {
        return isOpenGui;
    }

    public void setOpenGui(boolean openGui) {
        isOpenGui = openGui;
    }

    public EntityPlayerMP getPlayer() {
        return player;
    }

    public void setPlayer(EntityPlayerMP player) {
        this.player = player;
    }

    public Slot getInputSlot() {
        return inputSlot;
    }

    public void setInputSlot(Slot inputSlot) {
        this.inputSlot = inputSlot;
    }

    public NBTTagCompound getNbt() {
        return nbtTagCompound;
    }

    public void setNbt(NBTTagCompound nbt) {
        this.nbtTagCompound = nbt;
    }

    public Slot getOutputSlot() {
        return outputSlot;
    }

    /* IEnergyConnection */
    @Override
    public boolean canConnectEnergy(EnumFacing from) {
        return true;
    }

    /* IEnergyReceiver */
    @Override
    public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
        return storage.receiveEnergy(maxReceive, simulate);
    }

    /* IEnergyHandler */
    @Override
    public int getEnergyStored(EnumFacing from) {
        return storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(EnumFacing from) {
        return storage.getMaxEnergyStored();
    }
}
