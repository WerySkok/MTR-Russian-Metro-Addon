package ru.weryskok.mtrrumetro;

import mtr.RegistryObject;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import ru.weryskok.mtrrumetro.blocks.*;

public interface Blocks {
    RegistryObject<Block> MOSCOW_OLD_TICKET_BARRIER_ENTRANCE = new RegistryObject<>(()-> new BlockMoscowOldTicketBarrier(true));
    RegistryObject<Block> MOSCOW_OLD_TICKET_BARRIER_EXIT = new RegistryObject<>(()-> new BlockMoscowOldTicketBarrier(false));
    RegistryObject<Block> MOSCOW_OLD_TICKET_BARRIER_SIDE_COVER = new RegistryObject<>(BlockMoscowOldTicketBarrierSideCover::new);
    RegistryObject<Block> MOSCOW_NEW_TICKET_BARRIER_ENTRANCE = new RegistryObject<>(()-> new BlockMoscowNewTicketBarrier(true));
    RegistryObject<Block> MOSCOW_NEW_TICKET_BARRIER_EXIT = new RegistryObject<>(()-> new BlockMoscowNewTicketBarrier(false));
    RegistryObject<Block> MOSCOW_NEW_TICKET_BARRIER_SIDE_COVER = new RegistryObject<>(BlockMoscowNewTicketBarrierSideCover::new);
    RegistryObject<Block> MOSCOW_NEW_TICKET_MACHINE = new RegistryObject<>(() -> new BlockMoscowNewTicketMachine(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(2).lightLevel(state -> 5).noOcclusion()));
    RegistryObject<Block> SPB_HORIZONTAL_ELEVATOR_DOOR = new RegistryObject<>(BlockSPBHorizontalElevatorDoor::new);
}