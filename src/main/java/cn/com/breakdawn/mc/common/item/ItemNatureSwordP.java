package cn.com.breakdawn.mc.common.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 诅咒之剑
 * KSGFK 创建于 2019/2/1
 */
public class ItemNatureSwordP extends ItemNatureSword {
    private boolean isFirstAttack;
    private Entity lastAttackEntity;
    private int damage;

    public ItemNatureSwordP(ToolMaterial material) {
        super(material);
        isFirstAttack = true;
        damage = 0;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (isFirstAttack) {
            lastAttackEntity = entity;
            isFirstAttack = false;
        }
        if (entity.equals(lastAttackEntity)) {
            if (entity instanceof EntityMob) {
                if (!player.capabilities.isCreativeMode) {
                    player.setHealth(player.getHealth() - damage);
                }
                ((EntityMob) entity).setHealth(((EntityMob) entity).getHealth() - damage);
                damage++;
            }
        } else {
            damage = 0;
        }
        lastAttackEntity = entity;
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            tooltip.add(I18n.format("tooltip.more"));
            tooltip.add(I18n.format("tooltip.nature_sword_polluted.mob"));
            if (lastAttackEntity == null) {
                tooltip.add(I18n.format("tooltip.nature_sword_polluted.info") + "null");
            } else {
                tooltip.add(I18n.format("tooltip.nature_sword_polluted.info") + lastAttackEntity.getName());
            }
            tooltip.add(I18n.format("tooltip.nature_sword_polluted.damage") + damage);
        } else {
            tooltip.add(I18n.format("tooltip.nature_sword_polluted.normal"));
            tooltip.add(I18n.format("tooltip.shift"));
        }
    }
}
