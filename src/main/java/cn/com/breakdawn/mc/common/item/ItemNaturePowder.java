package cn.com.breakdawn.mc.common.item;

import cn.com.breakdawn.mc.common.block.BlockNatureOre;
import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import cn.com.breakdawn.mc.util.IMetaItemRender;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;

/**
 * @auther KSGFK
 */
public class ItemNaturePowder extends Item implements IMetaItemRender {
    public ItemNaturePowder() {
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

    @Override
    public void renderModel(Item item) {
        for (int a = 0; a < BlockNatureOre.EnumType.values().length; a++) {
            ModelLoader.setCustomModelResourceLocation(item, a, new ModelResourceLocation(item.getRegistryName() + "_" + BlockNatureOre.EnumType.values()[a].getUnlocalizedName(), "inventory"));
        }
    }
}
