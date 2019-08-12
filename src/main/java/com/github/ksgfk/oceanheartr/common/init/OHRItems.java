package com.github.ksgfk.oceanheartr.common.init;

import com.github.ksgfk.oceanheartr.annotation.ItemRegister;
import com.github.ksgfk.oceanheartr.annotation.ModRegistry;
import com.github.ksgfk.oceanheartr.common.item.ItemOHRBase;
import net.minecraft.item.Item;

/**
 * @author KSGFK create in 2019/8/12
 */
@ModRegistry
public final class OHRItems {
    @ItemRegister
    public static final Item OceanSoul = new ItemOHRBase("ocean_soul");
}
