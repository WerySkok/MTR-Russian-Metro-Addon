package ru.weryskok.mtrrumetro.mod.blocks;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockExtension;
import org.mtr.mapping.mapper.DirectionHelper;
import org.mtr.mapping.tool.HolderBase;
import org.mtr.mod.Blocks;
import org.mtr.mod.block.IBlock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class BlockTrainStopSign extends BlockExtension implements DirectionHelper {

    public BlockTrainStopSign() {
        super(Blocks.createDefaultBlockSettings(true).nonOpaque());
    }

    @Override
    public void addBlockProperties(List<HolderBase<?>> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getPlacementState2(ItemPlacementContext itemPlacementContext) {
        return this.getDefaultState2().with(new Property<>(FACING.data), getFacing(itemPlacementContext).data);
    }

    private static Direction getFacing(ItemPlacementContext context) {
        final Direction oppositeFace = context.getSide().getOpposite();
        if (oppositeFace.getOffsetY() == 0) {
            return oppositeFace.rotateYCounterclockwise();
        } else {
            return context.getPlayerFacing(); // TODO: checking for nearby blocks and rotating sign accordingly?
        }
    }

    @Nonnull
    @Override
    public VoxelShape getOutlineShape2(BlockState blockState, BlockView world, BlockPos pos, ShapeContext context) {
        return IBlock.getVoxelShapeByDirection(11.5, 0, 7, 16, 16, 9, IBlock.getStatePropertySafe(blockState, FACING));
    }
}
