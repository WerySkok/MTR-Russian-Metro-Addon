package ru.weryskok.mtrrumetro;

import mtr.RegistryObject;
import mtr.mappings.RegistryUtilities;
import net.minecraft.world.level.block.entity.BlockEntityType;
import ru.weryskok.mtrrumetro.blocks.*;

public interface BlockEntityTypes {
    RegistryObject<BlockEntityType<BlockSPBHorizontalElevatorDoor.TileEntitySPBHorizontalElevatorDoor>> SPB_HORIZONTAL_ELEVATOR_DOOR_TILE_ENTITY = new RegistryObject<>(() -> RegistryUtilities.getBlockEntityType(BlockSPBHorizontalElevatorDoor.TileEntitySPBHorizontalElevatorDoor::new, Blocks.SPB_HORIZONTAL_ELEVATOR_DOOR.get()));
}