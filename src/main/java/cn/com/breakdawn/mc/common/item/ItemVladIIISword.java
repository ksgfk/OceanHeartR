package cn.com.breakdawn.mc.common.item;

import cn.com.breakdawn.mc.OceanHeartR;
import cn.com.breakdawn.mc.common.init.OHRPotion;
import cn.com.breakdawn.mc.config.OHRConfig;
import cn.com.breakdawn.mc.util.Util;
import com.google.common.collect.Lists;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

/**
 * @author KSGFK create in 2019/2/18
 */
public class ItemVladIIISword extends ItemSwordBase {
    public static final Item.ToolMaterial VLADIII = EnumHelper.addToolMaterial("vlad_iii", 3, 1561, 8.0F, 4.0F, 10);

    private List<EntityLivingBase> near = Lists.newArrayList();
    private int range = 4;
    private int time = 100;

    private Potion fast = Objects.requireNonNull(Potion.getPotionById(1));

    public ItemVladIIISword() {
        super(VLADIII);
        this.attackDamage = 3.0F + VLADIII.getAttackDamage();
        this.attackSpeed = (float) -2.8;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        if (stack != null) {
            Util.addUnbreakable(stack);
        }
        return false;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!worldIn.isRemote && entityIn instanceof EntityPlayer && stack != null && isSelected) {
            if (stack.hasTagCompound() && stack.getTagCompound().hasKey("isOpening")) {
                NBTTagCompound nbt = stack.getTagCompound();
                boolean isOpening = nbt.getBoolean("isOpening");
                EntityPlayer player = (EntityPlayer) entityIn;
                if (isOpening) {
                    if (!player.isPotionActive(fast)) {
                        player.addPotionEffect(new PotionEffect(fast, 20000, 1));
                        player.addPotionEffect(new PotionEffect(OHRPotion.BLEED, 20000, 0));
                    }
                } else if (player.isPotionActive(fast) && player.isPotionActive(OHRPotion.BLEED)) {
                    player.removePotionEffect(fast);
                    player.removePotionEffect(OHRPotion.BLEED);
                }
            }
        }
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (!player.world.isRemote) Util.playerReturnHealth(player, 1);
        return false;
    }

    private int maxCD = OHRConfig.general.vladIIIMaxCD;

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        //long begintime = System.currentTimeMillis();
        if (!worldIn.isRemote) {
            boolean canUse;
            boolean isOpening;
            long cd;
            ItemStack sword = handIn == EnumHand.MAIN_HAND ? playerIn.getHeldItemMainhand() : playerIn.getHeldItemOffhand();
            NBTTagCompound nbt = sword.getTagCompound();

            if (nbt.hasKey("canUse")) {
                if (nbt.hasKey("cd")) cd = nbt.getLong("cd");
                else {
                    cd = -1;
                    OceanHeartR.getLogger().warn("未检测到cd标签,这可能是个bug");
                }
                canUse = nbt.getBoolean("canUse");
                if (!canUse) if (System.currentTimeMillis() > cd & cd != -1) canUse = true;
            } else {//第一次使用
                nbt.setBoolean("canUse", true);
                cd = System.currentTimeMillis() + maxCD * 1000;
                nbt.setLong("cd", cd);
                canUse = nbt.getBoolean("canUse");
            }

            if (nbt.hasKey("isOpening")) isOpening = nbt.getBoolean("isOpening");
            else isOpening = false;

            if (canUse && !isOpening) {
                cd = System.currentTimeMillis() + maxCD * 1000;
                nbt.setLong("cd", cd);
                List<EntityLivingBase> entities = worldIn.getEntities(EntityLivingBase.class, EntityLivingBase::isServerWorld);
                BlockPos pPos = playerIn.getPosition();

                if (!near.isEmpty()) near.clear();

                entities.forEach(e -> {
                    BlockPos pos = e.getPosition();
                    int x = Math.abs(pos.getX() - pPos.getX());
                    int y = Math.abs(pos.getY() - pPos.getY());
                    int z = Math.abs(pos.getZ() - pPos.getZ());
                    if (x <= range && y <= range && z <= range && !e.equals(playerIn)) near.add(e);
                });

                if (!near.isEmpty()) {
                    near.forEach(e -> {
                        e.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(18)), time));
                        e.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(19)), time));
                        e.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(20)), time));
                    });
                }

                if (sword.hasTagCompound()) {
                    nbt.setBoolean("isOpening", true);
                } else {
                    NBTTagCompound newNBT = new NBTTagCompound();
                    nbt.setBoolean("isOpening", true);
                    sword.setTagCompound(newNBT);
                }
                nbt.setBoolean("canUse", false);

                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            } else if (isOpening) nbt.setBoolean("isOpening", false);
        }

        //long endtime = System.currentTimeMillis();
        //OceanHeartR.getLogger().info(endtime - begintime);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private SimpleDateFormat ft = new SimpleDateFormat("mm:ss");

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            tooltip.add(I18n.format("tooltip.more"));
            if (stack.hasTagCompound() && stack.getTagCompound().hasKey("cd")) {
                tooltip.add(I18n.format("tooltip.vlad_iii_sword.initiative"));
                long now = System.currentTimeMillis();
                long cd = stack.getTagCompound().getLong("cd") - now;
                if (cd < 0) {
                    tooltip.add(I18n.format("tooltip.cd") + 0);
                } else {
                    tooltip.add(I18n.format("tooltip.cd ") + ft.format(cd));
                }
            } else
                tooltip.add(I18n.format("tooltip.vlad_iii_sword.initiative"));
            tooltip.add(I18n.format("tooltip.vlad_iii_sword.passive"));
        } else {
            tooltip.add(I18n.format("tooltip.vlad_iii_sword.normal"));
            tooltip.add(I18n.format("tooltip.shift"));
        }
    }
}
