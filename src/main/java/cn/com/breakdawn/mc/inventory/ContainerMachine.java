package cn.com.breakdawn.mc.inventory;

import cn.com.breakdawn.mc.common.tile.TileMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

/**
 * @author KSGFK create in 2019/2/28
 */
public abstract class ContainerMachine<T extends TileMachine> extends ContainerBase<T> {
    ContainerMachine(T tileEntity, EntityPlayer player) {
        super(tileEntity);
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 74 + i * 18));
        for (int i = 0; i < 9; ++i)
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 132));
    }

    ContainerMachine(T tileEntity, EntityPlayer player, int xOffsetA, int yOffsetA, int xOffsetB, int yOffsetB) {
        super(tileEntity);
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, xOffsetA + j * 18, yOffsetA + i * 18));
        for (int i = 0; i < 9; ++i)
            this.addSlotToContainer(new Slot(player.inventory, i, xOffsetB + i * 18, yOffsetB));
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        tileEntity.setOpenGui(false);
    }

    public T getTileEntity() {
        return tileEntity;
    }
}
