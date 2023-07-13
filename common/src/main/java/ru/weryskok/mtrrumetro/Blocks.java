package ru.weryskok.mtrrumetro;

import mtr.RegistryObject;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import ru.weryskok.mtrrumetro.blocks.*;
import ru.weryskok.mtrrumetro.blocks.outside.MetroLogo;

import java.util.function.BiConsumer;

public class Blocks {
    private BiConsumer<String, RegistryObject<Block>> registerBlock;
    private Main.RegisterBlockItem registerBlockItem;
    public static final RegistryObject<Block> MOSCOW_OLD_TICKET_BARRIER_ENTRANCE = new RegistryObject<>(()-> new BlockMoscowOldTicketBarrier(true));
    public static final RegistryObject<Block> MOSCOW_OLD_TICKET_BARRIER_EXIT = new RegistryObject<>(()-> new BlockMoscowOldTicketBarrier(false));
    public static final RegistryObject<Block> MOSCOW_OLD_TICKET_BARRIER_SIDE_COVER = new RegistryObject<>(BlockMoscowOldTicketBarrierSideCover::new);
    public static final RegistryObject<Block> MOSCOW_NEW_TICKET_BARRIER_ENTRANCE = new RegistryObject<>(()-> new BlockMoscowNewTicketBarrier(true));
    public static final RegistryObject<Block> MOSCOW_NEW_TICKET_BARRIER_EXIT = new RegistryObject<>(()-> new BlockMoscowNewTicketBarrier(false));
    public static final RegistryObject<Block> MOSCOW_NEW_TICKET_BARRIER_SIDE_COVER = new RegistryObject<>(BlockMoscowNewTicketBarrierSideCover::new);
    public static final RegistryObject<Block> MOSCOW_NEW_TICKET_MACHINE = new RegistryObject<>(() -> new BlockMoscowNewTicketMachine(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(2).lightLevel(state -> 5).noOcclusion()));
    public static final RegistryObject<Block> SPB_HORIZONTAL_ELEVATOR_DOOR = new RegistryObject<>(BlockSPBHorizontalElevatorDoor::new);
    public static final RegistryObject<Block> MOSCOW_OLD_INFOSOS_STAND = new RegistryObject<>(BlockMoscowOldInfoSosStand::new);
    public static final RegistryObject<Block> MOSCOW_METRO_LABEL = new RegistryObject<>(MetroLogo::new);

    public Blocks(BiConsumer<String, RegistryObject<Block>> registerBlock, Main.RegisterBlockItem registerBlockItem){
        this.registerBlock = registerBlock;
        this.registerBlockItem = registerBlockItem;
    }
    public void registerBlockItems(){
        //Регистрация блоков
        //Старые барьеры
        registerBlockItem.accept("moscow_old_ticket_barrier_entrance", Blocks.MOSCOW_OLD_TICKET_BARRIER_ENTRANCE, CreativeModeTabs.RUSSIAN_METRO_STUFF);
        registerBlockItem.accept("moscow_old_ticket_barrier_exit", Blocks.MOSCOW_OLD_TICKET_BARRIER_EXIT, CreativeModeTabs.RUSSIAN_METRO_STUFF);
        registerBlockItem.accept("moscow_old_ticket_barrier_side_cover", Blocks.MOSCOW_OLD_TICKET_BARRIER_SIDE_COVER, CreativeModeTabs.RUSSIAN_METRO_STUFF);
        //Новые барьеры
        registerBlockItem.accept("moscow_new_ticket_barrier_entrance", Blocks.MOSCOW_NEW_TICKET_BARRIER_ENTRANCE, CreativeModeTabs.RUSSIAN_METRO_STUFF);
        registerBlockItem.accept("moscow_new_ticket_barrier_exit", Blocks.MOSCOW_NEW_TICKET_BARRIER_EXIT, CreativeModeTabs.RUSSIAN_METRO_STUFF);
        registerBlockItem.accept("moscow_new_ticket_barrier_side_cover", Blocks.MOSCOW_NEW_TICKET_BARRIER_SIDE_COVER, CreativeModeTabs.RUSSIAN_METRO_STUFF);
        registerBlockItem.accept("moscow_new_ticket_machine", Blocks.MOSCOW_NEW_TICKET_MACHINE, CreativeModeTabs.RUSSIAN_METRO_STUFF);
        //Лого
        registerBlockItem.accept("moscow_metro_label", Blocks.MOSCOW_METRO_LABEL, CreativeModeTabs.RUSSIAN_METRO_STUFF);
        //Табло SOS
        registerBlockItem.accept("moscow_old_infosos_stand", Blocks.MOSCOW_OLD_INFOSOS_STAND, CreativeModeTabs.RUSSIAN_METRO_STUFF);
    }

    public void registerBlocks(){
        registerBlock.accept("spb_horizontal_elevator_door", Blocks.SPB_HORIZONTAL_ELEVATOR_DOOR);
    }
}