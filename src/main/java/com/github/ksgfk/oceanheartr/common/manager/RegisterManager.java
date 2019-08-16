package com.github.ksgfk.oceanheartr.common.manager;

import com.github.ksgfk.oceanheartr.OceanHeartR;
import com.github.ksgfk.oceanheartr.annotation.*;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
    private List<Class<? extends Entity>> ohrEntities = new ArrayList<>();
    private List<ASMDataTable.ASMData> ohrEntityModels = new ArrayList<>();
    private List<Field> furnace = new ArrayList<>();

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
        registerEntities(table);
        registerEntityModels(table);
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
                if (registerElement.isAnnotationPresent(OreDict.class)) {
                    oreDict.add(registerElement);
                }
                if (registerElement.isAnnotationPresent(FurnaceSmelting.class)) {
                    furnace.add(registerElement);
                }
            }
        }
    }

    private void registerEventSuber(ASMDataTable table) throws ClassNotFoundException {
        for (ASMDataTable.ASMData asmClass : table.getAll(OreGenBusSubscriber.class.getName())) {
            oreGenBusSuber.add(Class.forName(asmClass.getClassName()));
        }
    }

    private void registerEntities(ASMDataTable table) throws ClassNotFoundException {
        for (ASMDataTable.ASMData asmClass : table.getAll(EntityRegistry.class.getName())) {
            Class<?> realClass = Class.forName(asmClass.getClassName());
            try {
                Class<? extends Entity> e = realClass.asSubclass(Entity.class);
                ohrEntities.add(e);
            } catch (ClassCastException e) {
                OceanHeartR.logger.error("class {} 不是Entity", realClass.getName());
            }
        }
    }

    private void registerEntityModels(ASMDataTable table) {
        ohrEntityModels.addAll(table.getAll(EntityModelRegistry.class.getName()));
    }

    public EntityEntry[] getEntityEntries() {
        return ohrEntities.stream()
                .map((entityClass) -> {
                    EntityRegistry anno = entityClass.getAnnotation(EntityRegistry.class);
                    EntityEntryBuilder<Entity> builder = EntityEntryBuilder.create()
                            .entity(entityClass)
                            .id(new ResourceLocation(OceanHeartR.MOD_ID, anno.nameID()), anno.numID())
                            .name(anno.nameID())
                            .tracker(anno.updateRange(), anno.updateFrequency(), anno.isSendVelocityUpdates());
                    if (anno.eggPrimaryColor() != -1 && anno.eggSecondaryColor() != -1) {
                        builder.egg(anno.eggPrimaryColor(), anno.eggSecondaryColor());
                    }
                    if (anno.canAutoSpawn()) {
                        List<Biome> biomes = Arrays.stream(anno.biomes())
                                .map((biomeName -> {
                                    Biome biome = Biome.REGISTRY.getObject(new ResourceLocation(biomeName));
                                    if (biome == null) {
                                        throw new IllegalStateException("不存在名为" + biomeName + "的Biome");
                                    }
                                    return biome;
                                }))
                                .collect(Collectors.toList());
                        builder.spawn(anno.CreatureType(), anno.weight(), anno.min(), anno.max(), biomes);
                    }
                    return builder.build();
                })
                .toArray(EntityEntry[]::new);
    }

    public Item[] getItemBlocks() {
        return ohrBlocks.stream()
                .map(block -> {
                    ItemBlock i = new ItemBlock(block);
                    ResourceLocation rl = block.getRegistryName();
                    if (rl == null) {
                        OceanHeartR.logger.error("null ResourceLocation:" + block);
                        return null;
                    }
                    i.setRegistryName(rl);
                    return i;
                })
                .toArray(Item[]::new);
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

    public List<Class<? extends Entity>> getEntities() {
        return ohrEntities;
    }

    public List<ASMDataTable.ASMData> getOhrEntityModels() {
        return ohrEntityModels;
    }

    public List<Field> getFurnace() {
        return furnace;
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
            OceanHeartR.logger.error("无法获取字段引用的实例,{}", field.getType().getTypeName());
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

    public static void registerFurnaceSmelt(Field field) {
        FurnaceSmelting smelting = field.getAnnotation(FurnaceSmelting.class);
        Object instance;
        try {
            instance = field.get(null);
        } catch (IllegalAccessException e) {
            OceanHeartR.logger.error("无法获取字段引用的实例,{}", field.getType().getTypeName());
            return;
        }
        ItemStack objStack = null;
        if (instance instanceof Item) {
            objStack = new ItemStack((Item) instance);
        } else if (instance instanceof Block) {
            objStack = new ItemStack((Block) instance);
        } else {
            OceanHeartR.logger.warn("只有Item和Block可以注册燃烧,不支持:{}", field.getType().getTypeName());
            return;
        }
        Item output = Item.getByNameOrId(OceanHeartR.MOD_ID + ":" + smelting.output());
        if (output == null) {
            throw new IllegalArgumentException();
        }
        GameRegistry.addSmelting(objStack, new ItemStack(output), smelting.exp());
    }
}
