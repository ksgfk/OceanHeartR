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
import cn.com.breakdawn.mc.common.init.OHRBlocks;
import cn.com.breakdawn.mc.common.init.OHRItems;
import cn.com.breakdawn.mc.util.NameBuilder;
import cn.com.breakdawn.mc.util.RegBlock;
import cn.com.breakdawn.mc.util.RegItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 通用代理
 * KSGFK 创建于 2019/1/25
 */
public class CommonProxy {
    /**
     * 注册方块
     */
    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        for (Field field : OHRBlocks.class.getFields()) {
            field.setAccessible(true);
            RegBlock anno = field.getAnnotation(RegBlock.class);
            if (anno == null) continue;

            try {
                Block block = (Block) field.get(null);
                event.getRegistry().register(block.setRegistryName(NameBuilder.buildRegistryName(anno.value())).setUnlocalizedName(NameBuilder.buildUnlocalizedName(anno.value())));
            } catch (Exception e) {
                OceanHeartR.getLogger().warn("Un-able to register block " + field.toGenericString(), e);
            }
        }
    }

    /**
     * 注册物品
     */
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        for (Field field : OHRItems.class.getFields()) {
            field.setAccessible(true);
            RegItem anno = field.getAnnotation(RegItem.class);
            if (anno == null) continue;

            try {
                Item item = (Item) field.get(null);
                event.getRegistry().register(item.setRegistryName(NameBuilder.buildRegistryName(anno.value())).setUnlocalizedName(NameBuilder.buildUnlocalizedName(anno.value())));
                if (anno.oreDict().length != 0) {
                    Arrays.asList(anno.oreDict()).forEach(s -> OreDictionary.registerOre(s, item));
                }
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

                //Register item block.
                if (anno.isRegisterItemBlock()) {
                    Class<? extends Item> itemClass = anno.itemClass();
                    Constructor<? extends Item> con = itemClass.getDeclaredConstructor(Block.class);
                    con.setAccessible(true);
                    Item i = con.newInstance(block).setRegistryName(block.getRegistryName()).setUnlocalizedName(block.getUnlocalizedName());
                    event.getRegistry().register(i);
                    if (!(anno.oreDict().length == 0)) {
                        Arrays.asList(anno.oreDict()).forEach(s -> OreDictionary.registerOre(s, i));
                    }
                } else if (anno.isRegisterMultiTextureBlock()) {
                    block.setUnlocalizedName(NameBuilder.buildUnlocalizedName(anno.value()));
                    ItemMultiTexture i = registerMultiTextureBlock(block, event, block.getClass(), anno);

                    if (!(anno.oreDict().length == 0)) {
                        Arrays.asList(anno.oreDict()).forEach(s -> OreDictionary.registerOre(s, i));
                    }
                }
            } catch (Exception e) {
                OceanHeartR.getLogger().warn("Un-able to register item block " + field.toGenericString(), e);
            }
        }
    }

    /**
     * 注册多Metadata的ItemBlock
     *
     * @param block 要注册的block
     * @param event 事件
     * @param t     要注册的block的类
     * @param anno  注册Block类的注解
     */
    private <T extends Block> ItemMultiTexture registerMultiTextureBlock(Block block, RegistryEvent.Register<Item> event, Class<T> t, RegBlock anno) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        IStringSerializable[] blockMeta;
        if (anno.enumPath().isEmpty()) {
            blockMeta = getBlockMeta(t, anno);
        } else {
            Class c = Class.forName(anno.enumPath());
            blockMeta = getBlockMeta(c, anno);
        }
        IStringSerializable[] finalBlockMeta = blockMeta;
        ItemMultiTexture itemBlock = new ItemMultiTexture(block, block, var1 -> finalBlockMeta[var1.getMetadata()].getName());
        event.getRegistry().register(itemBlock.setRegistryName(block.getRegistryName()));
        return itemBlock;
    }

    /**
     * 从定义方块的枚举中获取meta值
     *
     * @param c    需要获取meta值的方块
     * @param <T>  meta类
     * @param anno 注册Block类的注解
     * @return 枚举实现的IStringSerializable接口
     * @throws NoSuchMethodException     无法找到枚举
     * @throws InvocationTargetException 无法执行方法
     * @throws IllegalAccessException    非法访问
     */
    private <T extends Block> IStringSerializable[] getBlockMeta(Class<T> c, RegBlock anno) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (anno.enumPath().isEmpty()) {
            for (Class in : c.getDeclaredClasses()) {
                if (in.isEnum()) {
                    Method m = in.getMethod("values");
                    return (IStringSerializable[]) m.invoke(null);
                }
            }
            throw new NoClassDefFoundError("Class " + c.getName() + " haven't internal class");
        } else {
            Method m = c.getMethod("values");
            return (IStringSerializable[]) m.invoke(null);
        }
    }
}
