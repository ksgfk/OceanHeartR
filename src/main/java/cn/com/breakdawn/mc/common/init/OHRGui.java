package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.client.gui.*;
import cn.com.breakdawn.mc.inventory.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nullable;

/**
 * @author ksgfk
 */
public class OHRGui implements IGuiHandler {
    private GuiContainerDynNature dynNature;
    private GuiContainerPul pul;
    private GuiContainerPurify purify;
    private GuiContainerPhi phi;

    public void init() {
        NetworkRegistry.INSTANCE.registerGuiHandler(OceanHeartR.instance, this);
    }

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 3:
                return new ContainerRedPacket(player);
            case 4:
                return new ContainerDynamoNature(world.getTileEntity(new BlockPos(x, y, z)), player);
            case 5:
                return new ContainerPulverizer(world.getTileEntity(new BlockPos(x, y, z)), player);
            case 6:
                return new ContainerPurify(world.getTileEntity(new BlockPos(x, y, z)), player);
            case 7:
                return new ContainerPhi(world.getTileEntity(new BlockPos(x, y, z)), player);
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 3:
                return new GuiContainerRedPacket(player);
            case 4:
                return dynNature = new GuiContainerDynNature(new ContainerDynamoNature(world.getTileEntity(new BlockPos(x, y, z)), player));
            case 5:
                return pul = new GuiContainerPul(new ContainerPulverizer(world.getTileEntity(new BlockPos(x, y, z)), player));
            case 6:
                return purify = new GuiContainerPurify(new ContainerPurify(world.getTileEntity(new BlockPos(x, y, z)), player));
            case 7:
                return phi = new GuiContainerPhi(new ContainerPhi(world.getTileEntity(new BlockPos(x, y, z)), player));
            default:
                return null;
        }
    }

    public GuiContainerDynNature getDynNature() {
        return dynNature;
    }

    public GuiContainerPul getPul() {
        return pul;
    }

    public GuiContainerPurify getPurify() {
        return purify;
    }

    public GuiContainerPhi getPhi() {
        return phi;
    }
}
