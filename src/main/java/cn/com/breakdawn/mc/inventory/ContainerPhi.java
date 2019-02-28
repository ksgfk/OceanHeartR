package cn.com.breakdawn.mc.inventory;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.tile.TilePhi;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

/**
 * @author KSGFK create in 2019/2/27
 */
public class ContainerPhi extends ContainerMachine<TilePhi> {
    public ContainerPhi(TileEntity tileEntity, EntityPlayer player) {
        super((TilePhi) tileEntity, player, 8, 105, 8, 163);
        if (tileEntity != null) {
            TilePhi tile = (TilePhi) tileEntity;
            this.addSlotToContainer(tile.getSlot0());
            this.addSlotToContainer(tile.getSlot1());
            this.addSlotToContainer(tile.getSlot2());
            this.addSlotToContainer(tile.getSlot3());
            this.addSlotToContainer(tile.getSlot4());
            this.addSlotToContainer(tile.getOut());
        } else OceanHeartR.getLogger().info("Oops,I don't think it's my problem.");
    }
}
