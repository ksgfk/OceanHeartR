package cn.com.breakdawn.mc.common.tile;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.init.OHRItems;
import cn.com.breakdawn.mc.config.OHRConfig;
import cn.com.breakdawn.mc.network.PurifyMsg;
import cn.com.breakdawn.mc.util.RedStoneEnergy;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

/**
 * @author KSGFK create in 2019/2/25
 */
public class TilePurify extends TileMachine {
    private SlotItemHandler inputSlot = new SlotItemHandler(items, 0, 56, 30) {
        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem().equals(OHRItems.NATURE_POWDER) && super.isItemValid(stack);
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

    private int processTime = 0;
    private int perTime = OHRConfig.general.purPerGenTime;
    private boolean isProcessing = false;
    private int lastDamage = 0;

    public TilePurify() {
        super(new RedStoneEnergy(OHRConfig.general.purMaxEnergy), new ItemStackHandler(2));
        storage.setMaxReceive(OHRConfig.general.purMaxReceive);
        storage.setMaxExtract(OHRConfig.general.purMaxExtract);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        processTime = nbt.getInteger("process");
        isProcessing = nbt.getBoolean("isProcess");
        lastDamage = nbt.getInteger("last");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("process", processTime);
        nbt.setBoolean("isProcess", isProcessing);
        nbt.setInteger("last", lastDamage);
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
                                items.setStackInSlot(1, new ItemStack(OHRItems.NATURE_MARROW, 1, lastDamage));
                            processTime = 0;
                        }
                    } else {
                        storage.extractEnergy(testExt, false);
                        processTime++;
                    }
                } else if (!items.getStackInSlot(0).equals(ItemStack.EMPTY) && items.getStackInSlot(0).getCount() > 1) {
                    items.getStackInSlot(0).setCount(items.getStackInSlot(0).getCount() - 2);
                    processTime = 0;
                    isProcessing = true;
                    lastDamage = items.getStackInSlot(0).getItemDamage();
                }
            }

            if (isOpenGui)
                OceanHeartR.getNetwork().sendTo(new PurifyMsg(storage.getEnergyStored(), storage.getMaxEnergyStored(), processTime), player);
        }
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
}
