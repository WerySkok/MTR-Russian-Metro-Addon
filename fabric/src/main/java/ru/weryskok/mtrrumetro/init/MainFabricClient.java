package ru.weryskok.mtrrumetro.init;

import net.fabricmc.api.ClientModInitializer;
import ru.weryskok.mtrrumetro.mod.InitClient;

public class MainFabricClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		InitClient.init();
	}
}
