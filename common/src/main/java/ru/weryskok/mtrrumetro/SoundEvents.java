package ru.weryskok.mtrrumetro;

import mtr.mappings.RegistryUtilities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public interface SoundEvents {
    SoundEvent MOSCOW_OLD_TICKET_BARRIER_FAIL = RegistryUtilities.createSoundEvent(new ResourceLocation(Main.MOD_ID, "moscow_old_ticket_barrier_fail"));
}
