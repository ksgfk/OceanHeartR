package cn.com.breakdawn.mc.common;

import cn.com.breakdawn.mc.common.init.Items;
import net.minecraft.item.Item;
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
     * @param event 事件形参
     */
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(Items.FIRST_ITEM);//以后应该会用for来注册吧
    }
}
