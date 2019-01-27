package cn.com.breakdawn.mc.util;

import java.lang.annotation.*;

/**
 * 注册物品时调用
 * KSGFK 创建于 2019/1/27
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RegisterItem {
    String oreDict() default "";
}
