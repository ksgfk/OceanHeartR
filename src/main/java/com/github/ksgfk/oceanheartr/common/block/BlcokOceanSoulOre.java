package com.github.ksgfk.oceanheartr.common.block;

import com.github.ksgfk.oceanheartr.common.init.OHRBlocks;
import com.github.ksgfk.oceanheartr.common.init.OHRItems;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author KSGFK create in 2019/8/15
 */
public class BlcokOceanSoulOre extends BlockOHRBase {
    public BlcokOceanSoulOre() {
        super("ocean_soul_ore", Material.ICE);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            if (state.getBlock() == this) {
                ItemStack heldItem = playerIn.getHeldItem(EnumHand.MAIN_HAND);
                if (heldItem.getItem() == OHRItems.FrigidDrillingBitRtx3080) {
                    world.setBlockToAir(pos);
                    InventoryHelper.spawnItemStack(world,
                            pos.getX(),
                            pos.getY(),
                            pos.getZ(),
                            new ItemStack(OHRBlocks.OceanSoulOre));
                }
            }
        }
        return super.onBlockActivated(world, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
}
