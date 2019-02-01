package cn.com.breakdawn.mc.common.block;

import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * 世界树木板
 * KSGFK 创建于 2019/1/30
 */
public class BlockOHRPlanksWT extends Block {
    //public static final PropertyEnum<BlockOHRPlanksWT.EnumType> VARIANT = PropertyEnum.create("variant", BlockOHRPlanksWT.EnumType.class);

    public BlockOHRPlanksWT() {
        super(Material.WOOD);
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
        //this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.YGGDRASILL));
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
        for (int a = 0; a < EnumType.values().length; a++) {
            Item item = Item.getItemFromBlock(block);
            ModelLoader.setCustomModelResourceLocation(item, a, new ModelResourceLocation(block.getRegistryName() + "_" + EnumType.values()[a].getName(), "inventory"));
        }
    }

    public enum EnumType implements IStringSerializable {
        YGGDRASILL(0, "yggdrasill"),
        ABCE(1, "abce");

        private static final BlockOHRPlanksWT.EnumType[] META_LOOKUP = new BlockOHRPlanksWT.EnumType[values().length];
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

        public static BlockOHRPlanksWT.EnumType byMetadata(int meta) {
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
            for (BlockOHRPlanksWT.EnumType BlockOHRPlanksWT$enumtype : values()) {
                META_LOOKUP[BlockOHRPlanksWT$enumtype.getMetadata()] = BlockOHRPlanksWT$enumtype;
            }
        }
    }
    */
}
