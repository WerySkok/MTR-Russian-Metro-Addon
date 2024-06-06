package ru.weryskok.mtrrumetro.mod.items;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.ItemExtension;
import org.mtr.mapping.registry.BlockRegistryObject;
import org.mtr.mod.block.IBlock;
import org.mtr.mod.item.ItemPSDAPGBase;
import ru.weryskok.mtrrumetro.mod.Blocks;
import ru.weryskok.mtrrumetro.mod.blocks.BlockSPBHorizontalElevatorDoor;

import javax.annotation.Nonnull;

public class ItemSPBHorizontalElevatorDoor extends ItemExtension implements IBlock {

	private final boolean isOdd;

	public ItemSPBHorizontalElevatorDoor(ItemSettings itemSettings, boolean isOdd) {
		super(itemSettings);
		this.isOdd = isOdd;
	}

	@Nonnull
	@Override
	public ActionResult useOnBlock2(ItemUsageContext context) {
		final int horizontalBlocks = isOdd ? 1 : 2;
		if (ItemPSDAPGBase.blocksNotReplaceable(context, horizontalBlocks, 2, getBlockStateFromItem().getBlock())) {
			return ActionResult.FAIL;
		}

		final World world = context.getWorld();
		final Direction playerFacing = context.getPlayerFacing();
		final BlockPos pos = context.getBlockPos().offset(context.getSide());

		for (int x = 0; x < horizontalBlocks; x++) {
			final BlockPos newPos = pos.offset(playerFacing.rotateYClockwise(), x);

			for (int y = 0; y < 2; y++) {
				BlockState state = getBlockStateFromItem().with(new Property<>(BlockSPBHorizontalElevatorDoor.FACING.data), playerFacing.data).with(new Property<>(HALF.data), y == 1 ? DoubleBlockHalf.UPPER : DoubleBlockHalf.LOWER);
				if (!isOdd) {
					state = state.with(new Property<>(SIDE.data), x == 0 ? EnumSide.LEFT : EnumSide.RIGHT);
				}
				world.setBlockState(newPos.up(y), state);
			}
		}

		context.getStack().decrement(1);
		return ActionResult.SUCCESS;
	}

	private BlockState getBlockStateFromItem() {
		BlockRegistryObject block = isOdd ? Blocks.SPB_HORIZONTAL_ELEVATOR_DOOR_ODD : Blocks.SPB_HORIZONTAL_ELEVATOR_DOOR;
		return block.get().getDefaultState();
	}
}
