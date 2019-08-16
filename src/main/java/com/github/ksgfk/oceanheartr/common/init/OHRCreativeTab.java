package com.github.ksgfk.oceanheartr.common.init;

import com.github.ksgfk.oceanheartr.OceanHeartR;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

/**
 * @author KSGFK create in 2019/8/12
 */
public final class OHRCreativeTab {
    public static final CreativeTabs Main = new CreativeTabs(OceanHeartR.MOD_ID + ".name") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(OHRBlocks.OceanSoulBlock);
        }
    };
}
