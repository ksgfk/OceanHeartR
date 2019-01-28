/*
  本源代码从ChinaCraft2仓库复制,开源地址:https://github.com/UnknownStudio/ChinaCraft2
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
package cn.com.breakdawn.mc.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注册方块的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RegBlock {
    /**
     * 该参数将自动设置方块的registryName和unlocalizedName
     * The params to build registryName and unlocalizedName.
     *
     * @see cn.mccraft.chinacraft.util.NameBuilder
     */
    String[] value();

    /**
     * 添加矿物词典
     * All {@link net.minecraftforge.oredict.OreDictionary} values to be registered.
     */
    String[] oreDict() default {};

    /**
     * 设置方块的ItemBlock类
     */
    Class<? extends Item> itemClass() default ItemBlock.class;

    /**
     * 是否自动注册ItemBlock
     */
    boolean isRegisterItemBlock() default true;

    /**
     * 是否自动注册渲染器
     */
    boolean isRegisterRender() default true;

    /**
     * 是否自动注册多Metadata方块的多贴图,不可与isRegisterItemBlock同时开启!
     */
    boolean isRegisterMultiTextureBlock() default false;

    /**
     * 注册该方块的Block子类的名字(多Metadata时使用)
     */
    String className() default "";
}
