package cn.com.breakdawn.mc.common.item;

import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import cn.com.breakdawn.mc.common.init.OHRItems;
import cn.com.breakdawn.mc.util.Util;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 自然套装
 * KSGFK 创建于 2019/2/1
 */
public class ItemNatureArmor extends ItemArmor {
    /**
     * 最后一次回饱食度的时间
     */
    private int lastTime;
    private int airTime;

    public ItemNatureArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
        this.setMaxDamage(2990);

        lastTime = 0;
        airTime = 0;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {//盔甲栏序号是反的...3是头盔,0是鞋子
        if (armorType == EntityEquipmentSlot.HEAD) {
            if (lastTime < 200) lastTime++;
            else {
                player.getFoodStats().addStats(1, 1F);
                lastTime = 0;
            }
        }

        if (armorType == EntityEquipmentSlot.LEGS) {
            if (airTime < 20) airTime++;
            else {
                if (player.inventory.armorInventory.get(0).getItem().equals(OHRItems.NATURE_BOOTS)) {
                    player.setAir(player.getAir() + 15);
                    airTime = 0;
                }
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (armorType == EntityEquipmentSlot.HEAD) tooltip.add(I18n.format("tooltip.nature_helmet.normal"));
        else if (armorType == EntityEquipmentSlot.CHEST) {
            Util.addEnchantment(stack, (short) 7, (short) 10);//荆棘
            tooltip.add(I18n.format("tooltip.nature_chestplate.normal"));
        } else if (armorType == EntityEquipmentSlot.LEGS) tooltip.add(I18n.format("tooltip.nature_leggings.normal"));
        else if (armorType == EntityEquipmentSlot.FEET) tooltip.add(I18n.format("tooltip.nature_boots.normal"));
    }
}
