package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.client.gui.GuiContainerRedPacket;
import cn.com.breakdawn.mc.inventory.ContainerRedPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nullable;
import java.util.Objects;

public class OHRGui implements IGuiHandler {
    public void init() {
        NetworkRegistry.INSTANCE.registerGuiHandler(OceanHeartR.instance, this);
    }

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 3:
                return new ContainerRedPacket(player);
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
            default:
                return null;
        }
    }
}
