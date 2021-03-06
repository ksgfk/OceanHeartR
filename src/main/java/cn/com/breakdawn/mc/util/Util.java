package cn.com.breakdawn.mc.util;

import cn.com.breakdawn.mc.common.init.OHREnch;
import com.mojang.authlib.GameProfile;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.SPacketChunkData;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.management.PlayerChunkMap;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.NibbleArray;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

/**
 * 一些工具
 * KSGFK 创建于 2019/2/2
 */
public class Util {
    public static final Random RANDOM = new Random();

    public static void addEnchantment(ItemStack stack, short id, short level) {
        boolean canEnch = false;

        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
        }

        if (!stack.getTagCompound().hasKey("ench", 9)) {
            stack.getTagCompound().setTag("ench", new NBTTagList());
        }

        NBTTagList nbttaglist = stack.getTagCompound().getTagList("ench", 10);
        if (nbttaglist.tagCount() > 0) {
            for (int a = 0; a < nbttaglist.tagCount(); a++) {
                NBTTagCompound n = nbttaglist.getCompoundTagAt(a);
                short i = n.getShort("id");
                //OceanHeartR.getLogger().info("id:" + i);
                if (i == id) break;
                else {
                    if (a == nbttaglist.tagCount() - 1) canEnch = true;
                }
            }
        } else {
            add(nbttaglist, id, level);
            return;
        }

        if (canEnch) add(nbttaglist, id, level);
    }

    public static void addUnbreakable(ItemStack stack) {
        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
        }

        if (!(stack.getTagCompound().hasKey("Unbreakable"))) {
            stack.setTagCompound(new NBTTagCompound().getCompoundTag("Unbreakable"));
            stack.getTagCompound().setBoolean("Unbreakable", true);
        }
    }

    private static void add(NBTTagList nbttaglist, short id, short level) {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setShort("id", id);
        nbttagcompound.setShort("lvl", level);
        nbttaglist.appendTag(nbttagcompound);
    }

    public static EntityPlayer getPlayerByUUID(UUID uuid) {
        return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(uuid);
    }

    public static EntityPlayer getPlayerByName(String name) {
        return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(name);
    }

    public static GameProfile getOfflinePlayerByName(String name) {
        for (GameProfile player : FMLCommonHandler.instance().getMinecraftServerInstance().getServerStatusResponse().getPlayers().getPlayers())
            if (player.getName().equalsIgnoreCase(name))
                return player;
        return null;
    }

    public static GameProfile getOfflinePlayerByUUID(UUID uuid) {
        for (GameProfile player : FMLCommonHandler.instance().getMinecraftServerInstance().getServerStatusResponse().getPlayers().getPlayers())
            if (player.getId().equals(uuid))
                return player;
        return null;
    }

    public static void relightChunk(Chunk chunk) {
        if (chunk != null) {
            chunk.generateSkylightMap();
            ExtendedBlockStorage[] storage = chunk.getBlockStorageArray();
            for (int i = storage.length; i-- > 0; )
                if (storage[i] != null) {
                    //{ spigot compat: force data array to exist
                    NibbleArray a = storage[i].getSkyLight();
                    if (a != null) {
                        a.set(0, 0, 0, 0);
                        a.set(0, 0, 0, 15);
                        //}
                        Arrays.fill(a.getData(), (byte) 0);
                    }
                }
            chunk.resetRelightChecks();
            chunk.setModified(true);
            World world = chunk.getWorld();
            if (world instanceof WorldServer) {
                PlayerChunkMap chunkMap = ((WorldServer) world).getPlayerChunkMap();

                PlayerChunkMapEntry entry = chunkMap.getEntry(chunk.x, chunk.z);

                if (entry != null)
                    entry.sendPacket(new SPacketChunkData(chunk, -1));
            }
        }
    }

    public static void setPotion(EntityLivingBase entity, int id, int time, int level) {
        entity.addPotionEffect(new PotionEffect(Objects.requireNonNull(Potion.getPotionById(id)), time, level));
    }

    public static void playerReturnHealth(EntityPlayer player, int health) {
        boolean canReturnHealth;
        float cooledAttackStrength = player.getCooledAttackStrength(0);
        if (cooledAttackStrength > 0 && cooledAttackStrength <= 1) {
            int cooldown = (int) (cooledAttackStrength * player.getCooldownPeriod());
            canReturnHealth = cooldown == 16;
        } else canReturnHealth = false;
        if (!player.world.isRemote && canReturnHealth) player.heal(health);
    }

    public static void removeEnchantment(ItemStack stack, Enchantment enc) {

        if (stack.getTagCompound() == null) {
            return;
        }
        if (!stack.getTagCompound().hasKey("ench", 9)) {
            return;
        }
        NBTTagList list = stack.getTagCompound().getTagList("ench", 10);
        int encId = Enchantment.getEnchantmentID(enc);

        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound tag = list.getCompoundTagAt(i);
            int id = tag.getInteger("id");
            if (encId == id) {
                list.removeTag(i);
                break;
            }
        }
    }

    public static void addEnchantment(ItemStack stack, Enchantment enc, int level) {
        stack.addEnchantment(enc, level);
    }

    public static int clamp(int a, int min, int max) {
        return a < min ? min : (a > max ? max : a);
    }

    public static boolean addToPlayerInventory(EntityPlayer player, ItemStack stack) {
        if (stack.isEmpty() || player == null) {
            return false;
        }
        if (stack.getItem() instanceof ItemArmor) {
            ItemArmor arm = (ItemArmor) stack.getItem();
            int index = arm.armorType.getIndex();
            if (player.inventory.armorInventory.get(index).isEmpty()) {
                player.inventory.armorInventory.set(index, stack);
                return true;
            }
        }
        InventoryPlayer inv = player.inventory;
        for (int i = 0; i < inv.mainInventory.size(); i++) {
            if (inv.mainInventory.get(i).isEmpty()) {
                inv.mainInventory.set(i, stack.copy());
                return true;
            }
        }
        return false;
    }

    public static boolean isSoulbound(ItemStack stack) {
        return EnchantmentHelper.getEnchantmentLevel(OHREnch.SOUL_BOND, stack) > 0;
    }
}
