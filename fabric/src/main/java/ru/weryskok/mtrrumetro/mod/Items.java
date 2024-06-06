package ru.weryskok.mtrrumetro.mod;

import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.Item;
import org.mtr.mapping.registry.ItemRegistryObject;
import ru.weryskok.mtrrumetro.mod.items.ItemSPBHorizontalElevatorDoor;

public class Items {

	static {
		SPB_HORIZONTAL_ELEVATOR_DOOR = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "spb_horizontal_elevator_door"), itemSettings -> new Item(new ItemSPBHorizontalElevatorDoor(itemSettings, false)), CreativeModeTabs.RUSSIAN_METRO_STUFF);
		SPB_HORIZONTAL_ELEVATOR_DOOR_ODD = Init.REGISTRY.registerItem(new Identifier(Init.MOD_ID, "spb_horizontal_elevator_door_odd"), itemSettings -> new Item(new ItemSPBHorizontalElevatorDoor(itemSettings, true)), CreativeModeTabs.RUSSIAN_METRO_STUFF);
	}

	public static final ItemRegistryObject SPB_HORIZONTAL_ELEVATOR_DOOR;
	public static final ItemRegistryObject SPB_HORIZONTAL_ELEVATOR_DOOR_ODD;

	public static void init() {
		org.mtr.mod.Init.LOGGER.info("Registering MTR Russian Metro Addon items");
	}
}
