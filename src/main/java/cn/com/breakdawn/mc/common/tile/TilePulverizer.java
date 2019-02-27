package cn.com.breakdawn.mc.common.tile;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.init.OHRBlocks;
import cn.com.breakdawn.mc.common.init.OHRItems;
import cn.com.breakdawn.mc.config.OHRConfig;
import cn.com.breakdawn.mc.network.PulMsg;
import cn.com.breakdawn.mc.util.RedStoneEnergy;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * TODO:能打其他矿石的打粉机
 *
 * @author KSGFK
 */
public class TilePulverizer extends TileEntity implements ITickable {
    private RedStoneEnergy storage = new RedStoneEnergy(OHRConfig.general.pulMaxEnergy);
    private EntityPlayerMP player;
    private boolean isOpenGui;
    private int processTime = 0;
    private int perTime = OHRConfig.general.pulPerGenTime;
    private boolean isProcessing;
    private int lastDamage;

    private ItemStackHandler items = new ItemStackHandler(2);
    private SlotItemHandler inputSlot = new SlotItemHandler(items, 0, 56, 30) {
        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem().equals(Item.getItemFromBlock(OHRBlocks.NATURE_ORE)) && super.isItemValid(stack);
        }

        @Override
        public int getSlotStackLimit() {
            return 64;
        }
    };
    private SlotItemHandler outputSlot = new SlotItemHandler(items, 1, 110, 30) {
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
        storage.readFromNBT(nbt);
        processTime = nbt.getInteger("process");
        isProcessing = nbt.getBoolean("isProcess");
        lastDamage = nbt.getInteger("last");
        items.deserializeNBT(nbt.getCompoundTag("items"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        storage.writeToNBT(nbt);
        nbt.setInteger("process", processTime);
        nbt.setBoolean("isProcess", isProcessing);
        nbt.setInteger("last", lastDamage);
        nbt.setTag("items", items.serializeNBT());
        return super.writeToNBT(nbt);
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            int testExt = storage.extractEnergy(storage.getMaxExtract(), true);
            if (testExt == storage.getMaxExtract()) {
                if (isProcessing) {
                    if (processTime >= perTime) {
                        if (items.getStackInSlot(1).getCount() < items.getStackInSlot(1).getMaxStackSize()) {
                            isProcessing = false;
                            if (!items.getStackInSlot(1).isEmpty()) {
                                if (lastDamage != items.getStackInSlot(1).getItemDamage()) {
                                    if (lastDamage == 0 && items.getStackInSlot(1).getItemDamage() == 1)
                                        items.getStackInSlot(1).setCount(items.getStackInSlot(1).getCount() + 1);
                                    else {
                                        items.getStackInSlot(1).setItemDamage(1);
                                        items.getStackInSlot(1).setCount(items.getStackInSlot(1).getCount() + 1);
                                    }
                                } else
                                    items.getStackInSlot(1).setCount(items.getStackInSlot(1).getCount() + 1);
                            } else
                                items.setStackInSlot(1, new ItemStack(OHRItems.NATURE_POWDER, 1, lastDamage));
                            processTime = 0;
                        }
                    } else {
                        storage.extractEnergy(testExt, false);
                        processTime++;
                    }
                } else if (!items.getStackInSlot(0).isEmpty()) {
                    items.getStackInSlot(0).setCount(items.getStackInSlot(0).getCount() - 1);
                    processTime = 0;
                    isProcessing = true;
                    lastDamage = items.getStackInSlot(0).getItemDamage();
                }
            }

            if (isOpenGui)
                OceanHeartR.getNetwork().sendTo(new PulMsg(storage.getEnergyStored(), storage.getMaxEnergyStored(), processTime), player);
        }
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability.equals(CapabilityEnergy.ENERGY);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return CapabilityEnergy.ENERGY.cast(storage);
    }

    public void setOpenGui(boolean openGui) {
        isOpenGui = openGui;
    }

    public void setPlayer(EntityPlayerMP player) {
        this.player = player;
    }

    public SlotItemHandler getInputSlot() {
        return inputSlot;
    }

    public SlotItemHandler getOutputSlot() {
        return outputSlot;
    }

    public int getPerTime() {
        return perTime;
    }

    public int getMaxEnergyStored(EnumFacing facing) {
        return storage.getMaxEnergyStored();
    }
}
