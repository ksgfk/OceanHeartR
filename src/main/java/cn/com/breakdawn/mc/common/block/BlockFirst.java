package cn.com.breakdawn.mc.common.block;

import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * 第一个方块
 * KSGFK 创建于 2019/1/26
 */
public class BlockFirst extends Block {
    /**
     * @param blockMaterialIn 方块的材质
     */
    public BlockFirst(Material blockMaterialIn, String name) {
        super(blockMaterialIn);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);

        this.setHardness(100);
        this.setHarvestLevel("pickaxe", 3);
    }
}
