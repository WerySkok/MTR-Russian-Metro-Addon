package ru.weryskok.mtrrumetro.mod.blocks;

import org.mtr.mapping.holder.*;
import org.mtr.mod.Blocks;
import org.mtr.mod.block.BlockTicketMachine;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;

public class BlockMoscowNewTicketMachine extends BlockTicketMachine {

	public BlockMoscowNewTicketMachine() {
		super(Blocks.createDefaultBlockSettings(true, state -> 5).nonOpaque());
	}

	@Nonnull
	@Override
	public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		Direction facing = IBlock.getStatePropertySafe(state, FACING);
		double height = IBlock.getStatePropertySafe(state, HALF) == DoubleBlockHalf.UPPER ? 11.8 : 16;
		return IBlock.getVoxelShapeByDirection(0, 0, 0, 16, height, 12.5, facing);
	}
}
