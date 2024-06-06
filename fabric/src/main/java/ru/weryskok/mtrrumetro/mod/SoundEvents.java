package ru.weryskok.mtrrumetro.mod;

import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.registry.SoundEventRegistryObject;

public class SoundEvents {

	static {
		MOSCOW_OLD_TICKET_BARRIER_FAIL = Init.REGISTRY.registerSoundEvent(new Identifier(Init.MOD_ID, "moscow_old_ticket_barrier_fail"));
	}

	public static final SoundEventRegistryObject MOSCOW_OLD_TICKET_BARRIER_FAIL;

	public static void init() {
		org.mtr.mod.Init.LOGGER.info("Registering MTR Russian Metro Addon sound events");
	}
}
