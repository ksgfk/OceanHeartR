package cn.com.breakdawn.mc.common.tile;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.init.OHRItems;
import cn.com.breakdawn.mc.config.OHRConfig;
import cn.com.breakdawn.mc.network.DynNatureMsg;
import cn.com.breakdawn.mc.util.RedStoneEnergy;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

/**
 * @author KSGFK
 */
public class TileDynamoNature extends TileMachine {
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

    public TileDynamoNature() {
        super(new RedStoneEnergy(OHRConfig.general.dynNatureMaxEnergy), new ItemStackHandler(1));
        storage.setMaxExtract(OHRConfig.general.dynNatureMaxExtract);
        storage.setMaxReceive(OHRConfig.general.dynNatureMaxReceive);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        powerGening = nbt.getInteger("Gening");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setInteger("Gening", powerGening);
        return super.writeToNBT(nbt);
    }

    @Override
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
            for (int a = 0; a < EnumFacing.values().length; a++)
                if (all[a] != null) receiveRF(all[a], EnumFacing.getFront(a), storage.getMaxExtract());
            if (isOpenGui)
                OceanHeartR.getNetwork().sendTo(new DynNatureMsg(storage.getEnergyStored(), storage.getMaxEnergyStored(), powerGening), player);
        }
    }

    private void receiveRF(TileEntity tileEntity, EnumFacing to, int ext) {
        if (tileEntity.hasCapability(CapabilityEnergy.ENERGY, to) && !(tileEntity instanceof TileDynamoNature)) {
            if (tileEntity.getCapability(CapabilityEnergy.ENERGY, to).canReceive()) {
                int testRec = tileEntity.getCapability(CapabilityEnergy.ENERGY, to).receiveEnergy(ext, true);
                int testExt = storage.extractEnergy(ext, true);
                if (testRec > 0 && testExt > 0) {
                    tileEntity.getCapability(CapabilityEnergy.ENERGY, to).receiveEnergy(testRec, false);
                    storage.extractEnergy(testRec, false);
                }
            }
        }
    }

    public SlotItemHandler getInputSlot() {
        return inputSlot;
    }

    public int getMaxPowerGen() {
        return maxPowerGen;
    }
}
