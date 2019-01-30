package cn.com.breakdawn.mc.common.block;

import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.util.EnumFacing;

/**
 * 装饰品
 * KSGFK 创建于 2019/1/30
 */
public class BlockDecoration extends BlockRotatedPillar {
    //public static final PropertyEnum<BlockDecoration.EnumType> VARIANT = PropertyEnum.create("variant", BlockDecoration.EnumType.class);

    public BlockDecoration() {
        super(Material.IRON);
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
        //this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.Y).withProperty(VARIANT, EnumType.BLACK));
        this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.Y));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        //return new BlockStateContainer(this, AXIS, VARIANT);
        return new BlockStateContainer(this, AXIS);
    }
    /*
    @Override
    public void renderModel(Block block) {
        for (int a = 0; a < EnumType.values().length; a++) {
            Item item = Item.getItemFromBlock(block);
            ModelLoader.setCustomModelResourceLocation(item, a, new ModelResourceLocation(block.getRegistryName() + "_" + EnumType.values()[a].getName(), "inventory"));
        }
    }

    public enum EnumType implements IStringSerializable {
        BLACK(0, "black");

        private final int meta;
        private final String name;
        private final String unlocalizedName;

        EnumType(int meta, String name) {
            this.meta = meta;
            this.name = name;
            this.unlocalizedName = name;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }
    */
}
