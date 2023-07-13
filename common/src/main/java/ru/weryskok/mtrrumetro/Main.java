package ru.weryskok.mtrrumetro;

import mtr.Registry;
import mtr.RegistryObject;
import mtr.mappings.BlockEntityMapper;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.BiConsumer;

public class Main {
    public static final String MOD_ID = "russianmetro";
    public static Blocks blocks;
    public static Items items;
    public static SoundEvents soundEvents;
    public static BlockEntityTypes blockEntityTypes;

    public static void init(
            BiConsumer<String, RegistryObject<Item>> registerItem,
            BiConsumer<String, RegistryObject<Block>> registerBlock,
            RegisterBlockItem registerBlockItem,
            BiConsumer<String, RegistryObject<? extends BlockEntityType<? extends BlockEntityMapper>>> registerBlockEntityType,
            BiConsumer<String, RegistryObject<? extends EntityType<? extends Entity>>> registerEntityType,
            BiConsumer<String, SoundEvent> registerSoundEvent
    ) {
        //Срать в ините = идея хуйня ебаная.
        //Лучше разделить на классы, вынести все в свое
        //Java <3
        blocks = new Blocks(registerBlock, registerBlockItem);
        items = new Items(registerItem);
        soundEvents = new SoundEvents(registerSoundEvent);
        blockEntityTypes = new BlockEntityTypes(registerBlockEntityType);

        blocks.registerBlockItems();
        items.registerItem();
        soundEvents.regiserEvents();
        blockEntityTypes.registerBlockEntites();

    }

    @FunctionalInterface
    public interface RegisterBlockItem {
        void accept(String string, RegistryObject<Block> block, mtr.CreativeModeTabs.Wrapper tab);
    }
}