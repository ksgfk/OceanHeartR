package com.github.ksgfk.oceanheartr.common.block;

import com.github.ksgfk.oceanheartr.OceanHeartR;
import com.github.ksgfk.oceanheartr.common.init.OHRCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * @author KSGFK create in 2019/8/12
 */
public class BlockOHRBase extends Block {
    public BlockOHRBase(String name, Material blockMaterialIn) {
        super(blockMaterialIn);
        setRegistryName(OceanHeartR.MOD_ID, name);
        setTranslationKey(OceanHeartR.MOD_ID + "." + name);
        setCreativeTab(OHRCreativeTab.Main);
    }
}
