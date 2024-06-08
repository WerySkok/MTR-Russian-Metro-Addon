package ru.weryskok.mtrrumetro.mod.blocks;

import org.mtr.mapping.holder.*;
import org.mtr.mapping.tool.HolderBase;
import org.mtr.mod.block.BlockTicketBarrier;
import org.mtr.mod.block.IBlock;
import org.mtr.mod.data.TicketSystem;
import ru.weryskok.mtrrumetro.mod.SoundEvents;

import javax.annotation.Nonnull;
import java.util.List;

public class BlockMoscowOldTicketBarrier extends BlockTicketBarrier {

	public enum EnumNormallyOpenedTicketBarrier implements StringIdentifiable {
		NEUTRAL("neutral"), CLOSED("closed"), OPEN("open");

		private final String name;

		EnumNormallyOpenedTicketBarrier(String name) {
			this.name = name;
		}

		@Nonnull
		@Override
		public String asString2() {
			return name;
		}
	}

	private final boolean isEntrance;
	public static final EnumProperty<EnumNormallyOpenedTicketBarrier> NORMAL_OPEN = EnumProperty.of("normal_open", EnumNormallyOpenedTicketBarrier.class);

	public BlockMoscowOldTicketBarrier(boolean isEntrance) {
		super(isEntrance);
		this.isEntrance = isEntrance; // isEntrance is private in parent class
	}

	@Override
	public void onEntityCollision2(BlockState state, World world, BlockPos pos, Entity entity) {
		if (!world.isClient() && PlayerEntity.isInstance(entity)) {
			final Direction facing = IBlock.getStatePropertySafe(state, FACING);
			final Vector3d playerPosRotated = entity.getPos().subtract(pos.getX() + 0.5, 0, pos.getZ() + 0.5).rotateY((float) Math.toRadians(facing.asRotation()));
			final TicketSystem.EnumTicketBarrierOpen open = IBlock.getStatePropertySafe(state, new Property<>(OPEN.data));

			if (isOpen(open) && playerPosRotated.getZMapped() > 0) {
				world.setBlockState(pos, state.with(new Property<>(OPEN.data), TicketSystem.EnumTicketBarrierOpen.CLOSED));
			} else if (open == TicketSystem.EnumTicketBarrierOpen.CLOSED && playerPosRotated.getZMapped() < 0 && !hasScheduledTick(world, pos, new Block(this))) {
				final BlockPos posCopy = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
				world.setBlockState(posCopy, state.with(new Property<>(OPEN.data), TicketSystem.EnumTicketBarrierOpen.PENDING));
				TicketSystem.passThrough(
						world, posCopy, PlayerEntity.cast(entity),
						isEntrance, !isEntrance,
						org.mtr.mod.SoundEvents.TICKET_BARRIER.get(), org.mtr.mod.SoundEvents.TICKET_BARRIER_CONCESSIONARY.get(),
						org.mtr.mod.SoundEvents.TICKET_BARRIER.get(), org.mtr.mod.SoundEvents.TICKET_BARRIER_CONCESSIONARY.get(),
						SoundEvents.MOSCOW_OLD_TICKET_BARRIER_FAIL.get(),
						false, newOpen -> {
							world.setBlockState(posCopy, state.with(new Property<>(OPEN.data), newOpen));
							switch (newOpen) {
								case OPEN_CONCESSIONARY:
								case OPEN:
									world.setBlockState(posCopy, state.with(new Property<>(NORMAL_OPEN.data), EnumNormallyOpenedTicketBarrier.OPEN));
									break;
								case CLOSED:
									world.setBlockState(posCopy, state.with(new Property<>(NORMAL_OPEN.data), EnumNormallyOpenedTicketBarrier.CLOSED));
//                        entity.hurt(DamageSource.CRAMMING, 1);
									break;
							}
						}
				);
				scheduleBlockTick(world, posCopy, new Block(this), 40);
			}
		}

	}

	@Override
	public void scheduledTick2(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		world.setBlockState(pos, state.with(new Property<>(OPEN.data), TicketSystem.EnumTicketBarrierOpen.CLOSED));
		world.setBlockState(pos, state.with(new Property<>(NORMAL_OPEN.data), EnumNormallyOpenedTicketBarrier.NEUTRAL));
	}

	@Override
	public BlockState getPlacementState2(ItemPlacementContext ctx) {
		return getDefaultState2().with(new Property<>(FACING.data), ctx.getPlayerFacing().data).with(new Property<>(OPEN.data), TicketSystem.EnumTicketBarrierOpen.CLOSED).with(new Property<>(NORMAL_OPEN.data), EnumNormallyOpenedTicketBarrier.NEUTRAL);
	}

	@Nonnull
	@Override
	public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		final Direction facing = IBlock.getStatePropertySafe(state, FACING);
		return IBlock.getVoxelShapeByDirection(10.9, 0.0, -2.0, 16.0, 24.1, 19.5, facing);
	}

	@Nonnull
	@Override
	public VoxelShape getCollisionShape2(BlockState state, BlockView world, BlockPos blockPos, ShapeContext context) {
		final Direction facing = IBlock.getStatePropertySafe(state, FACING);
		final EnumNormallyOpenedTicketBarrier normal_open = IBlock.getStatePropertySafe(state, NORMAL_OPEN);
		final VoxelShape base = IBlock.getVoxelShapeByDirection(10.9, 0.0, -2.0, 16.0, 24.1, 19.5, facing);
		return normal_open == EnumNormallyOpenedTicketBarrier.OPEN ? base : VoxelShapes.union(IBlock.getVoxelShapeByDirection(0.0, 0.0, 3.5, 16.0, 24.0, 8.0, facing), base);
	}

	@Override
	public void addBlockProperties(List<HolderBase<?>> builder) {
		builder.add(FACING);
		builder.add(OPEN);
		builder.add(NORMAL_OPEN);
	}

	public static boolean isOpen(TicketSystem.EnumTicketBarrierOpen open) {
		return open == TicketSystem.EnumTicketBarrierOpen.OPEN || open == TicketSystem.EnumTicketBarrierOpen.OPEN_CONCESSIONARY;
	}
}
