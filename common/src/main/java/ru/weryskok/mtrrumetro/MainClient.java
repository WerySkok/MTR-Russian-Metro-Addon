package ru.weryskok.mtrrumetro;

import mtr.RegistryClient;
import mtr.data.PIDSType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
//import ru.weryskok.mtrrumetro.config.Config;

public class MainClient {
    public static void init() {
        RegistryClient.registerBlockRenderType(RenderType.cutoutMipped(), Blocks.MOSCOW_OLD_TICKET_BARRIER_ENTRANCE.get());
        RegistryClient.registerBlockRenderType(RenderType.cutoutMipped(), Blocks.MOSCOW_OLD_TICKET_BARRIER_EXIT.get());
        RegistryClient.registerBlockRenderType(RenderType.cutoutMipped(), Blocks.MOSCOW_OLD_TICKET_BARRIER_SIDE_COVER.get());

        RegistryClient.registerBlockRenderType(RenderType.translucent(), Blocks.MOSCOW_NEW_TICKET_BARRIER_ENTRANCE.get());
        RegistryClient.registerBlockRenderType(RenderType.translucent(), Blocks.MOSCOW_NEW_TICKET_BARRIER_EXIT.get());
        RegistryClient.registerBlockRenderType(RenderType.translucent(), Blocks.MOSCOW_NEW_TICKET_BARRIER_SIDE_COVER.get());

        RegistryClient.registerBlockRenderType(RenderType.cutoutMipped(), Blocks.MOSCOW_NEW_TICKET_MACHINE.get());
//        Config.refreshProperties();
    }

    public static void registerItemModelPredicates() {

    }
}