package cn.com.breakdawn.mc.common.item;

import cn.com.breakdawn.mc.common.entity.EntityGodBrick;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author KSGFK create in 2019/5/2
 */
public class ItemGodBrick extends ItemBase {
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.isRemote) {
            EntityGodBrick brick = new EntityGodBrick(worldIn, playerIn);
            brick.setPosition(playerIn.posX, playerIn.posY + playerIn.eyeHeight + 0.5, playerIn.posZ);
            Vec3d v = playerIn.getLookVec();
            brick.shoot(v.x, v.y, v.z, 4f, 0f);
            brick.setNoGravity(true);
            worldIn.spawnEntity(brick);
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("tooltip.god_brick.base"));
    }
}
