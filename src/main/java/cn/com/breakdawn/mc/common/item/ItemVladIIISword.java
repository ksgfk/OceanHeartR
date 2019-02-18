package cn.com.breakdawn.mc.common.item;

import cn.com.breakdawn.mc.util.Util;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

/**
 * @author KSGFK create in 2019/2/18
 */
public class ItemVladIIISword extends ItemSwordBase {
    public static final Item.ToolMaterial VLADIII = EnumHelper.addToolMaterial("vlad_iii", 3, 1561, 8.0F, 4.0F, 10);

    public ItemVladIIISword() {
        super(VLADIII);
        this.attackDamage = 3.0F + VLADIII.getAttackDamage();
        this.attackSpeed = (float) -2.8;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        if (stack != null) {
            Util.addUnbreakable(stack);
        }
        return false;
    }
}
