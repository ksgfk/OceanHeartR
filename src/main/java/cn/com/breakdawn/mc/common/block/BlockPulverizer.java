package cn.com.breakdawn.mc.common.block;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.tile.TilePulverizer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * @author KSGFK
 */
public class BlockPulverizer extends BlockTileBase {
    public BlockPulverizer() {
        super(Material.IRON);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        NBTTagCompound nbt = new NBTTagCompound();
        TilePulverizer pul = new TilePulverizer();
        pul.readFromNBT(nbt);
        return pul;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TileEntity e = worldIn.getTileEntity(pos);
            if (e instanceof TilePulverizer) {
                ((TilePulverizer) e).setPlayer((EntityPlayerMP) playerIn);
                playerIn.openGui(OceanHeartR.instance, 5, worldIn, pos.getX(), pos.getY(), pos.getZ());
                ((TilePulverizer) e).setOpenGui(true);
            }
        }
        return true;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (!worldIn.isRemote) {
            TileEntity e = worldIn.getTileEntity(pos);
            if (e instanceof TilePulverizer && stack.getTagCompound() != null) {
                TilePulverizer dyn = (TilePulverizer) e;
                dyn.readFromNBT(stack.getTagCompound());
            }
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile != null) {
            worldIn.removeTileEntity(pos);
        }
    }
}
