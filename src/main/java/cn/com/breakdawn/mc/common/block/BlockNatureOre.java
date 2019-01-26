package cn.com.breakdawn.mc.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class BlockNatureOre extends BlockFirst {
    public BlockNatureOre(Material blockMaterialIn, String name) {
        super(blockMaterialIn, name);
        this.setHardness(25);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add(TextFormatting.GREEN + I18n.format("tooltip.nature_ore.normal"));
    }
}
