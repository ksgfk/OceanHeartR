package cn.com.breakdawn.mc.common.item;

import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public class ItemAngleSwordL1 extends ItemTool {
    public static final Item.ToolMaterial ANGLE_L1 = EnumHelper.addToolMaterial("angle_l1", 3, 1561, 8.0F, 4.0F, 10);
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE);

    public ItemAngleSwordL1() {
        super(ANGLE_L1, EFFECTIVE_ON);
        this.maxStackSize = 1;
        this.setMaxDamage(ANGLE_L1.getMaxUses());
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
        this.attackDamage = 3.0F + ANGLE_L1.getAttackDamage();
        this.attackSpeed = -1;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            tooltip.add(I18n.format("tooltip.more"));
            tooltip.add(I18n.format("tooltip.canUp"));
            tooltip.add("");
        }else {
            tooltip.add(I18n.format("tooltip.angle_sword_l1.normal"));
            tooltip.add("");
            tooltip.add(I18n.format("tooltip.shift"));
        }
    }
}
