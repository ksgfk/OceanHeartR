package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.common.item.ItemFirst;
import cn.com.breakdawn.mc.common.item.ItemNatureSword;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

/**
 * 初始化所有物品的类
 * 非本地话名称等于物品名
 * KSGFK 创建于 2019/1/25
 */
public final class OHRItems {
    /*工具材料,用来设置工具的基本属性,addToolMaterial()的参数依次是:工具材料名称,硬度,最大使用次数,挖掘效率,伤害(真实伤害-4才是这里要填的),附魔因素*/
    public static final Item.ToolMaterial NATURE = EnumHelper.addToolMaterial("nature", 6, 3122, 8, 4, 10);

    public static final Item FIRST = new ItemFirst("first_item");
    public static final Item NATURE_SWORD = new ItemNatureSword(NATURE, "nature_sword");
}
