package cn.com.breakdawn.mc.client;

import cn.com.breakdawn.mc.common.CommonProxy;
import cn.com.breakdawn.mc.common.init.Items;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 客户端代理
 * KSGFK 创建于 2019/1/25
 */
public class ClientProxy extends CommonProxy {
    @SubscribeEvent
    public void registerItemModels(ModelRegistryEvent event) {
        registerItemModel(Items.FIRST_ITEM);
    }

    /**
     * 注册物品模型
     *
     * @param item 需要被注册的物品
     */
    private void registerItemModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    /**
     * 注册物品模型,需要有meta值时调用这个
     *
     * @param item     注册物品模型
     * @param metadata 物品的Meta值
     */
    private void registerItemModel(Item item, int metadata) {
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }
}
