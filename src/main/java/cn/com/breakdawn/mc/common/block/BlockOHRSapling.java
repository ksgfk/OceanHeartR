package cn.com.breakdawn.mc.common.block;

import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import cn.com.breakdawn.mc.util.IMetaBlockRender;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;

/**
 * 所有种子
 * KSGFK 创建于 2019/1/30
 * TODO:添加种子模型以及贴图
 */
public class BlockOHRSapling extends BlockBush implements IMetaBlockRender {
    public static final PropertyEnum<BlockOHRPlanks.EnumType> TYPE = PropertyEnum.<BlockOHRPlanks.EnumType>create("type", BlockOHRPlanks.EnumType.class);

    public BlockOHRSapling() {
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, BlockOHRPlanks.EnumType.YGGDRASILL));
    }

    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(TYPE).getMetadata();
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (BlockOHRPlanks.EnumType blockstone$enumtype : BlockOHRPlanks.EnumType.values()) {
            items.add(new ItemStack(this, 1, blockstone$enumtype.getMetadata()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, BlockOHRPlanks.EnumType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(TYPE).getMetadata();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, TYPE);
    }

    @Override
    public void renderModel(Block block) {
        for (int a = 0; a < BlockOHRPlanks.EnumType.values().length; a++) {
            Item item = Item.getItemFromBlock(block);
            ModelLoader.setCustomModelResourceLocation(item, a, new ModelResourceLocation(block.getRegistryName() + "_" + BlockStrangeOre.EnumType.values()[a].getName(), "inventory"));
        }
    }
}
