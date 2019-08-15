package com.github.ksgfk.oceanheartr.common.event.subscriber;

import com.github.ksgfk.oceanheartr.common.entity.EntityBoomOceanSoulOre;
import com.github.ksgfk.oceanheartr.common.event.ClickOHRBlockEvent;
import com.github.ksgfk.oceanheartr.common.init.OHRBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
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
public final class SubscribePlayerEvent {
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
        world.spawnEntity(new EntityBoomOceanSoulOre(world));
        heldItem.damageItem(1, player);
    }
}
