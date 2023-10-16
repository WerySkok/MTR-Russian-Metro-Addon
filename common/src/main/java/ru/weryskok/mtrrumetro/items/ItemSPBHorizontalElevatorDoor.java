package ru.weryskok.mtrrumetro.items;

import mtr.RegistryObject;
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

    boolean is_odd;

    public ItemSPBHorizontalElevatorDoor(boolean is_odd) {
        super(CreativeModeTabs.RUSSIAN_METRO_STUFF);
        this.is_odd = is_odd;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        final int horizontalBlocks = is_odd ? 1 : 2;
        if (blocksNotReplaceable(context, horizontalBlocks, 2, getBlockStateFromItem().getBlock())) {
            return InteractionResult.FAIL;
        }

        final Level world = context.getLevel();
        final Direction playerFacing = context.getHorizontalDirection();
        final BlockPos pos = context.getClickedPos().relative(context.getClickedFace());

        for (int x = 0; x < horizontalBlocks; x++) {
            final BlockPos newPos = pos.relative(playerFacing.getClockWise(), x);

            for (int y = 0; y < 2; y++) {
                BlockState state = getBlockStateFromItem().setValue(BlockSPBHorizontalElevatorDoor.FACING, playerFacing).setValue(HALF, y == 1 ? DoubleBlockHalf.UPPER : DoubleBlockHalf.LOWER);
                if (!is_odd) {
                    state = state.setValue(SIDE, x == 0 ? EnumSide.LEFT : EnumSide.RIGHT);
                }
                world.setBlockAndUpdate(newPos.above(y), state);
            }
        }

        context.getItemInHand().shrink(1);
        return InteractionResult.SUCCESS;
    }

    private BlockState getBlockStateFromItem() {
        RegistryObject<Block> block = is_odd ? Blocks.SPB_HORIZONTAL_ELEVATOR_DOOR_ODD : Blocks.SPB_HORIZONTAL_ELEVATOR_DOOR;
        return block.get().defaultBlockState();
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
