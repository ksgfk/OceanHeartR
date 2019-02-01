package cn.com.breakdawn.mc.common.item;

import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

/**
 * 世界树的果实
 * KSGFK 创建于 2019/3/31
 */
public class ItemFruitWT extends ItemFood {
    public ItemFruitWT() {
        super(1, 16, true);
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
        this.setAlwaysEdible();
    }

    @Override
    public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote) {
            player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(1)), 6000, 3));
            player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(3)), 6000, 3));
            player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(5)), 6000, 3));
            player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(6)), 6000, 3));
            player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(8)), 6000, 3));
            player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(10)), 6000, 3));
            player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(11)), 6000, 3));
            player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(12)), 6000, 3));
            player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(13)), 6000, 3));
            player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(14)), 6000, 3));
            player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(16)), 6000, 3));
            player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(21)), 6000, 3));
            player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(22)), 6000, 3));
            player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(23)), 6000, 3));
            player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(24)), 6000, 3));
            player.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(26)), 6000, 3));
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("tooltip.fruit_yggdrasill_item.normal"));
    }
}
