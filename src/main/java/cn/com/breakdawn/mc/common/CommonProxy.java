package cn.com.breakdawn.mc.common;

import cn.com.breakdawn.mc.common.init.Blocks;
import cn.com.breakdawn.mc.common.init.Items;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 通用代理
 * KSGFK 创建于 2019/1/25
 */
public class CommonProxy {
    /**
     * 注册物品
     *
     * @param event 事件
     */
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        /*--------注册真·物品--------*/
        event.getRegistry().register(Items.FIRST);//以后应该会用for来注册吧
        event.getRegistry().register(Items.NATURE_SWORD);
        /*--------注册能拿在手上的方块--------*/
        registerItemBlock(Blocks.FIRST_BLOCK, event);
        registerItemBlock(Blocks.NATURE_ORE, event);
    }

    /**
     * 注册方块,注意方块还需要在物品里注册一次,因为能拿在手上的方块属于Item
     *
     * @param event 事件
     */
    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(Blocks.FIRST_BLOCK);
        event.getRegistry().register(Blocks.NATURE_ORE);
    }

    private void registerItemBlock(Block block, RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }
}
