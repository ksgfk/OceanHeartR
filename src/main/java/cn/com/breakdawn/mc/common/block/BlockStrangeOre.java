package cn.com.breakdawn.mc.common.block;

import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import cn.com.breakdawn.mc.util.IMetaBlockRender;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 奇怪矿石系列
 * KSGFK 创建于 2019/1/30
 */
public class BlockStrangeOre extends Block implements IMetaBlockRender {
    public static final PropertyEnum<BlockStrangeOre.EnumType> VARIANT = PropertyEnum.create("variant", BlockStrangeOre.EnumType.class);

    public BlockStrangeOre() {
        super(Material.ROCK);
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.GOLD));
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
    }

    /*
        @Override
        public String getLocalizedName() {
            return I18n.format(this.getUnlocalizedName() + "." + EnumType.GOLD.getUnlocalizedName() + ".name");
        }
    */
    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(VARIANT).getMetadata();
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for (BlockStrangeOre.EnumType blockstone$enumtype : BlockStrangeOre.EnumType.values()) {
            items.add(new ItemStack(this, 1, blockstone$enumtype.getMetadata()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, BlockStrangeOre.EnumType.byMetadata(meta));
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

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        int meta = stack.getMetadata();
        switch (meta) {
            case 0:
                tooltip.add(I18n.format("tooltip.strange_ore.gold.normal"));
                break;
            case 1:
                tooltip.add(I18n.format("tooltip.strange_ore.leve_soul.normal"));
                break;
            case 2:
                tooltip.add(I18n.format("tooltip.strange_ore.ocean_soul.normal"));
                break;
            case 3:
                tooltip.add(I18n.format("tooltip.strange_ore.end.normal"));
                break;
        }
    }

    public enum EnumType implements IStringSerializable {
        GOLD(0, MapColor.STONE, "gold", true),
        LEVE_SOUL(1, MapColor.STONE, "leve_soul", true),
        OCEAN_SOUL(2, MapColor.STONE, "ocean_soul", true),
        END(3, MapColor.STONE, "end", true);

        /**
         * Array of the Block's BlockStates
         */
        private static final BlockStrangeOre.EnumType[] META_LOOKUP = new BlockStrangeOre.EnumType[values().length];
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

        EnumType(int p_i46383_3_, MapColor p_i46383_4_, String p_i46383_5_, boolean p_i46383_6_) {
            this(p_i46383_3_, p_i46383_4_, p_i46383_5_, p_i46383_5_, p_i46383_6_);
        }

        EnumType(int p_i46384_3_, MapColor p_i46384_4_, String p_i46384_5_, String p_i46384_6_, boolean p_i46384_7_) {
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
        public static BlockStrangeOre.EnumType byMetadata(int meta) {
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

        public static EnumType byItemStack(ItemStack stack) {
            return byMetadata(stack.getMetadata());
        }

        static {
            for (BlockStrangeOre.EnumType blockstone$enumtype : values()) {
                META_LOOKUP[blockstone$enumtype.getMetadata()] = blockstone$enumtype;
            }
        }
    }
}
