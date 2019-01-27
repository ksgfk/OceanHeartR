package cn.com.breakdawn.mc.util;

import java.lang.annotation.*;

/**
 * 注册方块时调用
 * KSGFK 创建于 2019/1/27
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface RegisterBlock {
    /**
     * 方块meta值
     *
     * @return meta值
     */
    int meta() default 0;
}
