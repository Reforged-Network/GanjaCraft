package com.reforgednetwork.ganjacraft.block.custom;

import com.reforgednetwork.ganjacraft.util.TrippleBlockCrop;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;
import java.util.function.Supplier;

public class TrippleBlock extends VanillaCropsBlock {
	public static final EnumProperty<TrippleBlockCrop> TRIPPLE = EnumProperty.create("tripple", TrippleBlockCrop.class);

	private static final VoxelShape[] SHAPE_BY_AGE_LOWER = new VoxelShape[]{
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

	private static final VoxelShape[] SHAPE_BY_AGE_MIDDLE = new VoxelShape[]{
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};
	private static final VoxelShape[] SHAPE_BY_AGE_UPPER = new VoxelShape[]{
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 0.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 0.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 0.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 0.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};

	public TrippleBlock(Supplier<Item> seedsIn) {
		super(seedsIn);
		this.setDefaultState(this.stateContainer.getBaseState().with(TRIPPLE, TrippleBlockCrop.LOWER).with(AGE, 0));
	}

	@Override
	public boolean isValidPosition(BlockState stateIn, IWorldReader worldIn, BlockPos posIn) {
		Boolean unobstructed;
		BlockPos ground = posIn.down();

		if (stateIn.get(TRIPPLE) == TrippleBlockCrop.LOWER) {
			if (stateIn.get(AGE) <= 3) {
				unobstructed = isAir(worldIn.getBlockState(posIn.up()));
			} else {
				if (worldIn.getBlockState(posIn.up()).getBlock() == this) {
					unobstructed = worldIn.getBlockState(posIn.up()).get(TRIPPLE) == TrippleBlockCrop.MIDDLE;
					unobstructed = unobstructed || worldIn.getBlockState(posIn.up(2)).get(TRIPPLE) == TrippleBlockCrop.UPPER;
				} else {
					unobstructed = false;
				}
			}

			return worldIn.getBlockState(ground).canSustainPlant(worldIn, ground, Direction.UP, this) && unobstructed;

		} else if (stateIn.get(TRIPPLE) == TrippleBlockCrop.MIDDLE) {
			if (worldIn.getBlockState(ground).getBlock() == this) {
				return worldIn.getBlockState(ground).get(TRIPPLE) == TrippleBlockCrop.LOWER && worldIn.getBlockState(ground).get(AGE) >= 4;
			} else {
				return false;
			}

		} else {
			if (worldIn.getBlockState(posIn).getBlock() == this) {
				return worldIn.getBlockState(posIn).get(TRIPPLE) == TrippleBlockCrop.MIDDLE && worldIn.getBlockState(posIn).get(AGE) >= 4;
			} else {
				return false;
			}
		}
	}

	@Override
	public VoxelShape getShape(BlockState stateIn, IBlockReader worldIn, BlockPos posIn, ISelectionContext contextIn) {
		if (stateIn.get(TRIPPLE) == TrippleBlockCrop.LOWER) {
			return SHAPE_BY_AGE_LOWER[stateIn.get(this.getAgeProperty())];
		} else if (stateIn.get(TRIPPLE) == TrippleBlockCrop.MIDDLE){
			return SHAPE_BY_AGE_MIDDLE[stateIn.get(this.getAgeProperty())];
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
					worldIn.setBlockState(posIn, this.withAge(i + 1).with(TRIPPLE, stateIn.get(TRIPPLE)), 2);

					if (stateIn.get(TRIPPLE) == TrippleBlockCrop.LOWER && worldIn.isAirBlock(posIn.up()) && i + 1 >= 4) {
						worldIn.setBlockState(posIn.up(), this.withAge(i + 1).with(TRIPPLE, TrippleBlockCrop.MIDDLE), 2);
					}

					if (worldIn.getBlockState(posIn.up()).getBlock() instanceof TrippleBlock) {
						worldIn.setBlockState(posIn.up(), this.withAge(i + 1).with(TRIPPLE, TrippleBlockCrop.MIDDLE), 2);
					}

					if (stateIn.get(TRIPPLE) == TrippleBlockCrop.MIDDLE && worldIn.isAirBlock(posIn.up()) && i + 1 >= 4) {
						worldIn.setBlockState(posIn.up(), this.withAge(i + 1).with(TRIPPLE, TrippleBlockCrop.UPPER), 2);
					}

					if (worldIn.getBlockState(posIn.up()).getBlock() instanceof TrippleBlock) {
						worldIn.setBlockState(posIn.up(), this.withAge(i + 1).with(TRIPPLE, TrippleBlockCrop.UPPER), 2);
					}

					net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, posIn, stateIn);
				}
			}
		}
	}

	@Override
	public boolean ticksRandomly(BlockState stateIn) {
		return !this.isMaxAge(stateIn) && (stateIn.get(TRIPPLE) == TrippleBlockCrop.LOWER || stateIn.get(TRIPPLE) == TrippleBlockCrop.MIDDLE);
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builderIn) {
		builderIn.add(TRIPPLE);
		super.fillStateContainer(builderIn);
	}

	@Override
	public void grow(World worldIn, BlockPos posIn, BlockState stateIn) {
		int i = this.getAge(stateIn) + this.getBonemealAgeIncrease(worldIn);
		int j = this.getMaxAge();
		if (i > j) {
			i = j;
		}

		worldIn.setBlockState(posIn, this.withAge(i).with(TRIPPLE, stateIn.get(TRIPPLE)), 2);

		if (i >= 4 && worldIn.isAirBlock(posIn.up()) && stateIn.get(TRIPPLE) == TrippleBlockCrop.LOWER) {
			worldIn.setBlockState(posIn.up(), this.withAge(i).with(TRIPPLE, TrippleBlockCrop.MIDDLE), 2);
		}

		if (worldIn.getBlockState(posIn.up()).matchesBlock(this)) {
			worldIn.setBlockState(posIn.up(), this.withAge(i).with(TRIPPLE, TrippleBlockCrop.MIDDLE), 2);
		}

		if (stateIn.get(TRIPPLE) == TrippleBlockCrop.MIDDLE) {
			worldIn.setBlockState(posIn.down(), this.withAge(i).with(TRIPPLE, TrippleBlockCrop.LOWER), 2);
		}



		if (i >= 4 && worldIn.isAirBlock(posIn.up()) && stateIn.get(TRIPPLE) == TrippleBlockCrop.MIDDLE) {
			worldIn.setBlockState(posIn.up(), this.withAge(i).with(TRIPPLE, TrippleBlockCrop.UPPER), 2);
		}

		if (worldIn.getBlockState(posIn.up()).matchesBlock(this)) {
			worldIn.setBlockState(posIn.up(), this.withAge(i).with(TRIPPLE, TrippleBlockCrop.UPPER), 2);
		}

		if (stateIn.get(TRIPPLE) == TrippleBlockCrop.UPPER) {
			worldIn.setBlockState(posIn.down(), this.withAge(i).with(TRIPPLE, TrippleBlockCrop.MIDDLE), 2);
		}
	}
}