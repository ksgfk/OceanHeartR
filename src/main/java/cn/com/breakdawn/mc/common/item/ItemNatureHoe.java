package cn.com.breakdawn.mc.common.item;

import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 自然之铲
 * KSGFK 创建于 2019/2/1
 */
public class ItemNatureHoe extends ItemHoe {
    public ItemNatureHoe(ToolMaterial material) {
        super(material);
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = player.getHeldItem(hand);

        if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
            return EnumActionResult.FAIL;
        } else {
            int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(itemstack, player, worldIn, pos);
            if (hook != 0) return hook > 0 ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;

            IBlockState iblockstate = worldIn.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if (facing != EnumFacing.DOWN && worldIn.isAirBlock(pos.up())) {
                if (block == Blocks.GRASS || block == Blocks.GRASS_PATH) {
                    this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
                    return setSeedOnFarmLand(player, worldIn, pos, itemstack);
                }

                if (block == Blocks.DIRT) {
                    switch (iblockstate.getValue(BlockDirt.VARIANT)) {
                        case DIRT:
                            this.setBlock(itemstack, player, worldIn, pos, Blocks.FARMLAND.getDefaultState());
                            return setSeedOnFarmLand(player, worldIn, pos, itemstack);
                        case COARSE_DIRT:
                            this.setBlock(itemstack, player, worldIn, pos, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
                            return EnumActionResult.SUCCESS;
                    }
                } else if (block == Blocks.FARMLAND) {
                    return setSeedOnFarmLand(player, worldIn, pos, itemstack);
                }
            }
            return EnumActionResult.PASS;
        }
    }

    private EnumActionResult setSeedOnFarmLand(EntityPlayer player, World worldIn, BlockPos pos, ItemStack itemstack) {
        BlockPos pos1 = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
        if (worldIn.isAirBlock(pos1) && pos.getY() + 1 < 255) {
            this.setBlock(itemstack, player, worldIn, pos1, Blocks.WHEAT.getDefaultState().withProperty(BlockCrops.AGE, 3));
            this.setDamage(itemstack, this.getDamage(itemstack) - 1);
            return EnumActionResult.SUCCESS;
        } else
            player.sendMessage(new TextComponentTranslation("tooltip.nature_hoe.player"));
        return EnumActionResult.SUCCESS;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("tooltip.nature_hoe.normal"));
    }
}
