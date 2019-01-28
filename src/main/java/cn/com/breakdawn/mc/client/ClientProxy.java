/*
  本文件部分源代码从ChinaCraft2仓库复制,开源地址:https://github.com/UnknownStudio/ChinaCraft2
  ChinaCraft2 Copyright (C) 2017 Unknown Domain
  This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
  This is free software, and you are welcome to redistribute it
  nder certain conditions; type `show c' for details.

  The hypothetical commands `show w' and `show c' should show the appropriate
  parts of the General Public License.  Of course, your program's commands
  might be different; for a GUI interface, you would use an "about box".

  You should also get your employer (if you work as a programmer) or school,
  if any, to sign a "copyright disclaimer" for the program, if necessary.
  For more information on this, and how to apply and follow the GNU GPL, see
  <https://www.gnu.org/licenses/>.

  The GNU General Public License does not permit incorporating your program
  into proprietary programs.  If your program is a subroutine library, you
  may consider it more useful to permit linking proprietary applications with
  the library.  If this is what you want to do, use the GNU Lesser General
  Public License instead of this License.  But first, please read
  <https://www.gnu.org/licenses/why-not-lgpl.html>.
 */
package cn.com.breakdawn.mc.client;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.CommonProxy;
import cn.com.breakdawn.mc.common.init.OHRBlocks;
import cn.com.breakdawn.mc.common.init.OHRItems;
import cn.com.breakdawn.mc.util.RegBlock;
import cn.com.breakdawn.mc.util.RegItem;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 客户端代理
 * KSGFK 创建于 2019/1/25
 */
public class ClientProxy extends CommonProxy {
    @SubscribeEvent
    public void registerItemModels(ModelRegistryEvent event) {
        for (Field field : OHRItems.class.getFields()) {
            field.setAccessible(true);
            RegItem anno = field.getAnnotation(RegItem.class);
            if (anno == null) continue;

            if (!anno.isRegisterRender()) continue;

            try {
                Item item = (Item) field.get(null);
                registerRender(item, 0);
            } catch (Exception e) {
                OceanHeartR.getLogger().warn("Un-able to register item " + field.toGenericString(), e);
            }
        }
    }

    @SubscribeEvent
    public void registerBlockModels(ModelRegistryEvent event) {
        //int cycle = 0;
        for (Field field : OHRBlocks.class.getFields()) {
            field.setAccessible(true);
            RegBlock anno = field.getAnnotation(RegBlock.class);
            if (anno == null) continue;

            if (!anno.isRegisterRender()) continue;

            try {
                Block block = (Block) field.get(null);

                if (anno.isRegisterItemBlock()) {
                    registerRender(block, 0);
                    //cycle++;
                } else if (anno.isRegisterMultiTextureBlock()) {
                    //registerRender(block, 0);
                    //block.getMetaFromState(block.getDefaultState().withProperty(BlockNatureOre.VARIANT, BlockNatureOre.EnumType.byMetadata()));

                    Class c = Class.forName("cn.com.breakdawn.mc.common.block." + anno.className());
                    Method method = c.getDeclaredMethod("renderModel", Block.class);

                    method.invoke(block, block);

                    //block.getMetaFromState(Block.getStateById(block))
                    //OceanHeartR.getLogger().info(block.getUnlocalizedName());
                    //cycle++;
                }
            } catch (Exception e) {
                OceanHeartR.getLogger().warn("Un-able to register block " + field.toGenericString(), e);
            }
        }
        //OceanHeartR.getLogger().info(cycle);
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender(Block block, int meta) {
        Item item = Item.getItemFromBlock(block);
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }

    @SideOnly(Side.CLIENT)
    private void registerRender(Item item, int meta) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    /**
     * 注册物品模型
     *
     * @param item 需要被注册的物品
     */
    @Deprecated
    private void registerItemModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    /**
     * 注册物品模型,需要有meta值时调用这个
     *
     * @param item     注册物品模型
     * @param metadata 物品的Meta值
     */
    @Deprecated
    private void registerItemModel(Item item, int metadata) {
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    /**
     * 注册方块模型
     *
     * @param block 方块
     */
    @Deprecated
    private void registerBlockModel(Block block) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }

    /**
     * 注册方块模型
     *
     * @param block 方块
     */
    @Deprecated
    private void registerBlockModel(Block block, int metadata) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), metadata, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}
