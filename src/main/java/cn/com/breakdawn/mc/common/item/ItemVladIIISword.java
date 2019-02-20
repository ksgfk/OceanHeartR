package cn.com.breakdawn.mc.common.item;

import cn.com.breakdawn.mc.OceanHeartR;
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
import java.util.List;
import java.util.Objects;

/**
 * @author KSGFK create in 2019/2/18
 */
public class ItemVladIIISword extends ItemSwordBase {
    public static final Item.ToolMaterial VLADIII = EnumHelper.addToolMaterial("vlad_iii", 3, 1561, 8.0F, 4.0F, 10);

    private boolean canReturnHealth = false;
    private List<EntityLivingBase> near = Lists.newArrayList();
    private int range = 4;
    private int time = 100;
    private int cd = 0;
    private int maxCD = 7200;
    private boolean isOpenSkill = false;
    private int lastTickLostHealth = 0;
    private EntityPlayer owner;

    static Potion fast = Objects.requireNonNull(Potion.getPotionById(1));

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
            int cd;
            if (stack.hasTagCompound() && stack.getTagCompound().hasKey("cd")) {
                NBTTagCompound nbt = stack.getTagCompound();
                cd = nbt.getInteger("cd");

                EntityPlayer player = (EntityPlayer) entityIn;
                owner = player;

                if (cd > 0) nbt.setInteger("cd", cd - 1);

                if (isOpenSkill && lastTickLostHealth <= 0) {
                    player.addPotionEffect(new PotionEffect(fast, 999999999, 1));
                    player.setHealth(player.getHealth() - 1);
                    lastTickLostHealth = 40;
                } else if (player.isPotionActive(fast)) player.removePotionEffect(fast);

                lastTickLostHealth--;

                if (!canReturnHealth) {
                    float cooledAttackStrength = player.getCooledAttackStrength(0);
                    if (cooledAttackStrength > 0 && cooledAttackStrength < 1) {
                        int cooldown = (int) (cooledAttackStrength * player.getCooldownPeriod());
                        canReturnHealth = cooldown == 16;
                    }
                }
                OceanHeartR.getLogger().info(nbt);
            }
        }
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (!player.world.isRemote && canReturnHealth) {
            player.setHealth(player.getHealth() + 1);
            canReturnHealth = false;
        }
        return false;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        //long begintime = System.currentTimeMillis();
        if (!worldIn.isRemote) {
            ItemStack sword;
            int cd;
            sword = handIn == EnumHand.MAIN_HAND ? playerIn.getHeldItemMainhand() : playerIn.getHeldItemOffhand();

            NBTTagCompound nbt = sword.getTagCompound();
            if (nbt.hasKey("cd")) {
                cd = nbt.getInteger("cd");
            } else {
                nbt.setInteger("cd", 0);
                cd = nbt.getInteger("cd");
            }

            if (cd <= 0) {
                if (!isOpenSkill) isOpenSkill = true;

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
                    nbt.setInteger("cd", maxCD);
                } else {
                    NBTTagCompound newNBT = new NBTTagCompound();
                    nbt.setInteger("cd", maxCD);
                    sword.setTagCompound(newNBT);
                }
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
            }
            OceanHeartR.getLogger().info(sword.getTagCompound());

        }

        if (isOpenSkill) isOpenSkill = false;
        //long endtime = System.currentTimeMillis();
        //OceanHeartR.getLogger().info(endtime - begintime);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            tooltip.add(I18n.format("tooltip.more"));
            tooltip.add(I18n.format("tooltip.vlad_iii_sword.initiative") + I18n.format("tooltip.cd") + cd);
            tooltip.add(I18n.format("tooltip.vlad_iii_sword.passive"));
        } else {
            tooltip.add(I18n.format("tooltip.vlad_iii_sword.normal"));
            tooltip.add(I18n.format("tooltip.shift"));
        }
    }
}
