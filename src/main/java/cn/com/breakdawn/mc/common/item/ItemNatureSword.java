package cn.com.breakdawn.mc.common.item;

import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import cn.com.breakdawn.mc.config.OHRConfig;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/**
 * 自然之剑
 * KSGFK 创建于 2019/1/26
 */
public class ItemNatureSword extends ItemSword {
    public ItemNatureSword(ToolMaterial material) {
        super(material);
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (!player.world.isRemote) {
            if (entity instanceof EntityMob) {//玩家当前血量+怪物血量0.25
                float life = (player.getHealth() + ((EntityMob) entity).getHealth() * OHRConfig.general.natureSwordMob);
                player.setHealth(life);
                return false;
            } else if (entity instanceof EntityPlayer) {//PVP随机回复0-5生命
                float life = new Random().nextFloat() * OHRConfig.general.natureSwordPVP;
                player.setHealth(life);
                return false;
            } else {
                if (OHRConfig.general.natureSwordCanHitAnimal) return false;
                else return entity instanceof EntityAnimal;
            }
        }
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            tooltip.add(I18n.format("tooltip.more"));
            tooltip.add(I18n.format("tooltip.nature_sword.shift.mob"));
            tooltip.add(I18n.format("tooltip.nature_sword.shift.player"));
            tooltip.add(I18n.format("tooltip.nature_sword.shift.animal"));
        } else {
            tooltip.add(I18n.format("tooltip.nature_sword.normal"));
            tooltip.add(I18n.format("tooltip.shift"));
        }
    }
}
