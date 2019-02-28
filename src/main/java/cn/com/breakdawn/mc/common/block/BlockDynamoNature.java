package cn.com.breakdawn.mc.common.block;

import cn.com.breakdawn.mc.common.tile.TileDynamoNature;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 自然结晶发电机
 *
 * @author ksgfk
 */
public class BlockDynamoNature extends BlockTileBase {
    public BlockDynamoNature() {
        super(Material.IRON, 4);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileDynamoNature();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add(I18n.format("tooltip.dynamo_nature.normal"));
    }
}
