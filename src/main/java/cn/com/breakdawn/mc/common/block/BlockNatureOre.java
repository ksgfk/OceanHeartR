/*
 * 大部分CV自原版 BlockStone
 */
package cn.com.breakdawn.mc.common.block;

import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import cn.com.breakdawn.mc.util.IMetaBlockRender;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;

public class BlockNatureOre extends Block implements IMetaBlockRender {
    public static final PropertyEnum<BlockNatureOre.EnumType> VARIANT = PropertyEnum.<BlockNatureOre.EnumType>create("variant", BlockNatureOre.EnumType.class);

    public BlockNatureOre() {
        super(Material.ROCK);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.OVERWORLD));
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);

    }

    @Override
    public String getLocalizedName() {
        return I18n.format(this.getUnlocalizedName() + "." + EnumType.OVERWORLD.getUnlocalizedName() + ".name");
    }

    /*
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return state.getValue(VARIANT) == EnumType.OVERWORLD ? Item.getItemFromBlock(Blocks.COBBLESTONE) : Item.getItemFromBlock(Blocks.STONE);
    }
    */

    @Override
    public int damageDropped(IBlockState state) {
        return ((BlockNatureOre.EnumType) state.getValue(VARIANT)).getMetadata();
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (BlockNatureOre.EnumType blockstone$enumtype : BlockNatureOre.EnumType.values()) {
            items.add(new ItemStack(this, 1, blockstone$enumtype.getMetadata()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, BlockNatureOre.EnumType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((BlockNatureOre.EnumType) state.getValue(VARIANT)).getMetadata();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{VARIANT});
    }

    @Override
    public void renderModel(Block block) {
        for (int a = 0; a < EnumType.values().length; a++) {
            //ClientProxy.registerRender(block, a);
            Item item = Item.getItemFromBlock(block);
            ModelLoader.setCustomModelResourceLocation(item, a, new ModelResourceLocation(block.getRegistryName() + "_" + EnumType.values()[a].getName(), "inventory"));
        }
    }

    public static enum EnumType implements IStringSerializable {
        OVERWORLD(0, MapColor.STONE, "overworld", true),
        OOO(1, MapColor.STONE, "ooo", true);

        /**
         * Array of the Block's BlockStates
         */
        private static final BlockNatureOre.EnumType[] META_LOOKUP = new BlockNatureOre.EnumType[values().length];
        /**
         * The BlockState's metadata.
         */
        private final int meta;
        /**
         * The EnumType's name.
         */
        private final String name;
        private final String unlocalizedName;
        private final MapColor mapColor;
        private final boolean isNatural;

        private EnumType(int p_i46383_3_, MapColor p_i46383_4_, String p_i46383_5_, boolean p_i46383_6_) {
            this(p_i46383_3_, p_i46383_4_, p_i46383_5_, p_i46383_5_, p_i46383_6_);
        }

        private EnumType(int p_i46384_3_, MapColor p_i46384_4_, String p_i46384_5_, String p_i46384_6_, boolean p_i46384_7_) {
            this.meta = p_i46384_3_;
            this.name = p_i46384_5_;
            this.unlocalizedName = p_i46384_6_;
            this.mapColor = p_i46384_4_;
            this.isNatural = p_i46384_7_;
        }

        /**
         * Returns the EnumType's metadata value.
         */
        public int getMetadata() {
            return this.meta;
        }

        public MapColor getMapColor() {
            return this.mapColor;
        }

        @Override
        public String toString() {
            return this.name;
        }

        /**
         * Returns an EnumType for the BlockState from a metadata value.
         */
        public static BlockNatureOre.EnumType byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        @Override
        public String getName() {
            return this.name;
        }

        public String getUnlocalizedName() {
            return this.unlocalizedName;
        }

        public boolean isNatural() {
            return this.isNatural;
        }

        static {
            for (BlockNatureOre.EnumType blockstone$enumtype : values()) {
                META_LOOKUP[blockstone$enumtype.getMetadata()] = blockstone$enumtype;
            }
        }
    }
}