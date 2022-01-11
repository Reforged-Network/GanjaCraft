package com.reforgednetwork.ganjacraft.block.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;

import java.util.function.Supplier;

public class VanillaCropsBlock extends CropsBlock {
	private Supplier<Item> seeds;

	public VanillaCropsBlock(Supplier<Item> seedsIn) {
		super(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.CROP));
		seeds = seedsIn;
	}

	/*
	 * Minecraft Methods
	 */

	@Override
	protected IItemProvider getSeedsItem() {
		return seeds.get();
	}

}
