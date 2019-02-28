package cn.com.breakdawn.mc.inventory;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.tile.TileDynamoNature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

/**
 * @author KSGFK
 */
public class ContainerDynamoNature extends ContainerMachine<TileDynamoNature> {
    public ContainerDynamoNature(TileEntity tileEntity, EntityPlayer player) {
        super((TileDynamoNature) tileEntity, player);
        if (tileEntity != null) {
            this.addSlotToContainer(((TileDynamoNature) tileEntity).getInputSlot());
        } else OceanHeartR.getLogger().error("How can it be!");
    }
}
