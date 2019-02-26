package cn.com.breakdawn.mc.common.item;

import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import cn.com.breakdawn.mc.common.init.OHRModel;
import cn.com.breakdawn.mc.common.init.OHRPotion;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自然套装
 * KSGFK 创建于 2019/2/1
 */
public class ItemNatureArmor extends ItemArmor {
    private static Map<Enchantment, Integer> enchMap = new HashMap<>();

    public ItemNatureArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
        this.setMaxDamage(2990);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {//盔甲栏序号是反的...3是头盔,0是鞋子
        if (!world.isRemote) {
            boolean isNatureArmor = false;
            Iterable<ItemStack> armors = player.getArmorInventoryList();
            for (ItemStack armor : armors) {
                if (armor.isEmpty() || !(armor.getItem() instanceof ItemNatureArmor)) {
                    isNatureArmor = false;
                    break;
                } else isNatureArmor = true;
            }

            if (isNatureArmor && (!player.isPotionActive(OHRPotion.OXYGEN) || !player.isPotionActive(OHRPotion.FEED))) {
                player.addPotionEffect(new PotionEffect(OHRPotion.FEED, 20000, 0));
                player.addPotionEffect(new PotionEffect(OHRPotion.OXYGEN, 20000, 0));
            }

            if (!isNatureArmor && player.isPotionActive(OHRPotion.FEED)) {
                player.removePotionEffect(OHRPotion.FEED);
                player.removePotionEffect(OHRPotion.OXYGEN);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack) {
        if (armorType == EntityEquipmentSlot.CHEST)
            EnchantmentHelper.setEnchantments(enchMap, stack);
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (armorType == EntityEquipmentSlot.HEAD) tooltip.add(I18n.format("tooltip.nature_helmet.normal"));
        else if (armorType == EntityEquipmentSlot.CHEST) tooltip.add(I18n.format("tooltip.nature_chestplate.normal"));
        else if (armorType == EntityEquipmentSlot.LEGS) tooltip.add(I18n.format("tooltip.nature_leggings.normal"));
        else if (armorType == EntityEquipmentSlot.FEET) tooltip.add(I18n.format("tooltip.nature_boots.normal"));
    }

    @SideOnly(Side.CLIENT)
    @Nullable
    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        if (armorSlot.equals(EntityEquipmentSlot.LEGS)) return null;
        else if (armorSlot.equals(EntityEquipmentSlot.HEAD)) return OHRModel.HEAD_MODEL;
        else if (armorSlot.equals(EntityEquipmentSlot.CHEST)) return OHRModel.CHEST_MODEL;
        return super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
    }

    @SideOnly(Side.CLIENT)
    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        if (slot.equals(EntityEquipmentSlot.LEGS)) return OHRModel.LEGS_TEXTURE.toString();
        else if (slot.equals(EntityEquipmentSlot.HEAD)) return OHRModel.HEAD_TEXTURE.toString();
        else if (slot.equals(EntityEquipmentSlot.CHEST)) return OHRModel.CHEST_TEXTURE.toString();
        return super.getArmorTexture(stack, entity, slot, type);
    }

    static {
        enchMap.put(Enchantments.THORNS, 10);
    }
}
