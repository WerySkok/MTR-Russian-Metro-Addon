package ru.weryskok.mtrrumetro;

import mtr.RegistryClient;
import net.minecraft.client.renderer.RenderType;
import ru.weryskok.mtrrumetro.render.*;

public class MainClient {
    public static void init() {
        RegistryClient.registerBlockRenderType(RenderType.cutoutMipped(), Blocks.MOSCOW_OLD_TICKET_BARRIER_ENTRANCE.get());
        RegistryClient.registerBlockRenderType(RenderType.cutoutMipped(), Blocks.MOSCOW_OLD_TICKET_BARRIER_EXIT.get());
        RegistryClient.registerBlockRenderType(RenderType.cutoutMipped(), Blocks.MOSCOW_OLD_TICKET_BARRIER_SIDE_COVER.get());

        RegistryClient.registerBlockRenderType(RenderType.translucent(), Blocks.MOSCOW_NEW_TICKET_BARRIER_ENTRANCE.get());
        RegistryClient.registerBlockRenderType(RenderType.translucent(), Blocks.MOSCOW_NEW_TICKET_BARRIER_EXIT.get());
        RegistryClient.registerBlockRenderType(RenderType.translucent(), Blocks.MOSCOW_NEW_TICKET_BARRIER_SIDE_COVER.get());

        RegistryClient.registerBlockRenderType(RenderType.cutoutMipped(), Blocks.MOSCOW_NEW_TICKET_MACHINE.get());

        RegistryClient.registerBlockRenderType(RenderType.cutoutMipped(), Blocks.SPB_HORIZONTAL_ELEVATOR_DOOR.get());
        RegistryClient.registerBlockRenderType(RenderType.cutoutMipped(), Blocks.SPB_HORIZONTAL_ELEVATOR_DOOR_ODD.get());
        RegistryClient.registerTileEntityRenderer(BlockEntityTypes.SPB_HORIZONTAL_ELEVATOR_DOOR_TILE_ENTITY.get(), dispatcher -> new RenderSPBHorizontalElevatorDoor(dispatcher, false));
        RegistryClient.registerTileEntityRenderer(BlockEntityTypes.SPB_HORIZONTAL_ELEVATOR_DOOR_ODD_TILE_ENTITY.get(), dispatcher -> new RenderSPBHorizontalElevatorDoor(dispatcher, true));

        RegistryClient.registerBlockRenderType(RenderType.cutoutMipped(), Blocks.MOSCOW_OLD_INFOSOS_STAND.get());

        RegistryClient.registerBlockRenderType(RenderType.cutoutMipped(), Blocks.MOSCOW_METRO_LOGO.get());
    }

    public static void registerItemModelPredicates() {

    }
}