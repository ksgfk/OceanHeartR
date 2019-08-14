package com.github.ksgfk.oceanheartr.annotation;

import net.minecraft.entity.EnumCreatureType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author KSGFK create in 2019/8/13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EntityRegistry {
    String nameID();

    int numID();

    int updateRange() default 64;

    int updateFrequency() default 3;

    boolean isSendVelocityUpdates() default true;

    int eggPrimaryColor() default -1;

    int eggSecondaryColor() default -1;

    boolean canAutoSpawn();

    EnumCreatureType CreatureType() default EnumCreatureType.AMBIENT;

    int weight() default -1;

    int min() default -1;

    int max() default -1;

    String[] biomes() default "";
}
