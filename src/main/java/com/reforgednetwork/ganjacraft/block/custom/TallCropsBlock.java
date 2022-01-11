package com.reforgednetwork.ganjacraft.block.custom;

import java.util.Random;
import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.Item;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

/**
 * Special one-off class for tall crops.
 * Created to handle crops which grow upwards beyond a single block.
 */
public class TallCropsBlock extends VanillaCropsBlock {
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	private static final VoxelShape[] SHAPE_BY_AGE_LOWER = new VoxelShape[] {
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D) };
	private static final VoxelShape[] SHAPE_BY_AGE_UPPER = new VoxelShape[] {
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 0.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 0.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 0.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 0.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D) };

	public TallCropsBlock(Supplier<Item> seedsIn) {
		super(seedsIn);
		this.setDefaultState(this.stateContainer.getBaseState().with(HALF, DoubleBlockHalf.LOWER).with(AGE, 0));
	}

	/*
	 * Minecraft Methods
	 */

	@Override
	public boolean isValidPosition(BlockState stateIn, IWorldReader worldIn, BlockPos posIn) {
		Boolean unobstructed;
		BlockPos ground = posIn.down();

		if (stateIn.get(HALF) == DoubleBlockHalf.LOWER) {
			if (stateIn.get(AGE) <= 3) {
				unobstructed = isAir(worldIn.getBlockState(posIn.up()));
			} else {
				if (worldIn.getBlockState(posIn.up()).getBlock() == this) {
					unobstructed = worldIn.getBlockState(posIn.up()).get(HALF) == DoubleBlockHalf.UPPER;
				} else {
					unobstructed = false;
				}
			}
			return worldIn.getBlockState(ground).canSustainPlant(worldIn, ground, Direction.UP, this) && unobstructed;
		} else {
			if (worldIn.getBlockState(ground).getBlock() == this) {
				return worldIn.getBlockState(ground).get(HALF) == DoubleBlockHalf.LOWER && worldIn.getBlockState(ground).get(AGE) >= 4;
			} else {
				return false;
			}
		}
	}

	@Override
	public VoxelShape getShape(BlockState stateIn, IBlockReader worldIn, BlockPos posIn, ISelectionContext contextIn) {
		if (stateIn.get(HALF) == DoubleBlockHalf.LOWER) {
			return SHAPE_BY_AGE_LOWER[stateIn.get(this.getAgeProperty())];
		} else {
			return SHAPE_BY_AGE_UPPER[stateIn.get(this.getAgeProperty())];
		}
	}

	@Override
	public void randomTick(BlockState stateIn, ServerWorld worldIn, BlockPos posIn, Random randomIn) {
		if (!worldIn.isAreaLoaded(posIn, 1))
			return;
		if (worldIn.getLightSubtracted(posIn, 0) >= 9) {
			int i = this.getAge(stateIn);
			if (i < this.getMaxAge()) {
				float f = getGrowthChance(this, worldIn, posIn);
				if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, posIn, stateIn,
						randomIn.nextInt((int) (25.0F / f) + 1) == 0)) {
					worldIn.setBlockState(posIn, this.withAge(i + 1).with(HALF, stateIn.get(HALF)), 2);

					if (stateIn.get(HALF) == DoubleBlockHalf.LOWER && worldIn.isAirBlock(posIn.up()) && i + 1 >= 4) {
						worldIn.setBlockState(posIn.up(), this.withAge(i + 1).with(HALF, DoubleBlockHalf.UPPER), 2);
					}

					if (worldIn.getBlockState(posIn.up()).getBlock() instanceof TallCropsBlock) {
						worldIn.setBlockState(posIn.up(), this.withAge(i + 1).with(HALF, DoubleBlockHalf.UPPER), 2);
					}

					net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, posIn, stateIn);
				}
			}
		}
	}

	@Override
	public boolean ticksRandomly(BlockState stateIn) {
		return !this.isMaxAge(stateIn) && stateIn.get(HALF) == DoubleBlockHalf.LOWER;
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builderIn) {
		builderIn.add(HALF);
		super.fillStateContainer(builderIn);
	}

	@Override
	public void grow(World worldIn, BlockPos posIn, BlockState stateIn) {
		int i = this.getAge(stateIn) + this.getBonemealAgeIncrease(worldIn);
		int j = this.getMaxAge();
		if (i > j) {
			i = j;
		}

		worldIn.setBlockState(posIn, this.withAge(i).with(HALF, stateIn.get(HALF)), 2);

		if (i >= 4 && worldIn.isAirBlock(posIn.up()) && stateIn.get(HALF) == DoubleBlockHalf.LOWER) {
			worldIn.setBlockState(posIn.up(), this.withAge(i).with(HALF, DoubleBlockHalf.UPPER), 2);
		}

		if (worldIn.getBlockState(posIn.up()).matchesBlock(this)) {
			worldIn.setBlockState(posIn.up(), this.withAge(i).with(HALF, DoubleBlockHalf.UPPER), 2);
		}

		if (stateIn.get(HALF) == DoubleBlockHalf.UPPER) {
			worldIn.setBlockState(posIn.down(), this.withAge(i).with(HALF, DoubleBlockHalf.LOWER), 2);
		}
	}
}
