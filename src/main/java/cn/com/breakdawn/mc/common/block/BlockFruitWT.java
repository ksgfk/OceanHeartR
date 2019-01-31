package cn.com.breakdawn.mc.common.block;

import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import java.util.Random;

public class BlockFruitWT extends Block {
    public BlockFruitWT() {
        super(Material.WOOD);
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        drops.add(0, new ItemStack(this));
    }

    @Override
    public int quantityDropped(Random random) {
        return random.nextInt(2);
    }
}
