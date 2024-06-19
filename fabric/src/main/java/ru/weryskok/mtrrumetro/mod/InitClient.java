package ru.weryskok.mtrrumetro.mod;

import org.mtr.mapping.holder.RenderLayer;
import org.mtr.mapping.registry.RegistryClient;
import ru.weryskok.mtrrumetro.mod.render.RenderSPBHorizontalElevatorDoor;

public class InitClient {

	public static final RegistryClient REGISTRY_CLIENT = new RegistryClient(Init.REGISTRY);

	public static void init() {
		REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), Blocks.MOSCOW_OLD_TICKET_BARRIER_ENTRANCE);
		REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), Blocks.MOSCOW_OLD_TICKET_BARRIER_EXIT);
		REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), Blocks.MOSCOW_OLD_TICKET_BARRIER_SIDE_COVER);

		REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), Blocks.MOSCOW_NEW_TICKET_BARRIER_ENTRANCE);
		REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), Blocks.MOSCOW_NEW_TICKET_BARRIER_EXIT);
		REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getTranslucent(), Blocks.MOSCOW_NEW_TICKET_BARRIER_SIDE_COVER);

		REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), Blocks.MOSCOW_NEW_TICKET_MACHINE);

		REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), Blocks.SPB_HORIZONTAL_ELEVATOR_DOOR);
		REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), Blocks.SPB_HORIZONTAL_ELEVATOR_DOOR_ODD);
		REGISTRY_CLIENT.registerBlockEntityRenderer(BlockEntityTypes.SPB_HORIZONTAL_ELEVATOR_DOOR_TILE_ENTITY, dispatcher -> new RenderSPBHorizontalElevatorDoor(dispatcher, false));
		REGISTRY_CLIENT.registerBlockEntityRenderer(BlockEntityTypes.SPB_HORIZONTAL_ELEVATOR_DOOR_ODD_TILE_ENTITY, dispatcher -> new RenderSPBHorizontalElevatorDoor(dispatcher, true));

		REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), Blocks.MOSCOW_OLD_INFOSOS_STAND);

		REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), Blocks.MOSCOW_METRO_LOGO);

		REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), Blocks.TRAIN_STOP_SIGN);
		REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), Blocks.TRAIN_STOP_SIGN_1);
		REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), Blocks.TRAIN_STOP_SIGN_2);
		REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), Blocks.TRAIN_STOP_SIGN_3);
		REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), Blocks.TRAIN_STOP_SIGN_4);
		REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), Blocks.TRAIN_STOP_SIGN_5);
		REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), Blocks.TRAIN_STOP_SIGN_6);
		REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), Blocks.TRAIN_STOP_SIGN_7);
		REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), Blocks.TRAIN_STOP_SIGN_8);
		REGISTRY_CLIENT.registerBlockRenderType(RenderLayer.getCutout(), Blocks.TRAIN_STOP_SIGN_9);

		REGISTRY_CLIENT.init();
	}
}
