package cn.com.breakdawn.mc.common.block;

import cn.com.breakdawn.mc.common.tile.TilePulverizer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * @author KSGFK
 */
public class BlockPulverizer extends BlockTileBase {
    public BlockPulverizer() {
        super(Material.IRON, 5);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TilePulverizer();
    }
}
