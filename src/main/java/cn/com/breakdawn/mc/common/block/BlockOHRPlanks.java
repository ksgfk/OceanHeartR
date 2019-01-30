package cn.com.breakdawn.mc.common.block;

import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import cn.com.breakdawn.mc.util.IMetaBlockRender;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;

/**
 * 所有木板
 * KSGFK 创建于 2019/1/30
 */
public class BlockOHRPlanks extends Block implements IMetaBlockRender {
    public static final PropertyEnum<BlockOHRPlanks.EnumType> VARIANT = PropertyEnum.create("variant", BlockOHRPlanks.EnumType.class);

    public BlockOHRPlanks() {
        super(Material.WOOD);
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.YGGDRASILL));
    }

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
        for (int a = 0; a < EnumType.values().length; a++) {
            Item item = Item.getItemFromBlock(block);
            ModelLoader.setCustomModelResourceLocation(item, a, new ModelResourceLocation(block.getRegistryName() + "_" + EnumType.values()[a].getName(), "inventory"));
        }
    }

    public enum EnumType implements IStringSerializable {
        YGGDRASILL(0, "yggdrasill");

        private static final BlockOHRPlanks.EnumType[] META_LOOKUP = new BlockOHRPlanks.EnumType[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;

        EnumType(int metaIn, String nameIn) {
            this(metaIn, nameIn, nameIn);
        }

        EnumType(int metaIn, String nameIn, String unlocalizedNameIn) {
            this.meta = metaIn;
            this.name = nameIn;
            this.unlocalizedName = unlocalizedNameIn;
        }

        public int getMetadata() {
            return this.meta;
        }

        public String toString() {
            return this.name;
        }

        public static BlockOHRPlanks.EnumType byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName() {
            return this.name;
        }

        public String getUnlocalizedName() {
            return this.unlocalizedName;
        }

        static {
            for (BlockOHRPlanks.EnumType BlockOHRPlanks$enumtype : values()) {
                META_LOOKUP[BlockOHRPlanks$enumtype.getMetadata()] = BlockOHRPlanks$enumtype;
            }
        }
    }
}
