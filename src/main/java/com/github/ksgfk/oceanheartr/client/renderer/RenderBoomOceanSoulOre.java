package com.github.ksgfk.oceanheartr.client.renderer;

import com.github.ksgfk.oceanheartr.OceanHeartR;
import com.github.ksgfk.oceanheartr.annotation.EntityModelRegistry;
import com.github.ksgfk.oceanheartr.client.model.ModelBoomOceanSoulOre;
import com.github.ksgfk.oceanheartr.common.entity.EntityBoomOceanSoulOre;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

/**
 * @author KSGFK create in 2019/8/13
 */
@EntityModelRegistry(entityClass = EntityBoomOceanSoulOre.class)
public class RenderBoomOceanSoulOre extends RenderLiving<EntityBoomOceanSoulOre> {
    private static ResourceLocation rl = new ResourceLocation(OceanHeartR.MOD_ID, "textures/entity/boom_ocean_soul_ore.png");

    public RenderBoomOceanSoulOre(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelBoomOceanSoulOre(), 0.25F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityBoomOceanSoulOre entity) {
        return rl;
    }
}
