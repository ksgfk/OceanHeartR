package cn.com.breakdawn.mc.common.enchantment;

import cn.com.breakdawn.mc.OceanHeartR;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;

/**
 * @author KSGFK create in 2019/2/25
 */
public class EnchSoulBond extends Enchantment {
    public static boolean permanent = false;

    public EnchSoulBond() {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ALL, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
        this.setRegistryName(OceanHeartR.MODID + ":soulbond");
        this.setName(OceanHeartR.MODID + ".soulbond");
    }

    @Override
    public int getMinEnchantability(int level) {
        return 1 + (level - 1) * 10;
    }

    @Override
    public int getMaxEnchantability(int level) {
        return getMinEnchantability(level) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean canApplyTogether(Enchantment ench) {
        return super.canApplyTogether(ench) && ench != Enchantments.VANISHING_CURSE;
    }
}
