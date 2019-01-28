package cn.com.breakdawn.mc.common.block;

import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * 第一个方块
 * KSGFK 创建于 2019/1/26
 */
public class BlockFirst extends Block {
    /**
     * @param blockMaterialIn 方块的材质
     */
    public BlockFirst(Material blockMaterialIn) {
        super(blockMaterialIn);
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);

        this.setHardness(100);
        this.setHarvestLevel("pickaxe", 3);
    }

    public BlockFirst setHarvestLevelReturnBlock(String toolClass, int level) {
        super.setHarvestLevel(toolClass, level);
        return this;
    }

    @Override
    public BlockFirst setSoundType(SoundType sound) {
        super.setSoundType(sound);
        return this;
    }

    public float getResistance(){
        return blockResistance / 3.0F;
    }

    public float getHardness(){
        return blockHardness / 5.0F;
    }
}
