package cn.com.breakdawn.mc.common.item;

import cn.com.breakdawn.mc.common.init.CreativeTabsOHR;
import cn.com.breakdawn.mc.util.IMetaItemRender;
import com.google.common.collect.Maps;
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
import java.util.Map;

/**
 * 图标类
 * KSGFK 创建于 2019/1/29
 */
public class ItemIcon extends Item implements IMetaItemRender {
    public ItemIcon() {
        this.setCreativeTab(CreativeTabsOHR.tabsOceanHeart);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (ItemIcon.IconType ItemIcon$IconType : ItemIcon.IconType.values()) {
            items.add(new ItemStack(this, 1, ItemIcon$IconType.getMetadata()));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        ItemIcon.IconType itemIcon$IconType = ItemIcon.IconType.byItemStack(itemStack);
        return this.getUnlocalizedName() + "." + itemIcon$IconType.getUnlocalizedName();
    }

    @Override
    public void renderModel(Item item) {
        for (int a = 0; a < IconType.values().length; a++) {
            ModelLoader.setCustomModelResourceLocation(item, a, new ModelResourceLocation(item.getRegistryName() + "_" + IconType.values()[a].getUnlocalizedName(), "inventory"));
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        int meta = stack.getItemDamage();
        switch (meta) {
            case 0:
                tooltip.add(I18n.format("tooltip.icon.terminal.normal"));
                break;
            case 1:
                //tooltip.add(I18n.format("tooltip.icon.world.normal"));
                break;
        }
    }

    public enum IconType {
        TERMINAL(0, "terminal"),
        WORLD(1, "world"),
        BACK(2, "back"),
        BANK(3, "bank"),
        HOME(4, "home"),
        SIGNIN(5, "signin"),
        SPAWN(6, "spawn"),
        TERRITORY(7, "territory");

        private static final Map<Integer, IconType> META_LOOKUP = Maps.newHashMap();

        private final int meta;
        private final String unlocalizedName;

        IconType(int meta, String unlocalizedName) {
            this.meta = meta;
            this.unlocalizedName = unlocalizedName;
        }

        public int getMetadata() {
            return this.meta;
        }

        public String getUnlocalizedName() {
            return this.unlocalizedName;
        }

        public static IconType byMetadata(int meta) {
            IconType itemIcon$IconType = META_LOOKUP.get(meta);
            return itemIcon$IconType == null ? TERMINAL : itemIcon$IconType;
        }

        public static IconType byItemStack(ItemStack stack) {
            return byMetadata(stack.getMetadata());
        }

        static {
            for (IconType itemIcon$IconType : values()) {
                META_LOOKUP.put(itemIcon$IconType.getMetadata(), itemIcon$IconType);
            }
        }
    }
}
