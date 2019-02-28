package cn.com.breakdawn.mc.inventory;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.tile.TilePurify;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

/**
 * @author KSGFK create in 2019/2/25
 */
public class ContainerPurify extends ContainerMachine<TilePurify> {
    public ContainerPurify(TileEntity tileEntity, EntityPlayer player) {
        super((TilePurify) tileEntity, player);
        if (tileEntity != null) {
            TilePurify tile = (TilePurify) tileEntity;
            this.addSlotToContainer(tile.getInputSlot());
            this.addSlotToContainer(tile.getOutputSlot());
        } else OceanHeartR.getLogger().error("It's magic!");
    }
}
