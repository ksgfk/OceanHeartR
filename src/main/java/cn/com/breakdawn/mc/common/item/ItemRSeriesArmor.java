package cn.com.breakdawn.mc.common.item;

import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import cn.com.breakdawn.mc.common.init.OHRModel;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

/**
 * @author KSGFK create in 2019/5/11
 */
public class ItemRSeriesArmor extends ItemArmor {
    public ItemRSeriesArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return false;
    }

    @Nullable
    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        if (armorSlot == EntityEquipmentSlot.HEAD) {
            return OHRModel.r_78_2_heltmet;
        } else if (armorSlot == EntityEquipmentSlot.CHEST) {
            return OHRModel.r_78_2_chest;
        } else if (armorSlot == EntityEquipmentSlot.LEGS) {
            return OHRModel.r_78_2_leggings;
        }
        return null;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        if (slot == EntityEquipmentSlot.HEAD) {
            return OHRModel.r_78_2_heltmet_texture.toString();
        } else if (slot == EntityEquipmentSlot.CHEST) {
            return OHRModel.r_78_2_chest_texture.toString();
        } else if (slot == EntityEquipmentSlot.LEGS) {
            return OHRModel.r_78_2_leggings_texture.toString();
        }
        return null;
    }
}
