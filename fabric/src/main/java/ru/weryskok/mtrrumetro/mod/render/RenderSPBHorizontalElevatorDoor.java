package ru.weryskok.mtrrumetro.mod.render;


import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockEntityRenderer;
import org.mtr.mapping.mapper.EntityModelExtension;
import org.mtr.mapping.mapper.GraphicsHolder;
import org.mtr.mapping.mapper.ModelPartExtension;
import org.mtr.mod.block.IBlock;
import org.mtr.mod.data.IGui;
import org.mtr.mod.render.MainRenderer;
import org.mtr.mod.render.QueuedRenderLayer;
import org.mtr.mod.render.StoredMatrixTransformations;
import ru.weryskok.mtrrumetro.mod.blocks.BlockSPBHorizontalElevatorDoor;

public class RenderSPBHorizontalElevatorDoor extends BlockEntityRenderer<BlockSPBHorizontalElevatorDoor.BlockEntitySPBHorizontalElevatorDoor> implements IGui, IBlock {

	private static final ModelSingleCube MODEL_PSD = new ModelSingleCube(36, 18, 0, 0, 0, 16, 16, 2);
	private static final ModelSingleCube MODEL_PSD_DOOR_LOCKED = new ModelSingleCube(6, 6, 5, 6, 2.1f, 6, 6, 0);

	private final boolean isOdd;

	public RenderSPBHorizontalElevatorDoor(Argument dispatcher, boolean isOdd) {
		super(dispatcher);
		this.isOdd = isOdd;
	}

	@Override
	public void render(BlockSPBHorizontalElevatorDoor.BlockEntitySPBHorizontalElevatorDoor entity, float tickDelta, GraphicsHolder graphicsHolder, int light, int overlay) {
		final World world = entity.getWorld2();
		if (world == null) {
			return;
		}

		final BlockPos pos = entity.getPos2();
		final Direction facing = IBlock.getStatePropertySafe(world, pos, BlockSPBHorizontalElevatorDoor.FACING);
		final boolean side = IBlock.getStatePropertySafe(world, pos, BlockSPBHorizontalElevatorDoor.SIDE) == EnumSide.RIGHT;
		final boolean half = IBlock.getStatePropertySafe(world, pos, BlockSPBHorizontalElevatorDoor.HALF) == DoubleBlockHalf.UPPER;
		final boolean unlocked = IBlock.getStatePropertySafe(world, pos, BlockSPBHorizontalElevatorDoor.UNLOCKED);
		final double open = Math.min(entity.getDoorValue(), 1);

		final StoredMatrixTransformations storedMatrixTransformations = new StoredMatrixTransformations(0.5 + entity.getPos2().getX(), entity.getPos2().getY(), 0.5 + entity.getPos2().getZ());
		storedMatrixTransformations.add(graphicsHolderNew -> {
			graphicsHolderNew.rotateYDegrees(-facing.asRotation());
			graphicsHolderNew.rotateXDegrees(180);
		});

		if (isOdd) {
			final StoredMatrixTransformations rightStoredMatrixTransformations = storedMatrixTransformations.copy();

			rightStoredMatrixTransformations.add(matricesNew -> matricesNew.translate(open * (!side ? -1 : 1), 0, 0));
			rightStoredMatrixTransformations.add(matricesNew -> matricesNew.translate(-0.5, 0, 0));
			MainRenderer.scheduleRender(new Identifier(String.format("russianmetro:textures/block/spb_horizontal_elevator_door_%s_%s.png", half ? "top" : "bottom", !side ? "right" : "left")), false, QueuedRenderLayer.EXTERIOR, (graphicsHolderNew, offset) -> {
				rightStoredMatrixTransformations.transform(graphicsHolderNew, offset);
				MODEL_PSD.render(graphicsHolderNew, light, overlay, 1, 1, 1, 1);
				graphicsHolderNew.pop();
			});

			if (half && !unlocked) {
				MainRenderer.scheduleRender(new Identifier("mtr:textures/block/sign/door_not_in_use.png"), false, QueuedRenderLayer.EXTERIOR, (graphicsHolderNew, offset) -> {
					rightStoredMatrixTransformations.transform(graphicsHolderNew, offset);
					MODEL_PSD_DOOR_LOCKED.render(graphicsHolderNew, light, overlay, 1, 1, 1, 1);
					graphicsHolderNew.pop();
				});
			}

			storedMatrixTransformations.add(matricesNew -> matricesNew.translate(0.5, 0, 0));
		}
		storedMatrixTransformations.add(matricesNew -> matricesNew.translate(open * (side ? -1 : 1), 0, 0));
		MainRenderer.scheduleRender(new Identifier(String.format("russianmetro:textures/block/spb_horizontal_elevator_door_%s_%s.png", half ? "top" : "bottom", side ? "right" : "left")), false, QueuedRenderLayer.EXTERIOR, (graphicsHolderNew, offset) -> {
			storedMatrixTransformations.transform(graphicsHolderNew, offset);
			MODEL_PSD.render(graphicsHolderNew, light, overlay, 1, 1, 1, 1);
			graphicsHolderNew.pop();
		});

		if (half && !unlocked) {
			MainRenderer.scheduleRender(new Identifier("mtr:textures/block/sign/door_not_in_use.png"), false, QueuedRenderLayer.EXTERIOR, (graphicsHolderNew, offset) -> {
				storedMatrixTransformations.transform(graphicsHolderNew, offset);
				MODEL_PSD_DOOR_LOCKED.render(graphicsHolderNew, light, overlay, 1, 1, 1, 1);
				graphicsHolderNew.pop();
			});
		}


	}

	@Override
	public boolean rendersOutsideBoundingBox2(BlockSPBHorizontalElevatorDoor.BlockEntitySPBHorizontalElevatorDoor blockEntity) {
		return true;
	}

	private static class ModelSingleCube extends EntityModelExtension<EntityAbstractMapping> {

		private final ModelPartExtension cube;

		private ModelSingleCube(int textureWidth, int textureHeight, float x, float y, float z, int length, int height, int depth) {
			super(textureWidth, textureHeight);
			cube = createModelPart();
			cube.setTextureUVOffset(0, 0).addCuboid(x - 8, y - 16, z - 8, length, height, depth, 0, false);
			buildModel();
		}

		@Override
		public void render(GraphicsHolder graphicsHolder, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
			cube.render(graphicsHolder, 0, 0, 0, packedLight, packedOverlay);
		}

		@Override
		public void setAngles2(EntityAbstractMapping entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		}
	}

}
