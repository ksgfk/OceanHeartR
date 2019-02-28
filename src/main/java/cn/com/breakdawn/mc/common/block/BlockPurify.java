package cn.com.breakdawn.mc.common.block;

import cn.com.breakdawn.mc.common.tile.TilePurify;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author KSGFK create in 2019/2/25
 */
public class BlockPurify extends BlockTileBase {
    public BlockPurify() {
        super(Material.IRON, 6);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TilePurify();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add(I18n.format("tooltip.purify.normal"));
    }
}
