package ru.weryskok.mtrrumetro.items;

import mtr.block.IBlock;
import mtr.item.ItemWithCreativeTabBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import ru.weryskok.mtrrumetro.CreativeModeTabs;
import ru.weryskok.mtrrumetro.Blocks;
import ru.weryskok.mtrrumetro.blocks.*;

public class ItemSPBHorizontalElevatorDoor extends ItemWithCreativeTabBase implements IBlock  {


    public ItemSPBHorizontalElevatorDoor() {
        super(CreativeModeTabs.RUSSIAN_METRO_STUFF);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        final int horizontalBlocks = 2;
        if (blocksNotReplaceable(context, horizontalBlocks, 2, getBlockStateFromItem().getBlock())) {
            return InteractionResult.FAIL;
        }

        final Level world = context.getLevel();
        final Direction playerFacing = context.getHorizontalDirection();
        final BlockPos pos = context.getClickedPos().relative(context.getClickedFace());

        for (int x = 0; x < horizontalBlocks; x++) {
            final BlockPos newPos = pos.relative(playerFacing.getClockWise(), x);

            for (int y = 0; y < 2; y++) {
                final BlockState state = getBlockStateFromItem().setValue(BlockSPBHorizontalElevatorDoor.FACING, playerFacing).setValue(HALF, y == 1 ? DoubleBlockHalf.UPPER : DoubleBlockHalf.LOWER);
                BlockState newState = state.setValue(SIDE, x == 0 ? EnumSide.LEFT : EnumSide.RIGHT);
                world.setBlockAndUpdate(newPos.above(y), newState);
            }
        }

        context.getItemInHand().shrink(1);
        return InteractionResult.SUCCESS;
    }

    private BlockState getBlockStateFromItem() {
        return Blocks.SPB_HORIZONTAL_ELEVATOR_DOOR.get().defaultBlockState();
    }

    // The same implementation from MTR
    public static boolean blocksNotReplaceable(UseOnContext context, int width, int height, Block blacklistBlock) {
        final Direction facing = context.getHorizontalDirection();
        final Level world = context.getLevel();
        final BlockPos startingPos = context.getClickedPos().relative(context.getClickedFace());

        for (int x = 0; x < width; x++) {
            final BlockPos offsetPos = startingPos.relative(facing.getClockWise(), x);

            if (blacklistBlock != null) {
                final boolean isBlacklistedBelow = world.getBlockState(offsetPos.below()).is(blacklistBlock);
                final boolean isBlacklistedAbove = world.getBlockState(offsetPos.above(height)).is(blacklistBlock);
                if (isBlacklistedBelow || isBlacklistedAbove) {
                    return true;
                }
            }

            for (int y = 0; y < height; y++) {
                if (!world.getBlockState(offsetPos.above(y)).getMaterial().isReplaceable()) {
                    return true;
                }
            }
        }

        return false;
    }
}
