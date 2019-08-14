package com.github.ksgfk.oceanheartr.common.entity;

import com.github.ksgfk.oceanheartr.OceanHeartR;
import com.github.ksgfk.oceanheartr.annotation.EntityRegistry;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

/**
 * @author KSGFK create in 2019/8/13
 */
@EntityRegistry(
        nameID = OceanHeartR.MOD_ID + ".boom_ocean_soul_ore",
        numID = 233,
        eggPrimaryColor = 111111,
        eggSecondaryColor = 111111,
        canAutoSpawn = false
)
public class EntityBoomOceanSoulOre extends EntityLiving {
    public EntityBoomOceanSoulOre(World worldIn) {
        super(worldIn);
        setSize(1, 1);
    }
}
