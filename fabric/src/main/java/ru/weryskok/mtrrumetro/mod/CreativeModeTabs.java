package ru.weryskok.mtrrumetro.mod;

import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.ItemConvertible;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.registry.CreativeModeTabHolder;

public final class CreativeModeTabs {

	static {
		RUSSIAN_METRO_STUFF = Init.REGISTRY.createCreativeModeTabHolder(new Identifier(Init.MOD_ID, "russianmetro_stuff"), () -> new ItemStack(new ItemConvertible(Blocks.MOSCOW_OLD_TICKET_BARRIER_ENTRANCE.get().data)));
	}

	public static final CreativeModeTabHolder RUSSIAN_METRO_STUFF;

	public static void init() {
		Init.LOGGER.info("Registering MTR Russian Metro Addon creative mode tabs");
	}
}
