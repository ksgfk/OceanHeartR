package com.github.ksgfk.oceanheartr.common.entity;

import com.github.ksgfk.oceanheartr.OceanHeartR;
import com.github.ksgfk.oceanheartr.annotation.EntityRegistry;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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
    private static final DataParameter<Integer> CoolDown = EntityDataManager.createKey(EntityBoomOceanSoulOre.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> CanExplode = EntityDataManager.createKey(EntityBoomOceanSoulOre.class, DataSerializers.BOOLEAN);

    private int coolDown;
    private boolean canExplode;
    private EntityLivingBase placedBy;

    public EntityBoomOceanSoulOre(World worldIn) {
        super(worldIn);
        preventEntitySpawning = true;
        isImmuneToFire = true;
        coolDown = 40;
        canExplode = false;
        setSize(1F, 1F);
    }

    public EntityBoomOceanSoulOre(World worldIn, @Nonnull BlockPos pos, @Nullable EntityLivingBase placedBy) {
        this(worldIn);
        setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        this.placedBy = placedBy;
    }

    public EntityBoomOceanSoulOre(World worldIn, double x, double y, double z, @Nullable EntityLivingBase placedBy) {
        this(worldIn);
        setPosition(x + 0.5, y, z + 0.5);
        this.placedBy = placedBy;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(CoolDown, 40);
        dataManager.register(CanExplode, false);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setShort("coolDown", (short) coolDown);
        compound.setBoolean("canExplode", canExplode);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        coolDown = compound.getShort("coolDown");
        canExplode = compound.getBoolean("canExplode");
    }

    @Override
    public void onUpdate() {
        if (!world.isRemote) {
            coolDown--;
            if (coolDown > 0) {
                return;
            }
            canExplode = true;

            setDead();
            world.createExplosion(this,
                    this.posX,
                    this.posY + (double) (this.height / 16.0F),
                    this.posZ,
                    rand.nextInt(4) + 2,
                    true);
        } else {
            if (canExplode) {
                return;
            }
            spawnParticleWithEntity(EnumParticleTypes.SMOKE_LARGE, 0, 0.5, 0);
            spawnParticleWithEntity(EnumParticleTypes.SMOKE_LARGE, 0.5, 0, 0);
            spawnParticleWithEntity(EnumParticleTypes.SMOKE_LARGE, -0.5, 0, 0);
            spawnParticleWithEntity(EnumParticleTypes.SMOKE_LARGE, 0, 0, 0.5);
            spawnParticleWithEntity(EnumParticleTypes.SMOKE_LARGE, 0, 0, -0.5);
            spawnParticleWithEntity(EnumParticleTypes.FLAME, 0, 0.5, 0);
            spawnParticleWithEntity(EnumParticleTypes.FLAME, 0.5, 0, 0);
            spawnParticleWithEntity(EnumParticleTypes.FLAME, -0.5, 0, 0);
            spawnParticleWithEntity(EnumParticleTypes.FLAME, 0, 0, 0.5);
            spawnParticleWithEntity(EnumParticleTypes.FLAME, 0, 0, -0.5);
        }
    }

    @SideOnly(Side.CLIENT)
    private void spawnParticleWithEntity(EnumParticleTypes type, double offsetX, double offsetY, double offsetZ) {
        world.spawnParticle(type,
                posX + offsetX + rand.nextFloat(),
                posY + offsetY + rand.nextFloat(),
                posZ + offsetZ + rand.nextFloat(),
                rand.nextFloat() * getPorN(),
                rand.nextFloat() * getPorN(),
                rand.nextFloat() * getPorN());
    }

    private int getPorN() {
        return rand.nextInt(2) == 0 ? 1 : -1;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return !isDead;
    }


    @Override
    public float getEyeHeight() {
        return 0.0F;
    }

    @Nullable
    public EntityLivingBase getPlacedBy() {
        return placedBy;
    }
}
