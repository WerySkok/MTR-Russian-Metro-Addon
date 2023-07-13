package ru.weryskok.mtrrumetro;

import mtr.RegistryObject;
import net.minecraft.world.item.Item;
import ru.weryskok.mtrrumetro.items.*;

import java.util.function.BiConsumer;

public class Items {
    private final BiConsumer<String, RegistryObject<Item>> registerItem;
    public static final RegistryObject<Item> SPB_HORIZONTAL_ELEVATOR_DOOR = new RegistryObject<>(ItemSPBHorizontalElevatorDoor::new);

    public Items(BiConsumer<String, RegistryObject<Item>> registerItem) {
        this.registerItem = registerItem;
    }

    public void registerItem(){
        registerItem.accept("spb_horizontal_elevator_door", Items.SPB_HORIZONTAL_ELEVATOR_DOOR);
    }
}