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
	}
}
