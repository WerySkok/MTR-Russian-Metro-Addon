package ru.weryskok.mtrrumetro.mod.blocks;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockExtension;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.DirectionHelper;
import org.mtr.mapping.tool.HolderBase;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BlockMoscowOldInfoSosStand extends BlockExtension implements DirectionHelper, IBlock {

	public BlockMoscowOldInfoSosStand() {
		super(BlockHelper.createBlockSettings(true).strength(2).nonOpaque());
	}

	@Nonnull
	@Override
	public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		double height = IBlock.getStatePropertySafe(state, THIRD) == EnumThird.UPPER ? 9.5 : 16;
		return IBlock.getVoxelShapeByDirection(2, 0, 6.7, 14, height, 9.3, IBlock.getStatePropertySafe(state, FACING));
	}

	@Override
	public void onPlaced2(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
		if (!world.isClient()) {
			final Direction facing = IBlock.getStatePropertySafe(state, FACING);
			world.setBlockState(pos.up(), getDefaultState2().with(new Property<>(FACING.data), facing.data).with(new Property<>(THIRD.data), EnumThird.MIDDLE), 3);
			world.setBlockState(pos.up(2), getDefaultState2().with(new Property<>(FACING.data), facing.data).with(new Property<>(THIRD.data), EnumThird.UPPER), 3);
			world.updateNeighbors(pos, Blocks.getAirMapped());
			state.updateNeighbors(new WorldAccess(world.data), pos, 3);
		}
	}

	@Override
	public void onBreak2(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		switch (IBlock.getStatePropertySafe(state, THIRD)) {
			case MIDDLE:
				IBlock.onBreakCreative(world, player, pos.down());
				break;
			case UPPER:
				IBlock.onBreakCreative(world, player, pos.down(2));
				break;
		}
		super.onBreak2(world, pos, state, player);
	}

	@Nonnull
	@Override
	public BlockState getStateForNeighborUpdate2(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if ((direction == Direction.UP && IBlock.getStatePropertySafe(state, THIRD) != EnumThird.UPPER || direction == Direction.DOWN && IBlock.getStatePropertySafe(state, THIRD) != EnumThird.LOWER) && !newState.isOf(new Block(this))) {
			return Blocks.getAirMapped().getDefaultState();
		} else {
			return state;
		}
	}

	@Override
	public BlockState getPlacementState2(ItemPlacementContext ctx) {
		return IBlock.isReplaceable(ctx, Direction.UP, 3) ? getDefaultState2().with(new Property<>(FACING.data), ctx.getPlayerFacing().data).with(new Property<>(THIRD.data), EnumThird.LOWER) : null;
	}

	@Override
	public void addBlockProperties(List<HolderBase<?>> builder) {
		builder.add(FACING);
		builder.add(THIRD);
	}
}
