package cn.com.breakdawn.mc.inventory;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.tile.TilePurify;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.SlotItemHandler;
import sausage_core.api.core.inventory.ContainerBase;

/**
 * @author KSGFK create in 2019/2/25
 */
public class ContainerPurify extends ContainerBase<TilePurify> {
    public ContainerPurify(TileEntity tileEntity, EntityPlayer player) {
        super(tileEntity);
        if (tileEntity instanceof TilePurify) {
            TilePurify tile = (TilePurify) tileEntity;
            SlotItemHandler inputSlot = tile.getInputSlot();
            SlotItemHandler outputSlot = tile.getOutputSlot();
            this.addSlotToContainer(inputSlot);
            this.addSlotToContainer(outputSlot);
        } else OceanHeartR.getLogger().error("It's magic!");

        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 74 + i * 18));
        for (int i = 0; i < 9; ++i)
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 132));
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        tileEntity.setOpenGui(false);
    }

    public TilePurify getPur() {
        return tileEntity;
    }
}
