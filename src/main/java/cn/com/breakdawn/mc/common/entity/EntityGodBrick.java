package cn.com.breakdawn.mc.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * @author KSGFK create in 2019/5/2
 */
public class EntityGodBrick extends EntityThrowable {
    private static final DataParameter<Integer> DAMAGE = EntityDataManager.createKey(EntityGodBrick.class, DataSerializers.VARINT);
    private static final DataParameter<Float> GRAVITY = EntityDataManager.createKey(EntityGodBrick.class, DataSerializers.FLOAT);

    private int damage = 1;
    private float gravity = 0;

    public EntityGodBrick(World worldIn) {
        super(worldIn);
        this.getDataManager().register(DAMAGE, damage);
        this.getDataManager().register(GRAVITY, gravity);
    }

    public EntityGodBrick(World worldIn, EntityLivingBase entityLivingBase) {
        super(worldIn, entityLivingBase);
        thrower = entityLivingBase;
        this.getDataManager().register(DAMAGE, damage);
        this.getDataManager().register(GRAVITY, gravity);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.typeOfHit == RayTraceResult.Type.ENTITY && getThrower() != null && !result.entityHit.getUniqueID().equals(this.getThrower().getUniqueID())) {
            Entity e = result.entityHit;
            if (e instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) e;
                if (player.isCreative() || player.getName().equals("kabi0210")) {
                    e.attackEntityFrom(new EntityDamageSource("brick", getThrower()), 999);
                    ((EntityPlayer) e).setHealth(0);
                    e.setDead();
                }
            }
        } else if (result.typeOfHit == RayTraceResult.Type.BLOCK) {
            this.setDead();
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.ticksExisted > 200) {
            this.setDead();
        }
    }
}
