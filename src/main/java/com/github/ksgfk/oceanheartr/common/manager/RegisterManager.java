package com.github.ksgfk.oceanheartr.common.manager;

import com.github.ksgfk.oceanheartr.annotation.ItemRegister;
import com.github.ksgfk.oceanheartr.annotation.ModRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.discovery.ASMDataTable;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 该管理者的生命周期到触发{@link net.minecraftforge.fml.common.event.FMLLoadCompleteEvent}事件时结束
 *
 * @author KSGFK create in 2019/8/12
 */
public class RegisterManager {
    private static RegisterManager instance = new RegisterManager();

    private List<Item> ohrItems = new ArrayList<>();

    @Nullable
    public static RegisterManager getInstance() {
        return instance;
    }

    private RegisterManager() {
    }

    public void register(ASMDataTable table) throws ClassNotFoundException, IllegalAccessException {
        for (ASMDataTable.ASMData asmClass : table.getAll(ModRegistry.class.getName())) {
            Class<?> realClass = Class.forName(asmClass.getClassName());
            for (Field registerElement : realClass.getFields()) {
                Object elementInstance = registerElement.get(null);
                if (registerElement.isAnnotationPresent(ItemRegister.class)) {
                    if (elementInstance instanceof Item) {
                        ohrItems.add((Item) elementInstance);
                    }
                }
            }
        }
    }

    public Item[] getItems() {
        Item[] itemArray = new Item[ohrItems.size()];
        ohrItems.toArray(itemArray);
        return itemArray;
    }

    public static void dispose() {
        instance = null;
    }
}
