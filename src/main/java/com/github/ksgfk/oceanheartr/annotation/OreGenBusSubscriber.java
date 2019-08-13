package com.github.ksgfk.oceanheartr.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 订阅ORE_GEN_BUS事件
 *
 * @author KSGFK create in 2019/8/13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OreGenBusSubscriber {
}
