package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.common.item.*;
import cn.com.breakdawn.mc.util.RegItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

/**
 * 初始化所有物品的类
 * 非本地化名称等于物品名
 * KSGFK 创建于 2019/1/25
 */
public final class OHRItems {
    /*工具材料,用来设置工具的基本属性,addToolMaterial()的参数依次是:工具材料名称,硬度,最大使用次数,挖掘效率,伤害(真实伤害-4才是这里要填的),附魔因素*/
    public static final Item.ToolMaterial NATURE = EnumHelper.addToolMaterial("nature", 6, 3122, 8, 4, 10);

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

    @RegItem("nature_sword_polluted")
    public static final Item NATURE_SWORD_P = new ItemNatureSwordP(NATURE);

    @RegItem(value = "icon", className = "ItemIcon", isRegisterMultiTextureItem = true)
    public static final Item ICON = new ItemIcon();

    @RegItem("fruit_yggdrasill_item")
    public static final Item FRUIT_YGGDRASILL = new ItemFruitWT();

    @RegItem(value = "nature_ingot", oreDict = "ingotNature", className = "ItemNatureIngot", isRegisterMultiTextureItem = true)
    public static final Item NATURE_INGOT = new ItemNatureIngot();

    @RegItem(value = "strange_ingot", oreDict = "ingotStrange", className = "ItemStrangeIngot", isRegisterMultiTextureItem = true)
    public static final Item STRANGE_INGOT = new ItemStrangeIngot();
}
