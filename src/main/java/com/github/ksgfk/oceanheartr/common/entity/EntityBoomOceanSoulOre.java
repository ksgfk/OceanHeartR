package com.github.ksgfk.oceanheartr.common.entity;

import com.github.ksgfk.oceanheartr.OceanHeartR;
import com.github.ksgfk.oceanheartr.annotation.EntityRegistry;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
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
    private EntityLivingBase placedBy;

    public EntityBoomOceanSoulOre(World worldIn) {
        super(worldIn);
        setSize(1F, 1F);
    }

    public EntityBoomOceanSoulOre(World worldIn, BlockPos pos, EntityLivingBase placedBy) {
        this(worldIn);
        this.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        this.placedBy = placedBy;
    }

    public EntityBoomOceanSoulOre(World worldIn, double x, double y, double z, EntityLivingBase placedBy) {
        this(worldIn);
        this.setPosition(x + 0.5, y, z + 0.5);
        this.placedBy = placedBy;
    }

    public EntityLivingBase getPlacedBy() {
        return placedBy;
    }
}
