package cn.com.breakdawn.mc.common.block;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.block.dynamo.TileDynamoNature;
import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import cofh.api.block.IDismantleable;
import cofh.core.util.CoreUtils;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 自然结晶发电机
 *
 * @author ksgfk
 */
public class BlockDynamoNature extends BlockContainer implements IDismantleable {

    public BlockDynamoNature() {
        super(Material.IRON);
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
        this.setHardness(15.0F);
        this.setResistance(25.0F);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
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
    public EnumBlockRenderType getRenderType(IBlockState state) {//渲染类型设为普通方块
        return EnumBlockRenderType.MODEL;
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
    /*
    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        //TileEntity tile = worldIn.getTileEntity(pos);
        //IInventory inv = (IInventory) tile;
        //CoreUtils.dropItemStackIntoWorld(inv.getStackInSlot(0), worldIn, new Vec3d(pos));
        //if (tile != null) {
            worldIn.removeTileEntity(pos);
        //}
    }
    */
    @Override
    public ArrayList<ItemStack> dismantleBlock(World world, BlockPos pos, IBlockState state, EntityPlayer player, boolean returnDrops) {
        TileEntity tile = world.getTileEntity(pos);
        NBTTagCompound retTag = null;
        if (tile instanceof TileDynamoNature) {
            TileDynamoNature dyn = (TileDynamoNature) tile;
            retTag = dyn.getNbtTagCompound().getCompoundTag("info");
            IInventory inv = (IInventory) tile;
            CoreUtils.dropItemStackIntoWorld(inv.getStackInSlot(0), world, new Vec3d(pos));
            Arrays.fill(dyn.inventory, ItemStack.EMPTY);
        }
        return dismantleDelegate(retTag, world, pos, player, returnDrops, false);
    }

    private ArrayList<ItemStack> dismantleDelegate(NBTTagCompound nbt, World world, BlockPos pos, EntityPlayer player, boolean returnDrops, boolean simulate) {
        TileEntity tile = world.getTileEntity(pos);
        IBlockState state = world.getBlockState(pos);
        int meta = state.getBlock().getMetaFromState(state);
        ArrayList<ItemStack> ret = new ArrayList<>();

        if (state.getBlock() != this) {
            return ret;
        }
        ItemStack dropBlock = new ItemStack(this, 1, meta);

        if (nbt != null) {
            dropBlock.setTagCompound(nbt);
        }
        if (!simulate) {
            //if (tile instanceof TileCore) {
            //    ((TileCore) tile).blockDismantled();
            //}
            world.setBlockToAir(pos);

            if (!returnDrops) {
                float f = 0.3F;
                double x2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
                double y2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
                double z2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
                EntityItem dropEntity = new EntityItem(world, pos.getX() + x2, pos.getY() + y2, pos.getZ() + z2, dropBlock);
                dropEntity.setPickupDelay(10);
                //if (tile instanceof ISecurable && !((ISecurable) tile).getAccess().isPublic()) {
                //    dropEntity.setOwner(player.getName());
                // Set Owner - ensures dismantling player can pick it up first.
                //}
                world.spawnEntity(dropEntity);

                if (player != null) {
                    CoreUtils.dismantleLog(player.getName(), state.getBlock(), meta, pos);
                }
            }
        }
        ret.add(dropBlock);
        return ret;
    }

    @Override
    public boolean canDismantle(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        return true;
    }
}
