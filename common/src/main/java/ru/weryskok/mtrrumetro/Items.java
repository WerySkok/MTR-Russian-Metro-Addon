package ru.weryskok.mtrrumetro;

import mtr.RegistryObject;
import net.minecraft.world.item.Item;
import ru.weryskok.mtrrumetro.items.*;

public interface Items {
    RegistryObject<Item> SPB_HORIZONTAL_ELEVATOR_DOOR = new RegistryObject<>(ItemSPBHorizontalElevatorDoor::new);
}