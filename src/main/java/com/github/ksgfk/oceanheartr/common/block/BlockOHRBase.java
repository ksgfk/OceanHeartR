package com.github.ksgfk.oceanheartr.common.block;

import com.github.ksgfk.oceanheartr.OceanHeartR;
import com.github.ksgfk.oceanheartr.common.event.ClickOHRBlockEvent;
import com.github.ksgfk.oceanheartr.common.event.ExplosionDestroyOHRBlockEvent;
import com.github.ksgfk.oceanheartr.common.event.OHRBlockActiveEvent;
import com.github.ksgfk.oceanheartr.common.init.OHRCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

/**
 * @author KSGFK create in 2019/8/12
 */
public class BlockOHRBase extends Block {
    public BlockOHRBase(String name, Material blockMaterialIn) {
        super(blockMaterialIn);
        setRegistryName(OceanHeartR.MOD_ID, name);
        setTranslationKey(OceanHeartR.MOD_ID + "." + name);
        setCreativeTab(OHRCreativeTab.Main);
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        MinecraftForge.EVENT_BUS.post(new ClickOHRBlockEvent(worldIn, pos, worldIn.getBlockState(pos), playerIn));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        OHRBlockActiveEvent event = new OHRBlockActiveEvent(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
        event.setCanceled(false);
        return MinecraftForge.EVENT_BUS.post(event);
    }

    @Override
    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
        MinecraftForge.EVENT_BUS.post(new ExplosionDestroyOHRBlockEvent(worldIn, pos, worldIn.getBlockState(pos), explosionIn));
    }
}
