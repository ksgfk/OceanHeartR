package cn.com.breakdawn.mc.common.block;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.tile.TileDynamoNature;
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
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 自然结晶发电机
 *
 * @author ksgfk
 */
public class BlockDynamoNature extends BlockCanDismantle {

    public BlockDynamoNature() {
        super(Material.IRON);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        NBTTagCompound nbt = new NBTTagCompound();
        TileDynamoNature dyn = new TileDynamoNature();
        dyn.readFromNBT(nbt);
        return dyn;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TileEntity e = worldIn.getTileEntity(pos);
            if (e instanceof TileDynamoNature) {
                ((TileDynamoNature) e).setPlayer((EntityPlayerMP) playerIn);
                playerIn.openGui(OceanHeartR.instance, 4, worldIn, pos.getX(), pos.getY(), pos.getZ());
                ((TileDynamoNature) e).setOpenGui(true);
            }
        }
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile != null) {
            worldIn.removeTileEntity(pos);
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (!worldIn.isRemote) {
            TileEntity e = worldIn.getTileEntity(pos);
            if (e instanceof TileDynamoNature && stack.getTagCompound() != null) {
                TileDynamoNature dyn = (TileDynamoNature) e;
                dyn.readFromNBT(stack.getTagCompound());
            }
        }
    }
/*
    @Override
    public ArrayList<ItemStack> dismantleBlock(World world, BlockPos pos, IBlockState state, EntityPlayer player, boolean returnDrops) {
        TileEntity tile = world.getTileEntity(pos);
        NBTTagCompound retTag = null;
        if (tile instanceof TileDynamoNature) {
            TileDynamoNature dyn = (TileDynamoNature) tile;
            retTag = dyn.writeToNBT(dyn.getNbtTagCompound());
            dyn.inventory = new ItemStack[dyn.inventory.length];
            Arrays.fill(dyn.inventory, ItemStack.EMPTY);
        }
        return dismantleDelegate(retTag, world, pos, player, returnDrops, false);
    }
*/
}
