package cn.com.breakdawn.mc.common;

import cn.com.breakdawn.mc.util.RegisterBlock;
import cn.com.breakdawn.mc.util.RegisterItem;
import cn.com.breakdawn.mc.common.init.OHRBlocks;
import cn.com.breakdawn.mc.common.init.OHRItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Field;
import java.util.Objects;

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
        Class<OHRItems> cls = OHRItems.class;//获取注册物品的类
        Field[] fields = cls.getFields();//获取字段
        for (Field f : fields) {
            if (f.isAnnotationPresent(RegisterItem.class)) {//如果字段有注解
                try {
                    event.getRegistry().registerAll((Item) f.get(f.getName()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        /*--------注册能拿在手上的方块--------*/
        Class<OHRBlocks> cls1 = OHRBlocks.class;
        Field[] fields1 = cls1.getFields();
        for (Field f : fields1) {
            if (f.isAnnotationPresent(RegisterBlock.class)) {
                try {
                    Block block = (Block) f.get(f.getName());
                    event.getRegistry().register(new ItemBlock(block).setRegistryName(Objects.requireNonNull(block.getRegistryName())));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 注册方块
     *
     * @param event 事件
     */
    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        Class<OHRBlocks> cls = OHRBlocks.class;
        Field[] fields = cls.getFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(RegisterBlock.class)) {
                try {
                    event.getRegistry().registerAll((Block) f.get(f.getName()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Deprecated
    private void registerItemBlock(Block block, RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }
}
