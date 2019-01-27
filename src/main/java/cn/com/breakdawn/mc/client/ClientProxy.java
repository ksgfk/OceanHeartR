package cn.com.breakdawn.mc.client;

import cn.com.breakdawn.mc.common.CommonProxy;
import cn.com.breakdawn.mc.common.init.OHRBlocks;
import cn.com.breakdawn.mc.common.init.OHRItems;
import net.minecraft.block.Block;
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
        registerItemModel(OHRItems.FIRST);
        registerItemModel(OHRItems.NATURE_SWORD);
    }

    @SubscribeEvent
    public void registerBlockModels(ModelRegistryEvent event) {
        registerBlockModel(OHRBlocks.FIRST_BLOCK);
        registerBlockModel(OHRBlocks.NATURE_ORE);
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

    /**
     * 注册方块模型
     *
     * @param block 方块
     */
    private void registerBlockModel(Block block) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }

    /**
     * 注册方块模型
     *
     * @param block 方块
     */
    private void registerBlockModel(Block block, int metadata) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), metadata, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}
