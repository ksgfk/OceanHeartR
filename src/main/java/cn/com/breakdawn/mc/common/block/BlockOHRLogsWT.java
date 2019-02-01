package cn.com.breakdawn.mc.common.block;

import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.util.EnumFacing;

/**
 * 世界树树干
 * KSGFK 创建于 2019/1/31
 */
public class BlockOHRLogsWT extends BlockRotatedPillar {
    //public static final PropertyEnum<BlockOHRPlanksWT.EnumType> VARIANT = PropertyEnum.create("variant", BlockOHRPlanksWT.EnumType.class, input -> Objects.requireNonNull(input).getMetadata() >= 4);
    //public static final PropertyEnum<BlockOHRLogsWT.EnumAxis> LOG_AXIS = PropertyEnum.create("axis", BlockOHRLogsWT.EnumAxis.class);

    public BlockOHRLogsWT() {
        super(Material.WOOD);
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
        this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.Y));
    }

    /*
    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this, 1, BlockOHRPlanksWT.EnumType.YGGDRASILL.getMetadata() - 4));
    }
    */
    /*
    @Override
    public IBlockState getStateFromMeta(int meta) {
        /*
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, BlockOHRPlanksWT.EnumType.byMetadata((meta & 3) + 4));

        switch (meta & 12) {
            case 0:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockOHRLogsWT.EnumAxis.Y);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockOHRLogsWT.EnumAxis.X);
                break;
            case 8:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockOHRLogsWT.EnumAxis.Z);
                break;
            default:
                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockOHRLogsWT.EnumAxis.NONE);
        }

        return iblockstate;
        */
    /*
        switch (meta) {
            case 0:
                return this.getDefaultState().withProperty(LOG_AXIS, EnumAxis.Y);
            case 1:
                return this.getDefaultState().withProperty(LOG_AXIS, EnumAxis.X);
            case 2:
                return this.getDefaultState().withProperty(LOG_AXIS, EnumAxis.Z);
            default:
                return this.getDefaultState().withProperty(LOG_AXIS, EnumAxis.NONE);
        }
    }
    */
    /*
    @Override
    public int getMetaFromState(IBlockState state) {
        /*
        int i = 0;
        i = i | state.getValue(VARIANT).getMetadata() - 4;

        switch (state.getValue(LOG_AXIS)) {
            case X:
                i |= 4;
                break;
            case Z:
                i |= 8;
                break;
            case NONE:
                i |= 12;
        }

        return i;
        */
        /*
        switch (state.getValue(LOG_AXIS)) {
            case X:
                return 0;
            case Z:
                return 1;
            case NONE:
                return 2;
            default:
                return 3;
        }
    }
    */

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, AXIS);
    }

    /*
    @Override
    public int damageDropped(IBlockState state) {
        return state.getValue(VARIANT).getMetadata() - 4;
    }
    */
    /*
    @Override
    public void renderModel(Block block) {
        for (int a = 0; a < BlockOHRPlanksWT.EnumType.values().length; a++) {
            Item item = Item.getItemFromBlock(block);
            ModelLoader.setCustomModelResourceLocation(item, a, new ModelResourceLocation(block.getRegistryName() + "_" + BlockOHRPlanksWT.EnumType.values()[a].getName(), "inventory"));
        }
    }
    */
    /*
    public enum EnumAxis implements IStringSerializable {
        X("x"),
        Y("y"),
        Z("z"),
        NONE("none");

        private final String name;

        EnumAxis(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }

        public static BlockOHRLogsWT.EnumAxis fromFacingAxis(BlockOHRLogsWT.EnumAxis axis) {
            switch (axis) {
                case X:
                    return X;
                case Y:
                    return Y;
                case Z:
                    return Z;
                default:
                    return NONE;
            }
        }

        public String getName() {
            return this.name;
        }
    }
    */
}
