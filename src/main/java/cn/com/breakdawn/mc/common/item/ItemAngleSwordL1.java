package cn.com.breakdawn.mc.common.item;

import cn.com.breakdawn.mc.util.Util;
import cofh.core.init.CoreEnchantments;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 天使之剑
 * KSGFK 创建于 2019/2/2
 */
public class ItemAngleSwordL1 extends ItemSwordBase {
    public static final Item.ToolMaterial ANGLE_L1 = EnumHelper.addToolMaterial("angle_l1", 3, 1561, 8.0F, 4.0F, 10);

    private static Map<Enchantment, Integer> enchMap = new HashMap<>();

    public ItemAngleSwordL1() {
        super(ANGLE_L1);
        this.attackDamage = 3.0F + ANGLE_L1.getAttackDamage();
        this.attackSpeed = -1;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            tooltip.add(I18n.format("tooltip.more"));
            tooltip.add(I18n.format("tooltip.canUp"));
        } else {
            tooltip.add(I18n.format("tooltip.angle_sword_l1.normal"));
            tooltip.add(I18n.format("tooltip.shift"));
        }
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        if (stack != null) {
            EnchantmentHelper.setEnchantments(enchMap, stack);
            Util.addUnbreakable(stack);
        }
        return false;//千万别开附魔效果,太卡了
    }

    static {
        enchMap.put(CoreEnchantments.soulbound, 3);
    }
}
