package ru.weryskok.mtrrumetro;

import mtr.RegistryClient;
import mtr.data.PIDSType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
//import ru.weryskok.mtrrumetro.config.Config;

public class MainClient {
    public static void init() {
        RegistryClient.registerBlockRenderType(RenderType.cutout(), Blocks.MOSCOW_OLD_TICKET_BARRIER_ENTRANCE.get());
        RegistryClient.registerBlockRenderType(RenderType.cutout(), Blocks.MOSCOW_OLD_TICKET_BARRIER_EXIT.get());
//        Config.refreshProperties();
    }

    public static void registerItemModelPredicates() {

    }
}