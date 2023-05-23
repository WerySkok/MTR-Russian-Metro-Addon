package ru.weryskok.mtrrumetro;

import mtr.RegistryObject;
import net.minecraft.world.level.block.Block;
import ru.weryskok.mtrrumetro.blocks.*;

public interface Blocks {
    RegistryObject<Block> MOSCOW_OLD_TICKET_BARRIER_ENTRANCE = new RegistryObject<>(()-> new BlockMoscowOldTicketBarrier(true));
    RegistryObject<Block> MOSCOW_OLD_TICKET_BARRIER_EXIT = new RegistryObject<>(()-> new BlockMoscowOldTicketBarrier(false));
    RegistryObject<Block> MOSCOW_OLD_TICKET_BARRIER_SIDE_COVER = new RegistryObject<>(BlockMoscowOldTicketBarrierSideCover::new);
}