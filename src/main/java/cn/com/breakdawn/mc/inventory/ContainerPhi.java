package cn.com.breakdawn.mc.inventory;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.tile.TilePhi;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import sausage_core.api.core.inventory.ContainerBase;

/**
 * @author KSGFK create in 2019/2/27
 */
public class ContainerPhi extends ContainerBase<TilePhi> {
    public ContainerPhi(TileEntity tileEntity, EntityPlayer player) {
        super(tileEntity);
        if (tileEntity instanceof TilePhi) {
            TilePhi tile = (TilePhi) tileEntity;
            this.addSlotToContainer(tile.getSlot0());
            this.addSlotToContainer(tile.getSlot1());
            this.addSlotToContainer(tile.getSlot2());
            this.addSlotToContainer(tile.getSlot3());
            this.addSlotToContainer(tile.getSlot4());
            this.addSlotToContainer(tile.getOut());
        } else OceanHeartR.getLogger().info("Oops,I don't think it's my problem.");

        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 105 + i * 18));
        for (int i = 0; i < 9; ++i)
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 163));
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        tileEntity.setOpenGui(false);
    }

    public TilePhi getPhi() {
        return tileEntity;
    }
}
