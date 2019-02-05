package cn.com.breakdawn.mc.util;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.UUID;

/**
 * 一些工具
 * KSGFK 创建于 2019/2/2
 */
public class Util {
    /**
     * 添加附魔,自带附魔检查
     *
     * @param stack 需要附魔的ItemStack
     * @param id    附魔ID
     * @param level 附魔等级
     */
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
        for(GameProfile player:FMLCommonHandler.instance().getMinecraftServerInstance().getServerStatusResponse().getPlayers().getPlayers())
            if(player.getName().equalsIgnoreCase(name))
                return player;
        return null;
    }

    public static GameProfile getOfflinePlayerByUUID(UUID uuid) {
        for(GameProfile player:FMLCommonHandler.instance().getMinecraftServerInstance().getServerStatusResponse().getPlayers().getPlayers())
            if(player.getId().equals(uuid))
                return player;
        return null;
    }
}
