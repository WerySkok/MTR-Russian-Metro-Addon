package ru.weryskok.mtrrumetro.blocks;

import mtr.SoundEvents;
import mtr.block.BlockTicketBarrier;
import mtr.block.IBlock;
import mtr.data.TicketSystem;
import mtr.mappings.Utilities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BlockMoscowOldTicketBarrier extends BlockTicketBarrier {


    public enum EnumNormallyOpenedTicketBarrier implements StringRepresentable {
        NEUTRAL("neutral"), CLOSED("closed"), OPEN("open");
        private final String name;
        EnumNormallyOpenedTicketBarrier(String _name){
            name = _name;
        }
        @Override
        public String getSerializedName() {
            return name;
        }
    }

    private final boolean isEntrance;
    public static final EnumProperty<EnumNormallyOpenedTicketBarrier> NORMAL_OPEN = EnumProperty.create("normal_open", EnumNormallyOpenedTicketBarrier.class);
    public BlockMoscowOldTicketBarrier(boolean isEntrance) {
        super(isEntrance);
        this.isEntrance = isEntrance; // isEntrance is private in parent class
    }

    @Override
    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        if (!world.isClientSide && entity instanceof Player) {
            final Direction facing = IBlock.getStatePropertySafe(state, FACING);
            final Vec3 playerPosRotated = entity.position().subtract(pos.getX() + 0.5, 0, pos.getZ() + 0.5).yRot((float)Math.toRadians(facing.toYRot()));
            final TicketSystem.EnumTicketBarrierOpen open = IBlock.getStatePropertySafe(state, OPEN);

            if (open.isOpen() && playerPosRotated.z > 0) {
                world.setBlockAndUpdate(pos, state.setValue(OPEN, TicketSystem.EnumTicketBarrierOpen.CLOSED));
            } else if (!open.isOpen() && playerPosRotated.z < 0 && !world.getBlockTicks().hasScheduledTick(pos, this)) {
                final TicketSystem.EnumTicketBarrierOpen newOpen = TicketSystem.passThrough(world, pos, (Player)entity, isEntrance, !isEntrance, SoundEvents.TICKET_BARRIER, SoundEvents.TICKET_BARRIER_CONCESSIONARY, SoundEvents.TICKET_BARRIER, SoundEvents.TICKET_BARRIER_CONCESSIONARY, ru.weryskok.mtrrumetro.SoundEvents.MOSCOW_OLD_TICKET_BARRIER_FAIL, false);
                world.setBlockAndUpdate(pos, state.setValue(OPEN, newOpen));
                switch (newOpen){
                    case OPEN_CONCESSIONARY:
                    case OPEN:
                        world.setBlockAndUpdate(pos, state.setValue(NORMAL_OPEN, EnumNormallyOpenedTicketBarrier.OPEN));
                        break;
                    case CLOSED:
                        world.setBlockAndUpdate(pos, state.setValue(NORMAL_OPEN, EnumNormallyOpenedTicketBarrier.CLOSED));
                        entity.hurt(DamageSource.CRAMMING, 1);
                        break;
                }
                    Utilities.scheduleBlockTick(world, pos, this, 40);
            }
        }

    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos) {
        world.setBlockAndUpdate(pos, state.setValue(OPEN, TicketSystem.EnumTicketBarrierOpen.CLOSED));
        world.setBlockAndUpdate(pos, state.setValue(NORMAL_OPEN, EnumNormallyOpenedTicketBarrier.NEUTRAL));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return defaultBlockState().setValue(FACING, ctx.getHorizontalDirection()).setValue(OPEN, TicketSystem.EnumTicketBarrierOpen.CLOSED).setValue(NORMAL_OPEN, EnumNormallyOpenedTicketBarrier.NEUTRAL);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        return IBlock.getVoxelShapeByDirection(10.9, 0.0, -2.0, 16.0, 24.1, 19.5, facing);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        final Direction facing = IBlock.getStatePropertySafe(state, FACING);
        final EnumNormallyOpenedTicketBarrier normal_open = IBlock.getStatePropertySafe(state, NORMAL_OPEN);
        final VoxelShape base = IBlock.getVoxelShapeByDirection(10.9, 0.0, -2.0, 16.0, 24.1, 19.5, facing);
        return normal_open == EnumNormallyOpenedTicketBarrier.OPEN ? base : Shapes.or(IBlock.getVoxelShapeByDirection(0.0, 0.0, 3.5, 16.0, 24.0, 8.0, facing), base);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN, NORMAL_OPEN);
    }
}
