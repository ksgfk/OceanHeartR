package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.item.*;
import cn.com.breakdawn.mc.util.RegItem;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

/**
 * 初始化所有物品的类
 * 非本地化名称等于物品名
 * KSGFK 创建于 2019/1/25
 */
public final class OHRItems {
    /*工具材料,用来设置工具的基本属性,addToolMaterial()的参数依次是:工具材料名称,硬度,最大使用次数,挖掘效率,伤害(真实伤害-4才是这里要填的),附魔因素*/
    public static final Item.ToolMaterial NATURE = EnumHelper.addToolMaterial("nature", 3, 1561, 8, 4, 10);
    public static final ItemArmor.ArmorMaterial NATURE_ARMOR = EnumHelper.addArmorMaterial("nature", OceanHeartR.MODID + ":nature", 9999, new int[]{6, 12, 14, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F);

    @RegItem("first_item")
    public static final Item FIRST = new ItemBase();

    @RegItem("nature_sword")
    public static final Item NATURE_SWORD = new ItemNatureSword(NATURE);

    @RegItem("nature_axe")
    public static final Item NATURE_AXE = new ItemNatureAxe(NATURE);

    @RegItem("nature_hoe")
    public static final Item NATURE_HOE = new ItemNatureHoe(NATURE);

    @RegItem("nature_pickaxe")
    public static final Item NATURE_PICKAXE = new ItemNaturePickaxe(NATURE);

    @RegItem("nature_helmet")
    public static final Item NATURE_HELMET = new ItemNatureArmor(NATURE_ARMOR, 3, EntityEquipmentSlot.HEAD);

    @RegItem("nature_chestplate")
    public static final Item NATURE_CHESTPLATE = new ItemNatureArmor(NATURE_ARMOR, 3, EntityEquipmentSlot.CHEST);

    @RegItem("nature_leggings")
    public static final Item NATURE_LEGGINGS = new ItemNatureArmor(NATURE_ARMOR, 3, EntityEquipmentSlot.LEGS);

    @RegItem("nature_boots")
    public static final Item NATURE_BOOTS = new ItemNatureArmor(NATURE_ARMOR, 3, EntityEquipmentSlot.FEET);

    @RegItem("nature_sword_polluted")
    public static final Item NATURE_SWORD_P = new ItemNatureSwordP(NATURE);

    @RegItem(value = "icon", className = "ItemIcon", isRegisterMultiTextureItem = true)
    public static final Item ICON = new ItemIcon();

    @RegItem("fruit_yggdrasill_item")
    public static final Item FRUIT_YGGDRASILL = new ItemFruitWT();

    @RegItem(value = "nature_ingot", oreDict = "ingotNature", className = "ItemNatureIngot", isRegisterMultiTextureItem = true)
    public static final Item NATURE_INGOT = new ItemNatureIngot();

    @RegItem(value = "nature_powder", oreDict = "powderNature", className = "ItemNaturePowder", isRegisterMultiTextureItem = true)
    public static final Item NATURE_POWDER = new ItemNaturePowder();

    @RegItem(value = "nature_marrow", oreDict = "marrowNature", className = "ItemNaturePowder", isRegisterMultiTextureItem = true)
    public static final Item NATURE_MARROW = new ItemNaturePowder();

    @RegItem(value = "strange_ingot", oreDict = "ingotStrange", className = "ItemStrangeIngot", isRegisterMultiTextureItem = true)
    public static final Item STRANGE_INGOT = new ItemStrangeIngot();

    @RegItem("angle_sword_l1")
    public static final Item ANGLE_SWORD = new ItemAngleSwordL1();

    @RegItem("vlad_iii_sword")
    public static final Item VLAD_III = new ItemVladIIISword();

    @RegItem("red_packet")
    public static Item RED_PACKET = new ItemRedPacket();
}
