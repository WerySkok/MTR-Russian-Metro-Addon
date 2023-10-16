package ru.weryskok.mtrrumetro.render;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mtr.MTRClient;
import mtr.block.BlockPSDAPGDoorBase;
import mtr.block.IBlock;
import mtr.data.IGui;
import mtr.mappings.BlockEntityRendererMapper;
import mtr.mappings.ModelDataWrapper;
import mtr.mappings.ModelMapper;
import mtr.mappings.UtilitiesClient;
import mtr.render.RenderTrains;
import mtr.render.StoredMatrixTransformations;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import ru.weryskok.mtrrumetro.blocks.BlockSPBHorizontalElevatorDoor;

public class RenderSPBHorizontalElevatorDoor<T extends BlockPSDAPGDoorBase.TileEntityPSDAPGDoorBase> extends BlockEntityRendererMapper<T> implements IGui, IBlock {
    private static final ModelSingleCube MODEL_PSD = new ModelSingleCube(36, 18, 0, 0, 0, 16, 16, 2);
    private static final ModelSingleCube MODEL_PSD_DOOR_LOCKED = new ModelSingleCube(6, 6, 5, 6, 2.1f, 6, 6, 0);

    boolean is_odd;
    
    public RenderSPBHorizontalElevatorDoor(BlockEntityRenderDispatcher dispatcher, boolean is_odd) {
        super(dispatcher);
        this.is_odd = is_odd;
    }

    @Override
    public void render(T entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
        final Level world = entity.getLevel();
        if (world == null) {
            return;
        }

        final BlockPos pos = entity.getBlockPos();
        if (IBlock.getStatePropertySafe(world, pos, BlockSPBHorizontalElevatorDoor.TEMP)) {
            return;
        }

        final Direction facing = IBlock.getStatePropertySafe(world, pos, BlockSPBHorizontalElevatorDoor.FACING);
        final boolean side = IBlock.getStatePropertySafe(world, pos, BlockSPBHorizontalElevatorDoor.SIDE) == EnumSide.RIGHT;
        final boolean half = IBlock.getStatePropertySafe(world, pos, BlockSPBHorizontalElevatorDoor.HALF) == DoubleBlockHalf.UPPER;
        final boolean unlocked = IBlock.getStatePropertySafe(world, pos, BlockSPBHorizontalElevatorDoor.UNLOCKED);
        final float open = Math.min(entity.getOpen(MTRClient.getLastFrameDuration()), 1);

        final StoredMatrixTransformations storedMatrixTransformations = new StoredMatrixTransformations();
        storedMatrixTransformations.add(matricesNew -> {
            matricesNew.translate(0.5 + entity.getBlockPos().getX(), entity.getBlockPos().getY(), 0.5 + entity.getBlockPos().getZ());
            UtilitiesClient.rotateYDegrees(matricesNew, -facing.toYRot());
            UtilitiesClient.rotateXDegrees(matricesNew, 180);
        });
        if (is_odd) {
            final StoredMatrixTransformations rightStoredMatrixTransformations = storedMatrixTransformations.copy();

            rightStoredMatrixTransformations.add(matricesNew -> matricesNew.translate(open * (!side ? -1 : 1), 0, 0));
            rightStoredMatrixTransformations.add(matricesNew -> matricesNew.translate(-0.5, 0, 0));
            RenderTrains.scheduleRender(new ResourceLocation(String.format("russianmetro:textures/block/spb_horizontal_elevator_door_%s_%s.png", half ? "top" : "bottom", !side ? "right" : "left")), false, RenderTrains.QueuedRenderLayer.EXTERIOR, (matricesNew, vertexConsumer) -> {
                rightStoredMatrixTransformations.transform(matricesNew);
                MODEL_PSD.renderToBuffer(matricesNew, vertexConsumer, light, overlay, 1, 1, 1, 1);
                matricesNew.popPose();
            });

            if (half && !unlocked) {
                RenderTrains.scheduleRender(new ResourceLocation("mtr:textures/block/sign/door_not_in_use.png"), false, RenderTrains.QueuedRenderLayer.EXTERIOR, (matricesNew, vertexConsumer) -> {
                    rightStoredMatrixTransformations.transform(matricesNew);
                    MODEL_PSD_DOOR_LOCKED.renderToBuffer(matricesNew, vertexConsumer, light, overlay, 1, 1, 1, 1);
                    matricesNew.popPose();
                });
            }

            storedMatrixTransformations.add(matricesNew -> matricesNew.translate(0.5, 0, 0));
        }
        storedMatrixTransformations.add(matricesNew -> matricesNew.translate(open * (side ? -1 : 1), 0, 0));
        RenderTrains.scheduleRender(new ResourceLocation(String.format("russianmetro:textures/block/spb_horizontal_elevator_door_%s_%s.png", half ? "top" : "bottom", side ? "right" : "left")), false, RenderTrains.QueuedRenderLayer.EXTERIOR, (matricesNew, vertexConsumer) -> {
            storedMatrixTransformations.transform(matricesNew);
            MODEL_PSD.renderToBuffer(matricesNew, vertexConsumer, light, overlay, 1, 1, 1, 1);
            matricesNew.popPose();
        });

        if (half && !unlocked) {
            RenderTrains.scheduleRender(new ResourceLocation("mtr:textures/block/sign/door_not_in_use.png"), false, RenderTrains.QueuedRenderLayer.EXTERIOR, (matricesNew, vertexConsumer) -> {
                storedMatrixTransformations.transform(matricesNew);
                MODEL_PSD_DOOR_LOCKED.renderToBuffer(matricesNew, vertexConsumer, light, overlay, 1, 1, 1, 1);
                matricesNew.popPose();
            });
        }


    }

    @Override
    public boolean shouldRenderOffScreen(T blockEntity) {
        return true;
    }

    private static class ModelSingleCube extends EntityModel<Entity> {

        private final ModelMapper cube;

        private ModelSingleCube(int textureWidth, int textureHeight, float x, float y, float z, int length, int height, int depth) {
            final ModelDataWrapper modelDataWrapper = new ModelDataWrapper(this, textureWidth, textureHeight);
            cube = new ModelMapper(modelDataWrapper);
            cube.texOffs(0, 0).addBox(x - 8, y - 16, z - 8, length, height, depth, 0, false);
            modelDataWrapper.setModelPart(textureWidth, textureHeight);
            cube.setModelPart();
        }

        @Override
        public void renderToBuffer(PoseStack matrices, VertexConsumer vertices, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
            cube.render(matrices, vertices, 0, 0, 0, packedLight, packedOverlay);
        }

        @Override
        public void setupAnim(Entity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        }
    }

}
