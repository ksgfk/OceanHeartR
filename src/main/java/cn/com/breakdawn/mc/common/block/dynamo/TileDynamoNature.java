package cn.com.breakdawn.mc.common.block.dynamo;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
//TODO:不会写机器啊啊啊啊啊啊啊啊
public class TileDynamoNature extends TileEntity implements ITickable {
    protected int burnTime = 0;
    protected ItemStackHandler upInventory = new ItemStackHandler();
    protected ItemStackHandler downInventory = new ItemStackHandler();

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability)) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability)) {
            @SuppressWarnings("unchecked")
            T result = (T) (facing == EnumFacing.DOWN ? downInventory : upInventory);
            return result;
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.upInventory.deserializeNBT(compound.getCompoundTag("UpInventory"));
        this.downInventory.deserializeNBT(compound.getCompoundTag("DownInventory"));
        this.burnTime = compound.getInteger("BurnTime");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("UpInventory", this.upInventory.serializeNBT());
        compound.setTag("DownInventory", this.downInventory.serializeNBT());
        compound.setInteger("BurnTime", this.burnTime);
        return super.writeToNBT(compound);
    }

    @Override
    public void update() {

    }

    public int getBurnTime() {
        return this.burnTime;
    }

    public int getTotalBurnTime() {
        return 100;
    }
}
