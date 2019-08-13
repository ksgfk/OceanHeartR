package com.github.ksgfk.oceanheartr.common.manager;

import com.github.ksgfk.oceanheartr.annotation.ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.discovery.ASMDataTable;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 该管理者的生命周期到触发{@link net.minecraftforge.fml.common.event.FMLLoadCompleteEvent}事件时结束
 *
 * @author KSGFK create in 2019/8/12
 */
public class RegisterManager {
    private static RegisterManager instance = new RegisterManager();

    private Map<Class<?>, Consumer<Object>> tableDrive = new HashMap<>();
    private List<Item> ohrItems = new ArrayList<>();
    private List<Block> ohrBlocks = new ArrayList<>();

    @Nullable
    public static RegisterManager getInstance() {
        return instance;
    }

    private RegisterManager() {
        tableDrive.put(Item.class, (element) -> ohrItems.add((Item) element));
        tableDrive.put(Block.class, (element) -> ohrBlocks.add((Block) element));
    }

    public void register(ASMDataTable table) throws ClassNotFoundException, IllegalAccessException {
        for (ASMDataTable.ASMData asmClass : table.getAll(ModRegistry.class.getName())) {
            Class<?> realClass = Class.forName(asmClass.getClassName());
            for (Field registerElement : realClass.getFields()) {
                Object elementInstance = registerElement.get(null);
                tableDrive.get(registerElement.getType()).accept(elementInstance);
            }
        }
    }

    public Item[] getItems() {
        Item[] itemArray = new Item[ohrItems.size()];
        ohrItems.toArray(itemArray);
        return itemArray;
    }

    public Block[] getBlocks() {
        Block[] blockArray = new Block[ohrBlocks.size()];
        ohrBlocks.toArray(blockArray);
        return blockArray;
    }

    public static void dispose() {
        instance = null;
    }
}
