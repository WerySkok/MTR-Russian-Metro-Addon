package ru.weryskok.mtrrumetro.blocks;

import mtr.block.IBlock;
import mtr.mappings.BlockDirectionalMapper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockMoscowOldInfoSosStand extends BlockDirectionalMapper implements IBlock {
    public BlockMoscowOldInfoSosStand() {
        super(Properties.of(Material.METAL, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(2).noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext) {
        double height = IBlock.getStatePropertySafe(state, THIRD) == EnumThird.UPPER ? 9.5 : 16;
        return IBlock.getVoxelShapeByDirection(2, 0, 6.7, 14, height, 9.3, IBlock.getStatePropertySafe(state, FACING));
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (!world.isClientSide) {
            final Direction facing = IBlock.getStatePropertySafe(state, FACING);
            world.setBlock(pos.above(), defaultBlockState().setValue(FACING, facing).setValue(THIRD, EnumThird.MIDDLE), 3);
            world.setBlock(pos.above(2), defaultBlockState().setValue(FACING, facing).setValue(THIRD, EnumThird.UPPER), 3);
            world.updateNeighborsAt(pos, Blocks.AIR);
            state.updateNeighbourShapes(world, pos, 3);
        }
    }

    @Override
    public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        switch (IBlock.getStatePropertySafe(state, THIRD)) {
            case MIDDLE:
                IBlock.onBreakCreative(world, player, pos.below());
                break;
            case UPPER:
                IBlock.onBreakCreative(world, player, pos.below(2));
                break;
        }
        super.playerWillDestroy(world, pos, state, player);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor world, BlockPos pos, BlockPos posFrom) {
        if ((direction == Direction.UP && IBlock.getStatePropertySafe(state, THIRD) != EnumThird.UPPER || direction == Direction.DOWN && IBlock.getStatePropertySafe(state, THIRD) != EnumThird.LOWER) && !newState.is(this)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            return state;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return IBlock.isReplaceable(ctx, Direction.UP, 3) ? defaultBlockState().setValue(FACING, ctx.getHorizontalDirection()).setValue(THIRD, EnumThird.LOWER) : null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, THIRD);
    }
}
