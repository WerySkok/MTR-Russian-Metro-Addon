package ru.weryskok.mtrrumetro.blocks;

import mtr.block.BlockPSDAPGDoorBase;
import mtr.mappings.BlockEntityMapper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import ru.weryskok.mtrrumetro.BlockEntityTypes;
import ru.weryskok.mtrrumetro.Items;

public class BlockSPBHorizontalElevatorDoor extends BlockPSDAPGDoorBase {

    public BlockSPBHorizontalElevatorDoor() {
        super();
    }

    @Override
    public BlockEntityMapper createBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntitySPBHorizontalElevatorDoor(pos, state);
    }

    @Override
    public Item asItem() {
        return Items.SPB_HORIZONTAL_ELEVATOR_DOOR.get();
    }

    public static class TileEntitySPBHorizontalElevatorDoor extends TileEntityPSDAPGDoorBase {

        public TileEntitySPBHorizontalElevatorDoor(BlockPos pos, BlockState state) {
            super(BlockEntityTypes.SPB_HORIZONTAL_ELEVATOR_DOOR_TILE_ENTITY.get(), pos, state);
        }
    }
}
