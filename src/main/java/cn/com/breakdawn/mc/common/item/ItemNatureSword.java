package cn.com.breakdawn.mc.common.item;

import cn.com.breakdawn.mc.client.CreativeTabsOHR;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

import java.util.Random;

/**
 * 自然之剑
 * KSGFK 创建于 2019/1/26
 */
public class ItemNatureSword extends ItemSword {
    public ItemNatureSword(ToolMaterial material, String name) {
        super(material);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (entity instanceof EntityMob) {//玩家当前血量+怪物血量0.25
            float life = (float) (player.getHealth() + ((EntityMob) entity).getHealth() * 0.25);
            player.setHealth(life);
            return false;
        } else if (entity instanceof EntityPlayer) {//PVP随机回复0-5生命
            float life = new Random().nextFloat() * 5;
            player.setHealth(life);
            return false;
        } else {//你不能用这把剑攻击其他生物!
            return true;
        }
    }
}
