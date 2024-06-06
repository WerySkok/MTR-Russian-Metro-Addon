package ru.weryskok.mtrrumetro.mod.blocks;

import org.mtr.mapping.holder.*;
import org.mtr.mod.block.BlockTicketBarrier;
import org.mtr.mod.block.IBlock;
import org.mtr.mod.data.TicketSystem;

import javax.annotation.Nonnull;

public class BlockMoscowNewTicketBarrier extends BlockTicketBarrier {

	public BlockMoscowNewTicketBarrier(boolean isEntrance) {
		super(isEntrance);
	}

	@Nonnull
	@Override
	public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		final Direction facing = IBlock.getStatePropertySafe(state, FACING);
		return IBlock.getVoxelShapeByDirection(12.4, 0, -3.6, 16, 18, 21.2, facing);
	}

	@Nonnull
	@Override
	public VoxelShape getCollisionShape2(BlockState state, BlockView world, BlockPos blockPos, ShapeContext context) {
		final Direction facing = IBlock.getStatePropertySafe(state, FACING);
		final TicketSystem.EnumTicketBarrierOpen open = IBlock.getStatePropertySafe(state, OPEN);
		final VoxelShape base = IBlock.getVoxelShapeByDirection(12.4, 0, -3.6, 16, 24, 21.2, facing);
		return BlockMoscowOldTicketBarrier.isOpen(open) ? base : VoxelShapes.union(IBlock.getVoxelShapeByDirection(0.0, 0.0, 7.0, 16.0, 24.0, 9.0, facing), base);
	}
}
