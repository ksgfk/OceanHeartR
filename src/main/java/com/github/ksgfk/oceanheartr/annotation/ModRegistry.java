package com.github.ksgfk.oceanheartr.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 拥有该注解的类下的字段会被自动注册，当字段的类型是下列类型时才会被获取并注册
 * {@link net.minecraft.item.Item}
 * {@link net.minecraft.block.Block}
 *
 * @author KSGFK create in 2019/8/12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModRegistry {
}
