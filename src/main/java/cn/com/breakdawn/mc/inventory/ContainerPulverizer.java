package cn.com.breakdawn.mc.inventory;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.tile.TilePulverizer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

/**
 * @author KSGFK
 */
public class ContainerPulverizer extends ContainerMachine<TilePulverizer> {
    public ContainerPulverizer(TileEntity tileEntity, EntityPlayer player) {
        super((TilePulverizer) tileEntity, player);
        if (tileEntity != null) {
            TilePulverizer tile = (TilePulverizer) tileEntity;
            this.addSlotToContainer(tile.getInputSlot());
            this.addSlotToContainer(tile.getOutputSlot());
        } else OceanHeartR.getLogger().error("It's impossible!");
    }
}
