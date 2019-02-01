package cn.com.breakdawn.mc.common.item;

import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

/**
 * 自然之斧
 * KSGFK 创建于 2019/2/1
 */
public class ItemNatureAxe extends ItemAxe {
    public ItemNatureAxe(ToolMaterial material) {
        super(material, 4F, 0);
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
    }

    static Potion fast = Objects.requireNonNull(Potion.getPotionById(1));
    static Potion jump = Objects.requireNonNull(Potion.getPotionById(8));

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (entityIn instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityIn;
            if (player.getHeldItem(EnumHand.MAIN_HAND).getItem().equals(this) ||
                    (player.getHeldItem(EnumHand.OFF_HAND).getItem().equals(this))) {
                if (!(player.isPotionActive(fast) && player.isPotionActive(jump))) {
                    player.addPotionEffect(new PotionEffect(fast, 999999999, 1));
                    player.addPotionEffect(new PotionEffect(jump, 999999999, 1));
                }
            } else {
                if (((EntityPlayer) entityIn).isPotionActive(fast) && ((EntityPlayer) entityIn).isPotionActive(jump)) {
                    ((EntityPlayer) entityIn).removePotionEffect(fast);
                    ((EntityPlayer) entityIn).removePotionEffect(jump);
                }
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("tooltip.nature_axe.normal"));
    }
}
