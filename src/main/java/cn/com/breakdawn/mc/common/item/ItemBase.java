package cn.com.breakdawn.mc.common.item;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * 基本物品
 * KSGFK 创建于 2019/1/25
 */
public class ItemBase extends Item {
    public ItemBase() {
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
    }
}
