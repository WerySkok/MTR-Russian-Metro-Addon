package ru.weryskok.mtrrumetro;

import mtr.RegistryObject;
import mtr.mappings.BlockEntityMapper;
import mtr.mappings.RegistryUtilities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import ru.weryskok.mtrrumetro.blocks.*;

import java.util.function.BiConsumer;

public class BlockEntityTypes {
    public final BiConsumer<String, RegistryObject<? extends BlockEntityType<? extends BlockEntityMapper>>> registerBlockEntityType;
    public static final RegistryObject<BlockEntityType<BlockSPBHorizontalElevatorDoor.TileEntitySPBHorizontalElevatorDoor>> SPB_HORIZONTAL_ELEVATOR_DOOR_TILE_ENTITY = new RegistryObject<>(() -> {
        return RegistryUtilities.getBlockEntityType((BlockPos pos, BlockState state) -> new BlockSPBHorizontalElevatorDoor.TileEntitySPBHorizontalElevatorDoor(pos, state, false), Blocks.SPB_HORIZONTAL_ELEVATOR_DOOR.get());
    });
    public static final RegistryObject<BlockEntityType<BlockSPBHorizontalElevatorDoor.TileEntitySPBHorizontalElevatorDoor>> SPB_HORIZONTAL_ELEVATOR_DOOR_ODD_TILE_ENTITY = new RegistryObject<>(() -> {
        return RegistryUtilities.getBlockEntityType((BlockPos pos, BlockState state) -> new BlockSPBHorizontalElevatorDoor.TileEntitySPBHorizontalElevatorDoor(pos, state, true), Blocks.SPB_HORIZONTAL_ELEVATOR_DOOR_ODD.get());
    });

    public BlockEntityTypes(BiConsumer<String, RegistryObject<? extends BlockEntityType<? extends BlockEntityMapper>>> registerBlockEntityType) {
        this.registerBlockEntityType = registerBlockEntityType;
    }

    public void registerBlockEntites(){
        registerBlockEntityType.accept("spb_horizontal_elevator_door", BlockEntityTypes.SPB_HORIZONTAL_ELEVATOR_DOOR_TILE_ENTITY);
        registerBlockEntityType.accept("spb_horizontal_elevator_door_odd", BlockEntityTypes.SPB_HORIZONTAL_ELEVATOR_DOOR_ODD_TILE_ENTITY);
    }
}