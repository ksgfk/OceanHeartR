package cn.com.breakdawn.mc.common.block.dynamo;

import cn.com.breakdawn.mc.common.init.OHRItems;
import cofh.core.util.helpers.InventoryHelper;
import cofh.redstoneflux.api.IEnergyProvider;
import cofh.redstoneflux.api.IEnergyReceiver;
import cofh.redstoneflux.impl.EnergyStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

/**
 * @author KSGFK
 */
public class TileDynamoNature extends TileEntity implements ITickable, IEnergyProvider, IEnergyReceiver, ISidedInventory {
    protected EnergyStorage storage = new EnergyStorage(100000);
    private int powerGening = 0;
    protected int maxPowerGen = 1000000;

    public TileDynamoNature() {
        storage.setMaxExtract(32000);
        storage.setMaxReceive(1000);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        storage.readFromNBT(nbt);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        storage.writeToNBT(nbt);
        return nbt;
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

    /* IEnergyProvider */
    @Override
    public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
        return storage.extractEnergy(maxExtract, simulate);
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

    public void update() {
        if (!world.isRemote) {
            /*发电*/
            if (inventory[0].getItem().equals(OHRItems.NATURE_INGOT)) {
                ItemStack nature = inventory[0];
                if (powerGening == 0) {
                    if (nature.getCount() > 0) {
                        nature.setCount(nature.getCount() - 1);
                        powerGening = maxPowerGen;
                    }
                }
            }
            if (powerGening > 0) {
                int testRec = storage.receiveEnergy(storage.getMaxReceive(), true);
                if (powerGening > testRec) {
                    powerGening -= testRec;
                    storage.receiveEnergy(storage.getMaxReceive(), false);
                } else {
                    storage.receiveEnergy(powerGening, false);
                    powerGening = 0;
                }
            }
            /*发送RF*/
            TileEntity[] all = {
                    world.getTileEntity(pos.up()),
                    world.getTileEntity(pos.down()),
                    world.getTileEntity(pos.south()),
                    world.getTileEntity(pos.north()),
                    world.getTileEntity(pos.east()),
                    world.getTileEntity(pos.west())};
            for (int a = 0; a < EnumFacing.values().length; a++) {
                if (all[a] != null) {
                    receiveRF(all[a], EnumFacing.getFront(a), storage.getMaxExtract());
                }
            }
        }
    }

    private void receiveRF(TileEntity tileEntity, EnumFacing to, int ext) {
        if (tileEntity instanceof IEnergyReceiver) {
            IEnergyReceiver r = (IEnergyReceiver) tileEntity;
            if (r.canConnectEnergy(to)) {
                int testRec = r.receiveEnergy(to, ext, true);
                int testExt = this.extractEnergy(null, ext, true);
                if (testRec > 0 && testExt > 0) {
                    r.receiveEnergy(to, testRec, false);
                    this.extractEnergy(null, testRec, false);
                }
            }
        }
    }

    /*储存*/
    private int[] a = new int[1];
    private ItemStack air = new ItemStack(Items.AIR);
    public ItemStack[] inventory = new ItemStack[]{air};

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
        return false;
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return InventoryHelper.isEmpty(inventory);
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
        world.markChunkDirty(pos, this);
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
        return 0;
    }

    @Override
    public void clear() {
        inventory[0] = new ItemStack(Items.AIR);
    }

    @Override
    public String getName() {
        return "dynNature";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }
}
