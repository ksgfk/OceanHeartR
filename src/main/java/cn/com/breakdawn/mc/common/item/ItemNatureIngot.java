package cn.com.breakdawn.mc.common.item;

import cn.com.breakdawn.mc.common.block.BlockNatureOre;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 自然结晶系列
 * KSGFK 创建于 2019/2/1
 */
public class ItemNatureIngot extends Item implements IMetaItemRender {
    public ItemNatureIngot() {
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            for (BlockNatureOre.EnumType blocknatureore$enumtype : BlockNatureOre.EnumType.values()) {
                items.add(new ItemStack(this, 1, blocknatureore$enumtype.getMetadata()));
            }
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        BlockNatureOre.EnumType blocknatureore$enumtype = BlockNatureOre.EnumType.byItemStack(itemStack);
        return this.getUnlocalizedName() + "." + blocknatureore$enumtype.getUnlocalizedName();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (stack.getMetadata() == 0) {
            tooltip.add(I18n.format("tooltip.nature_ingot.overworld.normal"));
        } else if (stack.getMetadata() == 1) {
            tooltip.add(I18n.format("tooltip.nature_ingot.nether.normal"));
        }
    }

    @Override
    public void renderModel(Item item) {
        for (int a = 0; a < BlockNatureOre.EnumType.values().length; a++) {
            ModelLoader.setCustomModelResourceLocation(item, a, new ModelResourceLocation(item.getRegistryName() + "_" + BlockNatureOre.EnumType.values()[a].getUnlocalizedName(), "inventory"));
        }
    }
}
