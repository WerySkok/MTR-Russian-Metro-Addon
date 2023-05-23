package ru.weryskok.mtrrumetro;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public interface CreativeModeTabs {
    mtr.CreativeModeTabs.Wrapper RUSSIAN_METRO_STUFF = new mtr.CreativeModeTabs.Wrapper(new ResourceLocation(Main.MOD_ID, "russianmetro_stuff"), () -> new ItemStack(Blocks.MOSCOW_OLD_TICKET_BARRIER_ENTRANCE.get()));
}