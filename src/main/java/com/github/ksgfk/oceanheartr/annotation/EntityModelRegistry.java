package com.github.ksgfk.oceanheartr.annotation;

import net.minecraft.entity.Entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author KSGFK create in 2019/8/14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EntityModelRegistry {
    Class<? extends Entity> entityClass();
}
