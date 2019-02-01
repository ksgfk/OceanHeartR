package cn.com.breakdawn.mc.common.item;

import cn.com.breakdawn.mc.common.block.BlockStrangeOre;
import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import cn.com.breakdawn.mc.util.IMetaItemRender;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 传说系列
 * KSGFK 创建于 2019/2/1
 */
public class ItemStrangeIngot extends Item implements IMetaItemRender {
    public ItemStrangeIngot() {
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (BlockStrangeOre.EnumType blocknatureore$enumtype : BlockStrangeOre.EnumType.values()) {
                items.add(new ItemStack(this, 1, blocknatureore$enumtype.getMetadata()));
            }
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        BlockStrangeOre.EnumType blocknatureore$enumtype = BlockStrangeOre.EnumType.byItemStack(itemStack);
        return this.getUnlocalizedName() + "." + blocknatureore$enumtype.getUnlocalizedName();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        int meta = stack.getItemDamage();
        switch (meta) {
            case 0:
                tooltip.add(I18n.format("tooltip.strange_ingot.gold.normal"));
                break;
            case 1:
                tooltip.add(I18n.format("tooltip.strange_ingot.leve_soul.normal"));
                break;
            case 2:
                tooltip.add(I18n.format("tooltip.strange_ingot.ocean_soul.normal"));
                break;
            case 3:
                tooltip.add(I18n.format("tooltip.strange_ingot.end.normal"));
                break;
        }
    }

    @Override
    public void renderModel(Item item) {
        for (int a = 0; a < BlockStrangeOre.EnumType.values().length; a++) {
            ModelLoader.setCustomModelResourceLocation(item, a, new ModelResourceLocation(item.getRegistryName() + "_" + BlockStrangeOre.EnumType.values()[a].getUnlocalizedName(), "inventory"));
        }
    }
}
