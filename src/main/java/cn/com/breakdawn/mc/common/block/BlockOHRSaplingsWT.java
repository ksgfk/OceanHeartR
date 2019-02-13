package cn.com.breakdawn.mc.common.block;

import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import cn.com.breakdawn.mc.config.OHRConfig;
import cn.com.breakdawn.mc.world.gen.GenYggdrasillTree;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/**
 * 种子
 * KSGFK 创建于 2019/1/30
 */
public class BlockOHRSaplingsWT extends BlockBush implements IGrowable {
    //public static final PropertyEnum<BlockOHRPlanksWT.EnumType> VARIANT = PropertyEnum.<BlockOHRPlanksWT.EnumType>create("variant", BlockOHRPlanksWT.EnumType.class);

    public BlockOHRSaplingsWT() {
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
        //this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockOHRPlanksWT.EnumType.YGGDRASILL));
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return worldIn.rand.nextFloat() < 0.45D;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        if (OHRConfig.general.canYggdrasillGrow) {
            if (!(worldIn.isRemote || !TerrainGen.saplingGrowTree(worldIn, rand, pos))) {
                new GenYggdrasillTree(true).setSloped(true).generate(worldIn, rand, pos);
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, player, tooltip, advanced);
    }

    /*
    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(VARIANT).getMetadata();
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (BlockOHRPlanksWT.EnumType blockstone$enumtype : BlockOHRPlanksWT.EnumType.values()) {
            items.add(new ItemStack(this, 1, blockstone$enumtype.getMetadata()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, BlockOHRPlanksWT.EnumType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(VARIANT).getMetadata();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, VARIANT);
    }

    @Override
    public void renderModel(Block block) {
        for (int a = 0; a < BlockOHRPlanksWT.EnumType.values().length; a++) {
            Item item = Item.getItemFromBlock(block);
            ModelLoader.setCustomModelResourceLocation(item, a, new ModelResourceLocation(block.getRegistryName() + "_" + BlockOHRPlanksWT.EnumType.values()[a].getName(), "inventory"));
        }
    }
    */
}
