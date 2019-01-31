package cn.com.breakdawn.mc.common.block;

import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import net.minecraft.block.BlockBush;

/**
 * 种子
 * KSGFK 创建于 2019/1/30
 */
public class BlockOHRSaplings extends BlockBush {
    //public static final PropertyEnum<BlockOHRPlanks.EnumType> VARIANT = PropertyEnum.<BlockOHRPlanks.EnumType>create("variant", BlockOHRPlanks.EnumType.class);

    public BlockOHRSaplings() {
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
        //this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockOHRPlanks.EnumType.YGGDRASILL));
    }
    /*
    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(VARIANT).getMetadata();
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (BlockOHRPlanks.EnumType blockstone$enumtype : BlockOHRPlanks.EnumType.values()) {
            items.add(new ItemStack(this, 1, blockstone$enumtype.getMetadata()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, BlockOHRPlanks.EnumType.byMetadata(meta));
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
        for (int a = 0; a < BlockOHRPlanks.EnumType.values().length; a++) {
            Item item = Item.getItemFromBlock(block);
            ModelLoader.setCustomModelResourceLocation(item, a, new ModelResourceLocation(block.getRegistryName() + "_" + BlockOHRPlanks.EnumType.values()[a].getName(), "inventory"));
        }
    }
    */
}
