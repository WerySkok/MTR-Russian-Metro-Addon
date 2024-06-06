package ru.weryskok.mtrrumetro.mod;

import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.registry.BlockEntityTypeRegistryObject;
import ru.weryskok.mtrrumetro.mod.blocks.BlockSPBHorizontalElevatorDoor;

public final class BlockEntityTypes {

	static {
		SPB_HORIZONTAL_ELEVATOR_DOOR_TILE_ENTITY = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "spb_horizontal_elevator_door"), (pos, state) -> new BlockSPBHorizontalElevatorDoor.BlockEntitySPBHorizontalElevatorDoor(pos, state, false), Blocks.SPB_HORIZONTAL_ELEVATOR_DOOR::get);
		SPB_HORIZONTAL_ELEVATOR_DOOR_ODD_TILE_ENTITY = Init.REGISTRY.registerBlockEntityType(new Identifier(Init.MOD_ID, "spb_horizontal_elevator_door_odd"), (pos, state) -> new BlockSPBHorizontalElevatorDoor.BlockEntitySPBHorizontalElevatorDoor(pos, state, true), Blocks.SPB_HORIZONTAL_ELEVATOR_DOOR_ODD::get);
	}

	public static final BlockEntityTypeRegistryObject<BlockSPBHorizontalElevatorDoor.BlockEntitySPBHorizontalElevatorDoor> SPB_HORIZONTAL_ELEVATOR_DOOR_TILE_ENTITY;
	public static final BlockEntityTypeRegistryObject<BlockSPBHorizontalElevatorDoor.BlockEntitySPBHorizontalElevatorDoor> SPB_HORIZONTAL_ELEVATOR_DOOR_ODD_TILE_ENTITY;

	public static void init() {
		Init.LOGGER.info("Registering MTR Russian Metro Addon block entity types");
	}
}