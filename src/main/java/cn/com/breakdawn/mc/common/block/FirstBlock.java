package cn.com.breakdawn.mc.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * 第一个方块
 * KSGFK 创建于 2019/1/26
 */
public class FirstBlock extends Block {
    /**
     * @param blockMaterialIn 方块的材质
     */
    public FirstBlock(Material blockMaterialIn) {
        super(blockMaterialIn);
        this.setHardness(100);
        this.setHarvestLevel("pickaxe", 3);
    }
}
