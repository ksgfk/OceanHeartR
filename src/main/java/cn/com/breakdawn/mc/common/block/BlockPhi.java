package cn.com.breakdawn.mc.common.block;

import cn.com.breakdawn.mc.common.tile.TilePhi;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author KSGFK create in 2019/2/27
 */
public class BlockPhi extends BlockTileBase {
    public BlockPhi() {
        super(Material.IRON, 7);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TilePhi();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add(I18n.format("tooltip.phi.normal"));
    }
}
