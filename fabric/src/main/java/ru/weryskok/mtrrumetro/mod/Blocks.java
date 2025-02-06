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

		TRAIN_STOP_SIGN = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "train_stop_sign"), () -> new Block(new BlockTrainStopSign()), CreativeModeTabs.RUSSIAN_METRO_STUFF);
		TRAIN_STOP_SIGN_1 = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "train_stop_sign_1"), () -> new Block(new BlockTrainStopSign()), CreativeModeTabs.RUSSIAN_METRO_STUFF);
		TRAIN_STOP_SIGN_2 = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "train_stop_sign_2"), () -> new Block(new BlockTrainStopSign()), CreativeModeTabs.RUSSIAN_METRO_STUFF);
		TRAIN_STOP_SIGN_3 = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "train_stop_sign_3"), () -> new Block(new BlockTrainStopSign()), CreativeModeTabs.RUSSIAN_METRO_STUFF);
		TRAIN_STOP_SIGN_4 = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "train_stop_sign_4"), () -> new Block(new BlockTrainStopSign()), CreativeModeTabs.RUSSIAN_METRO_STUFF);
		TRAIN_STOP_SIGN_5 = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "train_stop_sign_5"), () -> new Block(new BlockTrainStopSign()), CreativeModeTabs.RUSSIAN_METRO_STUFF);
		TRAIN_STOP_SIGN_6 = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "train_stop_sign_6"), () -> new Block(new BlockTrainStopSign()), CreativeModeTabs.RUSSIAN_METRO_STUFF);
		TRAIN_STOP_SIGN_7 = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "train_stop_sign_7"), () -> new Block(new BlockTrainStopSign()), CreativeModeTabs.RUSSIAN_METRO_STUFF);
		TRAIN_STOP_SIGN_8 = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "train_stop_sign_8"), () -> new Block(new BlockTrainStopSign()), CreativeModeTabs.RUSSIAN_METRO_STUFF);
		TRAIN_STOP_SIGN_9 = Init.REGISTRY.registerBlockWithBlockItem(new Identifier(Init.MOD_ID, "train_stop_sign_9"), () -> new Block(new BlockTrainStopSign()), CreativeModeTabs.RUSSIAN_METRO_STUFF);
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
	public static final BlockRegistryObject TRAIN_STOP_SIGN;
	public static final BlockRegistryObject TRAIN_STOP_SIGN_1;
	public static final BlockRegistryObject TRAIN_STOP_SIGN_2;
	public static final BlockRegistryObject TRAIN_STOP_SIGN_3;
	public static final BlockRegistryObject TRAIN_STOP_SIGN_4;
	public static final BlockRegistryObject TRAIN_STOP_SIGN_5;
	public static final BlockRegistryObject TRAIN_STOP_SIGN_6;
	public static final BlockRegistryObject TRAIN_STOP_SIGN_7;
	public static final BlockRegistryObject TRAIN_STOP_SIGN_8;
	public static final BlockRegistryObject TRAIN_STOP_SIGN_9;

	public static void init() {
		Init.LOGGER.info("Registering MTR Russian Metro Addon blocks");
	}
}