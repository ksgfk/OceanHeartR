package cn.com.breakdawn.mc.common.tile;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.init.OHRItems;
import cn.com.breakdawn.mc.config.OHRConfig;
import cn.com.breakdawn.mc.network.DynNatureMsg;
import cn.com.breakdawn.mc.util.RedStoneEnergy;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author KSGFK
 */
public class TileDynamoNature extends TileEntity implements ITickable {
    private RedStoneEnergy storage = new RedStoneEnergy(OHRConfig.general.dynNatureMaxEnergy);
    private ItemStackHandler items = new ItemStackHandler(1);
    private SlotItemHandler inputSlot = new SlotItemHandler(items, 0, 80, 30) {
        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            if (OHRConfig.general.dynNatureCanPut)
                return stack.getItem().equals(OHRItems.NATURE_INGOT) && super.isItemValid(stack);
            else return stack.getItemDamage() == 0 && super.isItemValid(stack);//只能放入普通自然结晶
        }

        @Override
        public int getSlotStackLimit() {
            return 64;
        }
    };
    private int powerGening = 0;
    private int maxPowerGen = OHRConfig.general.dynNaturePerGen;
    private EntityPlayerMP player;
    private boolean isOpenGui;

    public TileDynamoNature() {
        storage.setMaxExtract(OHRConfig.general.dynNatureMaxExtract);
        storage.setMaxReceive(OHRConfig.general.dynNatureMaxReceive);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        storage.readFromNBT(nbt);
        powerGening = nbt.getInteger("Gening");
        items.deserializeNBT(nbt.getCompoundTag("input"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        storage.writeToNBT(nbt);
        nbt.setInteger("Gening", powerGening);
        nbt.setTag("input", items.serializeNBT());
        return super.writeToNBT(nbt);
    }
    /*
    @Override
    public boolean canConnectEnergy(EnumFacing from) {
        return true;
    }

    @Override
    public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
        return storage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
        return storage.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored(EnumFacing from) {
        return storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(EnumFacing from) {
        return storage.getMaxEnergyStored();
    }
    */
    public void update() {
        if (!world.isRemote) {
            /*发电*/
            if (items.getStackInSlot(0).getItem().equals(OHRItems.NATURE_INGOT)) {
                ItemStack nature = items.getStackInSlot(0);
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
                    //receiveRF(all[a], EnumFacing.getFront(a), storage.getMaxExtract());
                }
            }
            if (isOpenGui) {
                OceanHeartR.getNetwork().sendTo(new DynNatureMsg(storage.getEnergyStored(), storage.getMaxEnergyStored(), powerGening), player);
            }
        }
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return true;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return super.getCapability(capability, facing);
    }
    /*
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
                TODO:不使用redstone flux API发送能量
            }
        }
    }
    */
    public void setPlayer(EntityPlayerMP player) {
        this.player = player;
    }

    public void setOpenGui(boolean openGui) {
        isOpenGui = openGui;
    }

    public SlotItemHandler getInputSlot() {
        return inputSlot;
    }

    public int getMaxEnergyStored(EnumFacing facing){
        return storage.getMaxEnergyStored();
    }
}
