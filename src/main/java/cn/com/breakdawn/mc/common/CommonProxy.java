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
package cn.com.breakdawn.mc.common;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.block.BlockNatureOre;
import cn.com.breakdawn.mc.common.init.OHRBlocks;
import cn.com.breakdawn.mc.common.init.OHRItems;
import cn.com.breakdawn.mc.util.NameBuilder;
import cn.com.breakdawn.mc.util.RegBlock;
import cn.com.breakdawn.mc.util.RegItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 通用代理
 * KSGFK 创建于 2019/1/25
 */
public class CommonProxy {
    /**
     * 注册方块
     *
     * @param event 事件
     */
    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Field field : OHRBlocks.class.getFields()) {
            field.setAccessible(true);
            RegBlock anno = field.getAnnotation(RegBlock.class);
            if (anno == null) continue;

            try {
                Block block = (Block) field.get(null);
                //event.getRegistry().register(block.setRegistryName(NameBuilder.buildRegistryName(anno.value())).setUnlocalizedName(NameBuilder.buildUnlocalizedName(anno.value())));
                //event.getRegistry().register(block.setUnlocalizedName(NameBuilder.buildUnlocalizedName(anno.value())));
                if (anno.isRegisterItemBlock()) {
                    event.getRegistry().register(block.setRegistryName(NameBuilder.buildRegistryName(anno.value())).setUnlocalizedName(NameBuilder.buildUnlocalizedName(anno.value())));
                } else if (anno.isRegisterMultiTextureBlock()) {
                    event.getRegistry().register(block.setUnlocalizedName(NameBuilder.buildUnlocalizedName(anno.value())));
                }

                //Register item block.
                /*
                if (anno.isRegisterItemBlock()) {
                    Class<? extends Item> itemClass = anno.itemClass();
                    Constructor<? extends Item> con = itemClass.getDeclaredConstructor(Block.class);
                    con.setAccessible(true);
                    event.getRegistry().register(con.newInstance(block).setRegistryName(block.getRegistryName()).setUnlocalizedName(block.getUnlocalizedName()));
                }
                */
                //注册矿物词典
                //Arrays.asList(anno.oreDict()).forEach(s -> OreDictionary.registerOre(s, block));
            } catch (Exception e) {
                OceanHeartR.getLogger().warn("Un-able to register block " + field.toGenericString(), e);
            }
        }
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        for (Field field : OHRItems.class.getFields()) {
            field.setAccessible(true);
            RegItem anno = field.getAnnotation(RegItem.class);
            if (anno==null) continue;

            try {
                Item item = (Item) field.get(null);
                event.getRegistry().register(item.setRegistryName(NameBuilder.buildRegistryName(anno.value())).setUnlocalizedName(NameBuilder.buildUnlocalizedName(anno.value())));

                //Arrays.asList(anno.oreDict()).forEach(s -> OreDictionary.registerOre(s, item));
            } catch (Exception e) {
                OceanHeartR.getLogger().warn("Un-able to register item " + field.toGenericString(), e);
            }
        }

        for (Field field : OHRBlocks.class.getFields()) {
            field.setAccessible(true);
            RegBlock anno = field.getAnnotation(RegBlock.class);
            if (anno == null) continue;
            try {
                Block block = (Block) field.get(null);
                //event.getRegistry().register(block.setRegistryName(NameBuilder.buildRegistryName(anno.value())).setUnlocalizedName(NameBuilder.buildUnlocalizedName(anno.value())));

                //Register item block.
                if (anno.isRegisterItemBlock()) {
                    Class<? extends Item> itemClass = anno.itemClass();
                    Constructor<? extends Item> con = itemClass.getDeclaredConstructor(Block.class);
                    con.setAccessible(true);
                    event.getRegistry().register(con.newInstance(block).setRegistryName(block.getRegistryName()).setUnlocalizedName(block.getUnlocalizedName()));
                    //event.getRegistry().register(con.newInstance(block).setUnlocalizedName(block.getUnlocalizedName()));
                    //block.setRegistryName(block.getRegistryName()).setUnlocalizedName(NameBuilder.buildUnlocalizedName(anno.value()));
                    //registerItemBlock(block, event);
                } else if (anno.isRegisterMultiTextureBlock()) {
                    block.setUnlocalizedName(NameBuilder.buildUnlocalizedName(anno.value()));
                    registerMultiTextureBlock(block, event);
                }

                //Arrays.asList(anno.oreDict()).forEach(s -> OreDictionary.registerOre(s, block));
            } catch (Exception e) {
                OceanHeartR.getLogger().warn("Un-able to register block " + field.toGenericString(), e);
            }
        }
    }

    // 注册一个方块的物品形式
    public void registerItemBlock(Block block, RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    // 注册一个多Metadata方块的物品形式
    public void registerMultiTextureBlock(Block block, RegistryEvent.Register<Item> event) {
        ItemMultiTexture itemBlock = new ItemMultiTexture(block, block, var1 -> BlockNatureOre.EnumType.values()[var1.getMetadata()].getName());
        event.getRegistry().register(itemBlock.setRegistryName(block.getRegistryName()));
    }
}
