package ru.weryskok.mtrrumetro.init;

import net.fabricmc.api.ModInitializer;
import ru.weryskok.mtrrumetro.mod.Init;

public class MainFabric implements ModInitializer {

	@Override
	public void onInitialize() {
		Init.init();
	}
}
