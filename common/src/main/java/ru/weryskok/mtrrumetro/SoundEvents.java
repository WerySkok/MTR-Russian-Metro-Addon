package ru.weryskok.mtrrumetro;

import mtr.mappings.RegistryUtilities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.BiConsumer;

public class SoundEvents {
    private final BiConsumer<String, SoundEvent> registerSoundEvent;
    public static final SoundEvent MOSCOW_OLD_TICKET_BARRIER_FAIL = RegistryUtilities.createSoundEvent(new ResourceLocation(Main.MOD_ID, "moscow_old_ticket_barrier_fail"));

    SoundEvents(BiConsumer<String, SoundEvent> registerSoundEvent){
        this.registerSoundEvent =registerSoundEvent;
    }

    public void regiserEvents(){
        registerSoundEvent.accept("moscow_old_ticket_barrier_fail", SoundEvents.MOSCOW_OLD_TICKET_BARRIER_FAIL);
    }
}
