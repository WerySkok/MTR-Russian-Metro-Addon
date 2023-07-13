package ru.weryskok.mtrrumetro;

import mtr.RegistryObject;
import mtr.mappings.BlockEntityMapper;
import mtr.mappings.RegistryUtilities;
import net.minecraft.world.level.block.entity.BlockEntityType;
import ru.weryskok.mtrrumetro.blocks.*;

import java.util.function.BiConsumer;

public class BlockEntityTypes {
    public final BiConsumer<String, RegistryObject<? extends BlockEntityType<? extends BlockEntityMapper>>> registerBlockEntityType;
    public static final RegistryObject<BlockEntityType<BlockSPBHorizontalElevatorDoor.TileEntitySPBHorizontalElevatorDoor>> SPB_HORIZONTAL_ELEVATOR_DOOR_TILE_ENTITY = new RegistryObject<>(() -> RegistryUtilities.getBlockEntityType(BlockSPBHorizontalElevatorDoor.TileEntitySPBHorizontalElevatorDoor::new, Blocks.SPB_HORIZONTAL_ELEVATOR_DOOR.get()));

    public BlockEntityTypes(BiConsumer<String, RegistryObject<? extends BlockEntityType<? extends BlockEntityMapper>>> registerBlockEntityType) {
        this.registerBlockEntityType = registerBlockEntityType;
    }

    public void registerBlockEntites(){
        registerBlockEntityType.accept("spb_horizontal_elevator_door", BlockEntityTypes.SPB_HORIZONTAL_ELEVATOR_DOOR_TILE_ENTITY);
    }
}