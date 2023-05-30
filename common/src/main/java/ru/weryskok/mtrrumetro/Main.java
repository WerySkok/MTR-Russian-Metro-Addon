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

import java.util.function.BiConsumer;

public class Main {
    public static final String MOD_ID = "russianmetro";

    public static void init(
            BiConsumer<String, RegistryObject<Item>> registerItem,
            BiConsumer<String, RegistryObject<Block>> registerBlock,
            RegisterBlockItem registerBlockItem,
            BiConsumer<String, RegistryObject<? extends BlockEntityType<? extends BlockEntityMapper>>> registerBlockEntityType,
            BiConsumer<String, RegistryObject<? extends EntityType<? extends Entity>>> registerEntityType,
            BiConsumer<String, SoundEvent> registerSoundEvent
    ) {

        registerBlockItem.accept("moscow_old_ticket_barrier_entrance", Blocks.MOSCOW_OLD_TICKET_BARRIER_ENTRANCE, CreativeModeTabs.RUSSIAN_METRO_STUFF);
        registerBlockItem.accept("moscow_old_ticket_barrier_exit", Blocks.MOSCOW_OLD_TICKET_BARRIER_EXIT, CreativeModeTabs.RUSSIAN_METRO_STUFF);
        registerBlockItem.accept("moscow_old_ticket_barrier_side_cover", Blocks.MOSCOW_OLD_TICKET_BARRIER_SIDE_COVER, CreativeModeTabs.RUSSIAN_METRO_STUFF);

        registerBlockItem.accept("moscow_new_ticket_barrier_entrance", Blocks.MOSCOW_NEW_TICKET_BARRIER_ENTRANCE, CreativeModeTabs.RUSSIAN_METRO_STUFF);
        registerBlockItem.accept("moscow_new_ticket_barrier_exit", Blocks.MOSCOW_NEW_TICKET_BARRIER_EXIT, CreativeModeTabs.RUSSIAN_METRO_STUFF);
        registerBlockItem.accept("moscow_new_ticket_barrier_side_cover", Blocks.MOSCOW_NEW_TICKET_BARRIER_SIDE_COVER, CreativeModeTabs.RUSSIAN_METRO_STUFF);

        registerBlockItem.accept("moscow_new_ticket_machine", Blocks.MOSCOW_NEW_TICKET_MACHINE, CreativeModeTabs.RUSSIAN_METRO_STUFF);

    }

    @FunctionalInterface
    public interface RegisterBlockItem {
        void accept(String string, RegistryObject<Block> block, mtr.CreativeModeTabs.Wrapper tab);
    }
}