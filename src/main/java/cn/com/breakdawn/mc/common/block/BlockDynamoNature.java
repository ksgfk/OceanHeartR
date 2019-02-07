package cn.com.breakdawn.mc.common.block;

import cn.com.breakdawn.mc.common.block.dynamo.TileDynamoNature;
import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockDynamoNature extends BlockContainer {
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
    public TileEntity createNewTileEntity(World worldIn, int meta) {//创建TileEntity
        return new TileDynamoNature();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {//渲染类型设为普通方块
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {

        }
        if (!worldIn.isRemote) {

        }
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        super.breakBlock(worldIn, pos, state);
    }
}
