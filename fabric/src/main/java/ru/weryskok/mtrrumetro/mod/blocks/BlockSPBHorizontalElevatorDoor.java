package ru.weryskok.mtrrumetro.mod.blocks;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityExtension;
import org.mtr.mapping.tool.HolderBase;
import org.mtr.mod.block.BlockPSDAPGDoorBase;
import org.mtr.mod.block.BlockPSDAPGGlassEndBase;
import org.mtr.mod.block.IBlock;
import ru.weryskok.mtrrumetro.mod.BlockEntityTypes;
import ru.weryskok.mtrrumetro.mod.Items;

import javax.annotation.Nonnull;
import java.util.List;

public class BlockSPBHorizontalElevatorDoor extends BlockPSDAPGDoorBase {

	private final boolean isOdd;

	public BlockSPBHorizontalElevatorDoor(boolean isOdd) {
		super();
		this.isOdd = isOdd;
	}

	@Nonnull
	@Override
	public BlockState getStateForNeighborUpdate2(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
		if (isOdd) {
			boolean isTop = IBlock.getStatePropertySafe(state, HALF) == DoubleBlockHalf.UPPER;
			return (isTop && direction == Direction.DOWN || !isTop && direction == Direction.UP) && !newState.isOf(new Block(this)) ? Blocks.getAirMapped().getDefaultState() : state;
		}
		if (IBlock.getSideDirection(state) == direction && !newState.isOf(new Block(this))) {
			return Blocks.getAirMapped().getDefaultState();
		} else {
			BlockState superState = super.getStateForNeighborUpdate2(state, direction, newState, world, pos, posFrom);
			if (superState.getBlock().data == Blocks.getAirMapped().data) {
				return superState;
			} else {
				boolean end = world.getBlockState(pos.offset(IBlock.getSideDirection(state).getOpposite())).getBlock().data instanceof BlockPSDAPGGlassEndBase;
				return superState.with(new Property<>(END.data), end);
			}
		}
	}

	@Nonnull
	@Override
	public BlockEntityExtension createBlockEntity(BlockPos pos, BlockState state) {
		return new BlockEntitySPBHorizontalElevatorDoor(pos, state, isOdd);
	}

	@Nonnull
	@Override
	public Item asItem2() {
		return (isOdd ? Items.SPB_HORIZONTAL_ELEVATOR_DOOR_ODD : Items.SPB_HORIZONTAL_ELEVATOR_DOOR).get();
	}

	@Override
	public void addBlockProperties(List<HolderBase<?>> builder) {
		builder.add(END);
		builder.add(FACING);
		builder.add(HALF);
		builder.add(SIDE);
		builder.add(UNLOCKED);
	}

	public static class BlockEntitySPBHorizontalElevatorDoor extends BlockPSDAPGDoorBase.BlockEntityBase {

		public BlockEntitySPBHorizontalElevatorDoor(BlockPos pos, BlockState state, boolean isOdd) {
			super((isOdd ? BlockEntityTypes.SPB_HORIZONTAL_ELEVATOR_DOOR_ODD_TILE_ENTITY : BlockEntityTypes.SPB_HORIZONTAL_ELEVATOR_DOOR_TILE_ENTITY).get(), pos, state);
		}
	}
}
