package ru.weryskok.mtrrumetro;

import net.fabricmc.api.ClientModInitializer;

public class MainFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MainClient.init();
        MainClient.registerItemModelPredicates();
    }
}