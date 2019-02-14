package cn.com.breakdawn.mc.common.tile;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.block.BlockNatureOre;
import cn.com.breakdawn.mc.common.init.OHRBlocks;
import cn.com.breakdawn.mc.common.init.OHRItems;
import cn.com.breakdawn.mc.config.OHRConfig;
import cn.com.breakdawn.mc.network.PulMsg;
import cofh.redstoneflux.api.IEnergyReceiver;
import cofh.redstoneflux.impl.EnergyStorage;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

import javax.annotation.Nonnull;

/**
 * @auther KSGFK
 */
public class TilePulverizer extends TileInventory implements IEnergyReceiver, ITickable {
    private EnergyStorage storage = new EnergyStorage(OHRConfig.general.pulMaxEnergy);
    private NBTTagCompound nbtTagCompound;
    private EntityPlayerMP player;
    private boolean isOpenGui;
    private int processTime = 0;
    private int perTime = OHRConfig.general.pulPerGenTime;
    private boolean isProcessing = false;
    private int lastDamage = 0;

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

    public TilePulverizer() {
        storage.setMaxReceive(OHRConfig.general.pulMaxReceive);
        storage.setMaxExtract(OHRConfig.general.pulMaxExtract);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        storage.readFromNBT(nbt.getCompoundTag("info"));
        this.processTime = nbt.getCompoundTag("info").getInteger("process");
        if (inputSlot != null) {
            ItemStack i = new ItemStack(block.getBlock());
            i.setCount(nbt.getCompoundTag("info").getShort("inCount"));
            i.setItemDamage(nbt.getCompoundTag("info").getShort("inMeta"));
            inputSlot.putStack(i);
        }
        if (inputSlot != null) {
            ItemStack s = new ItemStack(OHRItems.NATURE_POWDER);
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
            nbt.getCompoundTag("info").setInteger("Gening", this.processTime);
        } else {
            NBTTagCompound n = new NBTTagCompound();
            storage.writeToNBT(n);
            n.setInteger("process", processTime);
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
        if (!world.isRemote) {
            if (inputSlot.getStack().getItemDamage() == 0) {
                processOre(0);
            } else if (inputSlot.getStack().getItemDamage() == 1) {
                processOre(1);
            }

            if (isOpenGui) {
                OceanHeartR.getNetwork().sendTo(new PulMsg(storage.getEnergyStored(), storage.getMaxEnergyStored(), processTime), player);
            }
        }
    }

    private void processOre(int itemDamage) {
        ItemStack in = inputSlot.getStack();
        int count = in.getCount();
        if (!in.isEmpty() && !isProcessing) {
            in.setCount(count - 1);
            isProcessing = true;
            processTime = 0;
            lastDamage = in.getItemDamage();
        }
        if (isProcessing && processTime < perTime) {
            int testExt = modifyEnergy(storage.getMaxExtract(), true);
            if (testExt == storage.getMaxExtract()) {
                OceanHeartR.getLogger().info(testExt);
                modifyEnergy(testExt, false);
                processTime++;
            }
        }

        if (processTime >= perTime) {
            isProcessing = false;
            ItemStack stack = outputSlot.getStack();
            if (!stack.getItem().equals(OHRItems.NATURE_POWDER)) {
                ItemStack normal = new ItemStack(OHRItems.NATURE_POWDER);
                normal.setItemDamage(lastDamage);
                outputSlot.putStack(normal);
                stack.setCount(stack.getCount() + 1);
                processTime = 0;
            } else {
                if (stack.getItemDamage() != itemDamage) {
                    stack.setItemDamage(1);
                }
                stack.setCount(stack.getCount() + 1);
                processTime = 0;
            }
        }
    }

    private int modifyEnergy(int maxExtract, boolean simulate) {
        int energyExtracted = Math.min(storage.getEnergyStored(), Math.min(storage.getMaxEnergyStored(), maxExtract));
        if (!simulate) {
            storage.setEnergyStored(storage.getEnergyStored() - energyExtracted);
        }
        return energyExtracted;
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
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public void clear() {
        inventory[0] = new ItemStack(Items.AIR);

    }

    @Override
    public String getName() {
        return "pul";
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

    public int getPerTime() {
        return perTime;
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
