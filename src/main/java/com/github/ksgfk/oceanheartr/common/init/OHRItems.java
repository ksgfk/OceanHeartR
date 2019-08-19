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
    public static final Item OceanSoulIngot = new ItemOHRBase("ocean_soul_ingot");
    @OreDict(name = "ingotLavaSoul")
    public static final Item LavaSoulIngot = new ItemOHRBase("lava_soul_ingot");
    @OreDict(name = "ingotLegend")
    public static final Item LegendIngot = new ItemOHRBase("legend_ingot");
    @OreDict(name = "ingotLegendGold")
    public static final Item LegendGoldIngot = new ItemOHRBase("legend_gold_ingot");
    @OreDict(name = "powerLegend")
    public static final Item LegendPower = new ItemOHRBase("power_legend");
    @OreDict(name = "ingotVoid")
    public static final Item VoidIngot = new ItemOHRBase("void_ingot");
    @OreDict(name = "crystalNature")
    public static final Item NatureIngot = new ItemOHRBase("nature_crystal");
    @OreDict(name = "crystalNaturePolluted")
    public static final Item NatureIngotPolluted = new ItemOHRBase("nature_crystal_polluted");
    public static final Item FrigidDrillingBitRtx3080 = new ItemOHRBase("frigid_drilling_bit_rtx3080");
}
