package com.github.ksgfk.oceanheartr.common.init;

import com.github.ksgfk.oceanheartr.annotation.ModRegistry;
import com.github.ksgfk.oceanheartr.annotation.OreDict;
import com.github.ksgfk.oceanheartr.common.item.ItemOHRBase;
import net.minecraft.item.Item;

/**
 * @author KSGFK create in 2019/8/12
 */
@ModRegistry
public final class OHRItems {
    @OreDict(name = "ingotOceanSoul")
    public static final Item OceanSoul = new ItemOHRBase("ocean_soul_ingot");
}
