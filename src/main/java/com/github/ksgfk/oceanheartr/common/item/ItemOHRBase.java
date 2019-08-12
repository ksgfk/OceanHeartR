package com.github.ksgfk.oceanheartr.common.item;

import com.github.ksgfk.oceanheartr.OceanHeartR;
import com.github.ksgfk.oceanheartr.common.init.OHRCreativeTab;
import net.minecraft.item.Item;

/**
 * @author KSGFK create in 2019/8/12
 */
public class ItemOHRBase extends Item {
    public ItemOHRBase(String name) {
        setRegistryName(OceanHeartR.MOD_ID, name);
        setTranslationKey(OceanHeartR.MOD_ID + "." + name);
        setCreativeTab(OHRCreativeTab.Main);
    }
}
