package cn.com.breakdawn.mc.common.block.dynamo;

import cofh.redstoneflux.api.IEnergyProvider;
import cofh.redstoneflux.api.IEnergyReceiver;
import cofh.redstoneflux.impl.EnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public class TileDynamoNature extends TileEntity implements ITickable, IEnergyProvider, IEnergyReceiver {

    protected EnergyStorage storage = new EnergyStorage(32000);

    public TileDynamoNature() {
        storage.setMaxExtract(1000);
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
}
