package cn.com.breakdawn.mc.common.item;

import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import cn.com.breakdawn.mc.util.Util;
import cofh.core.init.CoreEnchantments;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 天使之剑
 * KSGFK 创建于 2019/2/2
 */
public class ItemAngleSwordL1 extends ItemTool {
    public static final Item.ToolMaterial ANGLE_L1 = EnumHelper.addToolMaterial("angle_l1", 3, 1561, 8.0F, 4.0F, 10);
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE);

    private static Map<Enchantment, Integer> enchMap = new HashMap<>();

    public ItemAngleSwordL1() {
        super(ANGLE_L1, EFFECTIVE_ON);
        this.maxStackSize = 1;
        this.setMaxDamage(ANGLE_L1.getMaxUses());
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
        this.attackDamage = 3.0F + ANGLE_L1.getAttackDamage();
        this.attackSpeed = -1;
    }

    //Copy from vanilla ItemSword
    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        Block block = state.getBlock();

        if (block == Blocks.WEB) {
            return 15.0F;
        } else {
            Material material = state.getMaterial();
            return material != Material.PLANTS && material != Material.VINE && material != Material.CORAL && material != Material.LEAVES && material != Material.GOURD ? 1.0F : 1.5F;
        }
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        if ((double) state.getBlockHardness(worldIn, pos) != 0.0D) {
            stack.damageItem(2, entityLiving);
        }

        return true;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        stack.damageItem(1, attacker);
        return true;
    }

    @Override
    public boolean canHarvestBlock(IBlockState blockIn) {
        return blockIn.getBlock() == Blocks.WEB;
    }
    //copy end

    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        //Util.addUnbreakable(stack);
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            tooltip.add(I18n.format("tooltip.more"));
            tooltip.add(I18n.format("tooltip.canUp"));
            tooltip.add("");
        } else {
            tooltip.add(I18n.format("tooltip.angle_sword_l1.normal"));
            tooltip.add("");
            tooltip.add(I18n.format("tooltip.shift"));
            tooltip.add("");
        }
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        if (stack != null) {
            EnchantmentHelper.setEnchantments(enchMap, stack);
            Util.addUnbreakable(stack);
            return true;
        }
        return false;
    }

    static {
        enchMap.put(CoreEnchantments.soulbound, 3);
    }
}
