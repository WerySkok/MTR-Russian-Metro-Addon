package ru.weryskok.mtrrumetro.mod;

import org.mtr.mapping.holder.Block;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.registry.BlockRegistryObject;
import ru.weryskok.mtrrumetro.mod.blocks.*;

public final class Blocks {

	static {
		MOSCOW_OLD_TICKET_BARRIER_ENTRANCE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "moscow_old_ticket_barrier_entrance"), () -> new Block(new BlockMoscowOldTicketBarrier(true)), CreativeModeTabs.RUSSIAN_METRO_STUFF);
		MOSCOW_OLD_TICKET_BARRIER_EXIT = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "moscow_old_ticket_barrier_exit"), () -> new Block(new BlockMoscowOldTicketBarrier(false)), CreativeModeTabs.RUSSIAN_METRO_STUFF);
		MOSCOW_OLD_TICKET_BARRIER_SIDE_COVER = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "moscow_old_ticket_barrier_side_cover"), () -> new Block(new BlockMoscowOldTicketBarrierSideCover()), CreativeModeTabs.RUSSIAN_METRO_STUFF);

		MOSCOW_NEW_TICKET_BARRIER_ENTRANCE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "moscow_new_ticket_barrier_entrance"), () -> new Block(new BlockMoscowNewTicketBarrier(true)), CreativeModeTabs.RUSSIAN_METRO_STUFF);
		MOSCOW_NEW_TICKET_BARRIER_EXIT = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "moscow_new_ticket_barrier_exit"), () -> new Block(new BlockMoscowNewTicketBarrier(false)), CreativeModeTabs.RUSSIAN_METRO_STUFF);
		MOSCOW_NEW_TICKET_BARRIER_SIDE_COVER = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "moscow_new_ticket_barrier_side_cover"), () -> new Block(new BlockMoscowNewTicketBarrierSideCover()), CreativeModeTabs.RUSSIAN_METRO_STUFF);
		MOSCOW_NEW_TICKET_MACHINE = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "moscow_new_ticket_machine"), () -> new Block(new BlockMoscowNewTicketMachine()), CreativeModeTabs.RUSSIAN_METRO_STUFF);

		MOSCOW_METRO_LOGO = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "moscow_metro_logo"), () -> new Block(new BlockMoscowMetroLogo()), CreativeModeTabs.RUSSIAN_METRO_STUFF);

		MOSCOW_OLD_INFOSOS_STAND = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "moscow_old_infosos_stand"), () -> new Block(new BlockMoscowOldInfoSosStand()), CreativeModeTabs.RUSSIAN_METRO_STUFF);

		SPB_HORIZONTAL_ELEVATOR_DOOR = Init.REGISTRY.registerBlock(new Identifier(Init.MOD_ID, "spb_horizontal_elevator_door"), () -> new Block(new BlockSPBHorizontalElevatorDoor(false)));
		SPB_HORIZONTAL_ELEVATOR_DOOR_ODD = Init.REGISTRY.registerBlock(new Identifier(Init.MOD_ID, "spb_horizontal_elevator_door_odd"), () -> new Block(new BlockSPBHorizontalElevatorDoor(true)));
	}

	public static final BlockRegistryObject MOSCOW_OLD_TICKET_BARRIER_ENTRANCE;
	public static final BlockRegistryObject MOSCOW_OLD_TICKET_BARRIER_EXIT;
	public static final BlockRegistryObject MOSCOW_OLD_TICKET_BARRIER_SIDE_COVER;
	public static final BlockRegistryObject MOSCOW_NEW_TICKET_BARRIER_ENTRANCE;
	public static final BlockRegistryObject MOSCOW_NEW_TICKET_BARRIER_EXIT;
	public static final BlockRegistryObject MOSCOW_NEW_TICKET_BARRIER_SIDE_COVER;
	public static final BlockRegistryObject MOSCOW_NEW_TICKET_MACHINE;
	public static final BlockRegistryObject MOSCOW_METRO_LOGO;
	public static final BlockRegistryObject MOSCOW_OLD_INFOSOS_STAND;
	public static final BlockRegistryObject SPB_HORIZONTAL_ELEVATOR_DOOR;
	public static final BlockRegistryObject SPB_HORIZONTAL_ELEVATOR_DOOR_ODD;

	public static void init() {
		Init.LOGGER.info("Registering MTR Russian Metro Addon blocks");
	}
}