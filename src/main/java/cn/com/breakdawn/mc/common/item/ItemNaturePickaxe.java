package cn.com.breakdawn.mc.common.item;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 自然之镐
 * KSGFK 创建于 2019/2/1
 */
public class ItemNaturePickaxe extends ItemPickaxe {
    public ItemNaturePickaxe(ToolMaterial material) {
        super(material);
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = player.getHeldItem(hand);
        if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
            return EnumActionResult.FAIL;
        } else {
            destroyBlock(worldIn, itemstack, pos, facing);
            return EnumActionResult.SUCCESS;
        }
    }

    private void lessDamage(ItemStack itemstack, int damage) {
        OceanHeartR.getLogger().info(this.getDamage(itemstack));
        this.setDamage(itemstack, this.getDamage(itemstack) + damage);//什么鬼玩意,为啥镐子要加号,锄头是减...
    }

    /**
     * 破坏9X9的方块
     *
     * @param worldIn 破坏方块的世界
     * @param pos     方块坐标
     * @param facing  方块的面
     */
    private void destroyBlock(World worldIn, ItemStack itemstack, BlockPos pos, EnumFacing facing) {
        Block block;
        BlockPos blockPos;
        for (int a = -1; a <= 1; a++) {
            for (int b = -1; b <= 1; b++) {
                switch (facing) {
                    case DOWN:
                        blockPos = new BlockPos(pos.getX() + a, pos.getY(), pos.getZ() + b);
                        block = worldIn.getBlockState(blockPos).getBlock();
                        if (block.equals(Blocks.STONE)) {
                            worldIn.destroyBlock(blockPos, true);
                            lessDamage(itemstack, 9);
                        }
                        break;
                    case UP:
                        blockPos = new BlockPos(pos.getX() + a, pos.getY(), pos.getZ() + b);
                        block = worldIn.getBlockState(blockPos).getBlock();
                        if (block.equals(Blocks.STONE)) {
                            worldIn.destroyBlock(blockPos, true);
                            lessDamage(itemstack, 9);
                        }
                        break;
                    case EAST:
                        blockPos = new BlockPos(pos.getX(), pos.getY() + b, pos.getZ() + a);
                        block = worldIn.getBlockState(blockPos).getBlock();
                        if (block.equals(Blocks.STONE)) {
                            worldIn.destroyBlock(blockPos, true);
                            lessDamage(itemstack, 9);
                        }
                        break;
                    case WEST:
                        blockPos = new BlockPos(pos.getX(), pos.getY() + b, pos.getZ() + a);
                        block = worldIn.getBlockState(blockPos).getBlock();
                        if (block.equals(Blocks.STONE)) {
                            worldIn.destroyBlock(blockPos, true);
                            lessDamage(itemstack, 9);
                        }
                        break;
                    case NORTH:
                        blockPos = new BlockPos(pos.getX() + a, pos.getY() + b, pos.getZ());
                        block = worldIn.getBlockState(blockPos).getBlock();
                        if (block.equals(Blocks.STONE)) {
                            worldIn.destroyBlock(blockPos, true);
                            lessDamage(itemstack, 9);
                        }
                        break;
                    case SOUTH:
                        blockPos = new BlockPos(pos.getX() + a, pos.getY() + b, pos.getZ());
                        block = worldIn.getBlockState(blockPos).getBlock();
                        if (block.equals(Blocks.STONE)) {
                            worldIn.destroyBlock(blockPos, true);
                            lessDamage(itemstack, 9);
                        }
                        break;
                }
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("tooltip.nature_pickaxe.normal"));
    }
}
