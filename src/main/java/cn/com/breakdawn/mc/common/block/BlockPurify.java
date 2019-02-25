package cn.com.breakdawn.mc.common.block;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.tile.TilePurify;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * @author KSGFK create in 2019/2/25
 */
public class BlockPurify extends BlockCanDismantle {
    public BlockPurify() {
        super(Material.IRON);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        NBTTagCompound nbt = new NBTTagCompound();
        TilePurify pul = new TilePurify();
        pul.readFromNBT(nbt);
        return pul;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TileEntity e = worldIn.getTileEntity(pos);
            if (e instanceof TilePurify) {
                ((TilePurify) e).setPlayer((EntityPlayerMP) playerIn);
                playerIn.openGui(OceanHeartR.instance, 6, worldIn, pos.getX(), pos.getY(), pos.getZ());
                ((TilePurify) e).setOpenGui(true);
            }
        }
        return true;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (!worldIn.isRemote) {
            TileEntity e = worldIn.getTileEntity(pos);
            if (e instanceof TilePurify && stack.getTagCompound() != null) {
                TilePurify dyn = (TilePurify) e;
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
/*
    @Override
    public ArrayList<ItemStack> dismantleBlock(World world, BlockPos pos, IBlockState state, EntityPlayer player, boolean returnDrops) {
        TileEntity tile = world.getTileEntity(pos);
        NBTTagCompound retTag = null;
        if (tile instanceof TilePurify) {
            TilePurify pul = (TilePurify) tile;
            retTag = pul.writeToNBT(pul.getNbt());
            pul.inventory = new ItemStack[pul.inventory.length];
            Arrays.fill(pul.inventory, ItemStack.EMPTY);
        }
        return this.dismantleDelegate(retTag, world, pos, player, returnDrops, false);
    }
*/
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
