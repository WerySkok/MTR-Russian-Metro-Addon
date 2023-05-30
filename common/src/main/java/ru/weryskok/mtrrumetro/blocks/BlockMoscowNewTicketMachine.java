package ru.weryskok.mtrrumetro.blocks;

import mtr.block.BlockTicketMachine;
import mtr.block.IBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockMoscowNewTicketMachine extends BlockTicketMachine {

    public BlockMoscowNewTicketMachine(Properties settings) {
        super(settings);
    }

    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext) {
        Direction facing = IBlock.getStatePropertySafe(state, FACING);
        double height = IBlock.getStatePropertySafe(state, HALF) == DoubleBlockHalf.UPPER ? 11.8 : 16;
        return IBlock.getVoxelShapeByDirection(0, 0, 0, 16, height, 12.5, facing);
    }
}
