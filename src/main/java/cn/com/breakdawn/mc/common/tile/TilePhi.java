package cn.com.breakdawn.mc.common.tile;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.init.OHRItems;
import cn.com.breakdawn.mc.config.OHRConfig;
import cn.com.breakdawn.mc.network.PhiMsg;
import cn.com.breakdawn.mc.util.RedStoneEnergy;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

/**
 * @author KSGFK create in 2019/2/27
 */
public class TilePhi extends TileMachine {
    private SlotItemHandler slot0 = new SlotItemHandler(items, 0, 28, 13) {
        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem().equals(OHRItems.NATURE_MARROW) && super.isItemValid(stack);
        }
    };
    private SlotItemHandler slot1 = new SlotItemHandler(items, 1, 76, 13) {
        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem().equals(OHRItems.NATURE_MARROW) && super.isItemValid(stack);
        }
    };
    private SlotItemHandler slot2 = new SlotItemHandler(items, 2, 28, 61) {
        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem().equals(OHRItems.NATURE_MARROW) && super.isItemValid(stack);
        }
    };
    private SlotItemHandler slot3 = new SlotItemHandler(items, 3, 76, 61) {
        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem().equals(OHRItems.NATURE_MARROW) && super.isItemValid(stack);
        }
    };
    private SlotItemHandler slot4 = new SlotItemHandler(items, 4, 52, 37) {
        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return stack.getItem().equals(Item.getItemFromBlock(Blocks.SAPLING)) && super.isItemValid(stack);
        }
    };
    private SlotItemHandler out = new SlotItemHandler(items, 5, 109, 37) {
        @Override
        public boolean isItemValid(@Nonnull ItemStack stack) {
            return false;
        }
    };

    private int processTime = 0;
    private int perTime = OHRConfig.general.phiPerGenTime;
    private boolean isProcessing = false;

    public TilePhi() {
        super(new RedStoneEnergy(OHRConfig.general.phiMaxEnergy), new ItemStackHandler(6));
        storage.setMaxReceive(OHRConfig.general.phiMaxReceive);
        storage.setMaxExtract(OHRConfig.general.phiMaxExtract);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        processTime = compound.getInteger("process");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("process", processTime);
        return super.writeToNBT(compound);
    }

    @Override
    public void update() {
        boolean isBoom = false;
        if (!world.isRemote) {
            int testExt = storage.extractEnergy(storage.getMaxExtract(), true);
            if (testExt == storage.getMaxExtract()) {
                if (isProcessing) {
                    if (processTime >= perTime) {
                        if (items.getStackInSlot(5).getCount() < items.getStackInSlot(5).getMaxStackSize()) {
                            isProcessing = false;
                            if (!items.getStackInSlot(5).isEmpty())
                                items.getStackInSlot(5).setCount(items.getStackInSlot(5).getCount() + 1);
                            else
                                items.setStackInSlot(5, new ItemStack(OHRItems.NATURE_INGOT, 1, 0));
                            processTime = 0;
                        }
                    } else {
                        storage.extractEnergy(testExt, false);
                        processTime++;
                    }
                } else {
                    boolean can = false;
                    int[] damage = new int[4];
                    for (int a = 0; a < 5; a++) {
                        if (items.getStackInSlot(a).isEmpty()) {
                            can = false;
                            break;
                        } else can = true;
                    }
                    if (can) {
                        for (int a = 0; a < 5; a++) {
                            items.getStackInSlot(a).setCount(items.getStackInSlot(a).getCount() - 1);
                            if (a < 4) damage[a] = items.getStackInSlot(a).getItemDamage();
                        }
                        for (int a : damage) if (a == 1) isBoom = true;
                        if (isBoom)
                            world.createExplosion(player, player.posX, player.posY, player.posZ, 15, true);
                        processTime = 0;
                        isProcessing = true;
                    }
                }
            }
            if (isOpenGui)
                OceanHeartR.getNetwork().sendTo(new PhiMsg(storage.getEnergyStored(), storage.getMaxEnergyStored(), processTime), player);
        }

        if (isBoom) {
            player.closeContainer();
            player.closeScreen();
        }
    }

    public SlotItemHandler getSlot0() {
        return slot0;
    }

    public SlotItemHandler getSlot1() {
        return slot1;
    }

    public SlotItemHandler getSlot2() {
        return slot2;
    }

    public SlotItemHandler getSlot3() {
        return slot3;
    }

    public SlotItemHandler getSlot4() {
        return slot4;
    }

    public SlotItemHandler getOut() {
        return out;
    }

    public int getPerTime() {
        return perTime;
    }
}
