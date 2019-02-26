package cn.com.breakdawn.mc.common.tile;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.block.BlockNatureOre;
import cn.com.breakdawn.mc.common.init.OHRBlocks;
import cn.com.breakdawn.mc.common.init.OHRItems;
import cn.com.breakdawn.mc.config.OHRConfig;
import cn.com.breakdawn.mc.network.PulMsg;
import cn.com.breakdawn.mc.util.RedStoneEnergy;
import net.minecraft.block.state.IBlockState;
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
    private boolean isProcessing = false;
    private int lastDamage = 0;

    private IBlockState block = OHRBlocks.NATURE_ORE.getDefaultState().withProperty(BlockNatureOre.VARIANT, BlockNatureOre.EnumType.OVERWORLD);

    private ItemStackHandler items = new ItemStackHandler(2);
    private SlotItemHandler inputSlot = new SlotItemHandler(items, 0, 56, 30) {
        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem().equals(Item.getItemFromBlock(block.getBlock())) && super.isItemValid(stack);
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
        items.deserializeNBT(nbt.getCompoundTag("items"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        storage.writeToNBT(nbt);
        nbt.setInteger("process", processTime);
        nbt.setTag("items", items.serializeNBT());
        return super.writeToNBT(nbt);
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            if (inputSlot.getStack().getItemDamage() == 0) {
                processOre(0);
            } else if (inputSlot.getStack().getItemDamage() == 1) {
                processOre(1);
            }

            if (isOpenGui)
                OceanHeartR.getNetwork().sendTo(new PulMsg(storage.getEnergyStored(), storage.getMaxEnergyStored(), processTime), player);
        }
    }

    private void processOre(int itemDamage) {
        ItemStack in = items.getStackInSlot(0);
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
                modifyEnergy(testExt, false);
                processTime++;
            }
        }

        if (processTime >= perTime) {
            isProcessing = false;
            ItemStack stack = items.getStackInSlot(1);
            if (!stack.getItem().equals(OHRItems.NATURE_POWDER)) {
                ItemStack normal = new ItemStack(OHRItems.NATURE_POWDER);
                normal.setItemDamage(lastDamage);
                items.setStackInSlot(1, normal);
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

    public EntityPlayerMP getPlayer() {
        return player;
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
