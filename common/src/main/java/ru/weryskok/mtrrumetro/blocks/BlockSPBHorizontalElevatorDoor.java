package ru.weryskok.mtrrumetro.blocks;

import mtr.block.BlockPSDAPGDoorBase;
import mtr.block.BlockPSDAPGGlassEndBase;
import mtr.block.IBlock;
import mtr.mappings.BlockEntityMapper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import ru.weryskok.mtrrumetro.BlockEntityTypes;
import ru.weryskok.mtrrumetro.Items;

public class BlockSPBHorizontalElevatorDoor extends BlockPSDAPGDoorBase {
    boolean is_odd;

    public BlockSPBHorizontalElevatorDoor(boolean is_odd) {
        super();
        this.is_odd = is_odd;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor world, BlockPos pos, BlockPos posFrom) {
        if (is_odd){
            boolean isTop = IBlock.getStatePropertySafe(state, HALF) == DoubleBlockHalf.UPPER;
            return (isTop && direction == Direction.DOWN || !isTop && direction == Direction.UP) && !newState.is(this) ? Blocks.AIR.defaultBlockState() : state;
        }
        if (IBlock.getSideDirection(state) == direction && !newState.is(this)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            BlockState superState = super.updateShape(state, direction, newState, world, pos, posFrom);
            if (superState.getBlock() == Blocks.AIR) {
                return superState;
            } else {
                boolean end = world.getBlockState(pos.relative(IBlock.getSideDirection(state).getOpposite())).getBlock() instanceof BlockPSDAPGGlassEndBase;
                return (BlockState)superState.setValue(END, end);
            }
        }
    }

    @Override
    public BlockEntityMapper createBlockEntity(BlockPos pos, BlockState state) {
        return new TileEntitySPBHorizontalElevatorDoor(pos, state, is_odd);
    }

    @Override
    public Item asItem() {
        return (is_odd? Items.SPB_HORIZONTAL_ELEVATOR_DOOR_ODD : Items.SPB_HORIZONTAL_ELEVATOR_DOOR).get();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{END, FACING, HALF, SIDE, TEMP, UNLOCKED});
    }

    public static class TileEntitySPBHorizontalElevatorDoor extends TileEntityPSDAPGDoorBase {

        public TileEntitySPBHorizontalElevatorDoor(BlockPos pos, BlockState state, boolean is_odd) {
            super((is_odd ? BlockEntityTypes.SPB_HORIZONTAL_ELEVATOR_DOOR_ODD_TILE_ENTITY : BlockEntityTypes.SPB_HORIZONTAL_ELEVATOR_DOOR_TILE_ENTITY).get(), pos, state);
        }
    }
}
