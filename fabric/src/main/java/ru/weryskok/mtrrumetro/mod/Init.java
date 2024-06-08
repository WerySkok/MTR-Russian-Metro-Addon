package ru.weryskok.mtrrumetro.mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mtr.mapping.registry.Registry;
import org.mtr.mapping.tool.DummyClass;

public class Init {

	public static final String MOD_ID = "russianmetro";
	public static final Logger LOGGER = LogManager.getLogger("MTRRussianMetroAddon");
	public static final Registry REGISTRY = new Registry();

	public static void init() {
		Blocks.init();
		Items.init();
		BlockEntityTypes.init();
		CreativeModeTabs.init();
		SoundEvents.init();
		DummyClass.enableLogging();

		// Finish registration
		REGISTRY.init();
	}
}
