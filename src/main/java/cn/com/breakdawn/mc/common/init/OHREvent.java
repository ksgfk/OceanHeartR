package cn.com.breakdawn.mc.common.init;

import cn.com.breakdawn.mc.common.enchantment.EnchSoulBond;
import cn.com.breakdawn.mc.util.Util;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ListIterator;

public class OHREvent {
    @SubscribeEvent
    public static void canPutInAnvil(AnvilUpdateEvent event) {
        if (event.getLeft().getItem().equals(OHRItems.VLAD_III)) {
            event.setCanceled(true);
        }
    }

    //感谢CoFH的开源代码
    @SubscribeEvent
    public void handlePlayerCloneEvent(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) {
            return;
        }
        EntityPlayer player = event.getEntityPlayer();
        EntityPlayer oldPlayer = event.getOriginal();

        if (player instanceof FakePlayer) {
            return;
        }
        if (player.world.getGameRules().getBoolean("keepInventory")) {
            return;
        }
        for (int i = 0; i < oldPlayer.inventory.armorInventory.size(); i++) {
            ItemStack stack = oldPlayer.inventory.armorInventory.get(i);
            int encSoulbound = EnchantmentHelper.getEnchantmentLevel(OHREnch.SOUL_BOND, stack);
            if (encSoulbound > 0) {
                if (EnchSoulBond.permanent) {
                    if (encSoulbound > 1) {
                        Util.removeEnchantment(stack, OHREnch.SOUL_BOND);
                        Util.addEnchantment(stack, OHREnch.SOUL_BOND, 1);
                    }
                } else if (Util.RANDOM.nextInt(1 + encSoulbound) == 0) {
                    Util.removeEnchantment(stack, OHREnch.SOUL_BOND);
                    if (encSoulbound > 1) {
                        Util.addEnchantment(stack, OHREnch.SOUL_BOND, encSoulbound - 1);
                    }
                }
                if (Util.addToPlayerInventory(player, stack)) {
                    oldPlayer.inventory.armorInventory.set(i, ItemStack.EMPTY);
                }
            }
        }
        for (int i = 0; i < oldPlayer.inventory.mainInventory.size(); i++) {
            ItemStack stack = oldPlayer.inventory.mainInventory.get(i);
            int encSoulbound = Util.clamp(EnchantmentHelper.getEnchantmentLevel(OHREnch.SOUL_BOND, stack), 0, OHREnch.SOUL_BOND.getMaxLevel() * 2);
            if (encSoulbound > 0) {
                if (EnchSoulBond.permanent) {
                    if (encSoulbound > 1) {
                        Util.removeEnchantment(stack, OHREnch.SOUL_BOND);
                        Util.addEnchantment(stack, OHREnch.SOUL_BOND, 1);
                    }
                } else if (Util.RANDOM.nextInt(1 + encSoulbound) == 0) {
                    Util.removeEnchantment(stack, OHREnch.SOUL_BOND);
                    if (encSoulbound > 1) {
                        Util.addEnchantment(stack, OHREnch.SOUL_BOND, encSoulbound - 1);
                    }
                }
                if (Util.addToPlayerInventory(player, stack)) {
                    oldPlayer.inventory.mainInventory.set(i, ItemStack.EMPTY);
                }
            }
        }
    }

    //感谢CoFH的开源代码
    @SubscribeEvent
    public void handlePlayerDropsEvent(PlayerDropsEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        if (player instanceof FakePlayer) {
            return;
        }
        if (player.world.getGameRules().getBoolean("keepInventory")) {
            return;
        }
        ListIterator<EntityItem> iter = event.getDrops().listIterator();
        while (iter.hasNext()) {
            EntityItem drop = iter.next();
            ItemStack stack = drop.getItem();
            if (Util.isSoulbound(stack)) {
                if (Util.addToPlayerInventory(player, stack)) {
                    iter.remove();
                }
            }
        }
    }
}
