package com.github.ksgfk.oceanheartr.common.manager;

import com.github.ksgfk.oceanheartr.OceanHeartR;
import com.github.ksgfk.oceanheartr.annotation.ModRegistry;
import com.github.ksgfk.oceanheartr.annotation.OreDict;
import com.github.ksgfk.oceanheartr.annotation.OreGenBusSubscriber;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.oredict.OreDictionary;

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
    private List<Field> oreDict = new ArrayList<>();
    private List<Class<?>> oreGenBusSuber = new ArrayList<>();

    @Nullable
    public static RegisterManager getInstance() {
        return instance;
    }

    private RegisterManager() {
        tableDrive.put(Item.class, (element) -> ohrItems.add((Item) element));
        tableDrive.put(Block.class, (element) -> ohrBlocks.add((Block) element));
    }

    public void register(ASMDataTable table) throws ClassNotFoundException, IllegalAccessException {
        registerModElements(table);
        registerEventSuber(table);
    }

    private void registerModElements(ASMDataTable table) throws ClassNotFoundException, IllegalAccessException {
        for (ASMDataTable.ASMData asmClass : table.getAll(ModRegistry.class.getName())) {
            Class<?> realClass = Class.forName(asmClass.getClassName());
            for (Field registerElement : realClass.getFields()) {
                Object elementInstance = registerElement.get(null);
                if (!tableDrive.containsKey(registerElement.getType())) {
                    continue;
                }
                tableDrive.get(registerElement.getType()).accept(elementInstance);
                if (!registerElement.isAnnotationPresent(OreDict.class)) {
                    continue;
                }
                oreDict.add(registerElement);
            }
        }
    }

    private void registerEventSuber(ASMDataTable table) throws ClassNotFoundException {
        for (ASMDataTable.ASMData asmClass : table.getAll(OreGenBusSubscriber.class.getName())) {
            oreGenBusSuber.add(Class.forName(asmClass.getClassName()));
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

    public Iterable<Field> getOreDict() {
        return oreDict;
    }

    public Iterable<Class<?>> getOreGenSuber() {
        return oreGenBusSuber;
    }

    public static void dispose() {
        instance = null;
    }

    public static void registerOreDict(Field field) {
        OreDict anno = field.getAnnotation(OreDict.class);
        Object instance = null;
        try {
            instance = field.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (instance instanceof Item) {
            OreDictionary.registerOre(anno.name(), (Item) instance);
        } else if (instance instanceof Block) {
            OreDictionary.registerOre(anno.name(), (Block) instance);
        } else {
            OceanHeartR.logger.warn("只有Item和Block可以注册矿词,不支持:{}", field.getType().getTypeName());
        }
    }
}
