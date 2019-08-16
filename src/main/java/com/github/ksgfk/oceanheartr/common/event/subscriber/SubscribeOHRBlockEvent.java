package com.github.ksgfk.oceanheartr.common.event.subscriber;

import com.github.ksgfk.oceanheartr.common.entity.EntityBoomOceanSoulOre;
import com.github.ksgfk.oceanheartr.common.event.ClickOHRBlockEvent;
import com.github.ksgfk.oceanheartr.common.event.ExplosionDestroyOHRBlockEvent;
import com.github.ksgfk.oceanheartr.common.event.OHRBlockActiveEvent;
import com.github.ksgfk.oceanheartr.common.init.OHRBlocks;
import com.github.ksgfk.oceanheartr.common.init.OHRItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author KSGFK create in 2019/8/15
 */
@Mod.EventBusSubscriber
public final class SubscribeOHRBlockEvent {
    @SubscribeEvent
    public static void onPlayerClickOceanSoulOre(ClickOHRBlockEvent event) {
        World world = event.getWorld();
        if (world.isRemote) {
            return;
        }
        IBlockState blockState = event.getState();
        if (blockState.getBlock() != OHRBlocks.OceanSoulOre) {
            return;
        }
        BlockPos pos = event.getPos();
        EntityPlayer player = event.getClicker();
        ItemStack heldItem = player.getHeldItem(EnumHand.MAIN_HAND);
        world.setBlockToAir(pos);
        heldItem.damageItem(1, player);
        EntityBoomOceanSoulOre ore = new EntityBoomOceanSoulOre(world, pos, player);
        world.spawnEntity(ore);
    }

    @SubscribeEvent
    public static void onPlayerActionOceanSoulOre(OHRBlockActiveEvent event) {
        World world = event.getWorld();
        IBlockState state = event.getState();
        EntityPlayer playerIn = event.getPlayer();
        BlockPos pos = event.getPos();
        if (!world.isRemote) {
            if (state.getBlock() == OHRBlocks.OceanSoulOre) {
                ItemStack heldItem = playerIn.getHeldItem(EnumHand.MAIN_HAND);
                if (heldItem.getItem() == OHRItems.FrigidDrillingBitRtx3080) {
                    world.setBlockToAir(pos);
                    InventoryHelper.spawnItemStack(world,
                            pos.getX(),
                            pos.getY(),
                            pos.getZ(),
                            new ItemStack(OHRBlocks.OceanSoulOre));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onExplosionDestroyOceanSoulOre(ExplosionDestroyOHRBlockEvent event) {
        if (event.getWorld().isRemote) {
            return;
        }
        if (event.getState().getBlock() != OHRBlocks.OceanSoulOre) {
            return;
        }
        EntityBoomOceanSoulOre ore = new EntityBoomOceanSoulOre(event.getWorld(),
                event.getPos(),
                event.getExplosion().getExplosivePlacedBy());
        ore.setCoolDown((event.getWorld().rand.nextInt(ore.getCooldown() / 2) + ore.getCooldown() / 4));
        event.getWorld().spawnEntity(ore);
    }
}
