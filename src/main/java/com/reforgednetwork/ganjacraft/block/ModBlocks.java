package com.reforgednetwork.ganjacraft.block;

import com.reforgednetwork.ganjacraft.GanjaCraft;
import com.reforgednetwork.ganjacraft.block.custom.*;
import com.reforgednetwork.ganjacraft.item.ModItemGroup;
import com.reforgednetwork.ganjacraft.item.ModItems;
import com.reforgednetwork.ganjacraft.util.TrippleBlockCrop;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {

	public static final DeferredRegister<Block> BLOCKS
			= DeferredRegister.create(ForgeRegistries.BLOCKS, GanjaCraft.MOD_ID);

	public static final RegistryObject<Block> SATIVA_CROP = BLOCKS.register("sativa_crop", () -> new TallCropsBlock(ModItems.SATIVA_SEED));
//	public static final RegistryObject<Block> TEST_CROP = BLOCKS.register("test_crop", () -> new TrippleBlock(ModItems.COOKED_SEED));
	public static final RegistryObject<Block> INDICA_CROP = BLOCKS.register("indica_crop", () -> new TallCropsBlock(ModItems.INDICA_SEED));
	public static final RegistryObject<Block> HEMP_BLOCK = registerBlock("hemp_block", () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.YELLOW).hardnessAndResistance(0.5F).sound(SoundType.PLANT)));
	public static final RegistryObject<Block> HEMP_PLANKS = registerBlock("hemp_planks", () -> new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> HEMP_BRICKS = registerBlock("hemp_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F)));
	public static final RegistryObject<Block> HEMPCRETEBLOCK = registerBlock("hempcrete_block", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, DyeColor.BLACK).setRequiresTool().hardnessAndResistance(1.8F)));
	public static final RegistryObject<Block> HEMPCRETE_POWDER = registerBlock("hempcrete_powder", () -> new ConcretePowderBlock(HEMPCRETEBLOCK.get(), AbstractBlock.Properties.create(Material.SAND).hardnessAndResistance(0.5F).sound(SoundType.SAND)));
	public static final RegistryObject<Block> HEMP_STAIRS = registerBlock("hemp_stairs", () -> new StairsBlock(() -> HEMP_PLANKS.get().getDefaultState(), AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2.0F, 3.0F)));
	public static final RegistryObject<Block> HEMP_BRICK_STAIRS = registerBlock("hemp_brick_stairs", () -> new StairsBlock(() -> HEMP_BRICKS.get().getDefaultState(), AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F)));
	public static final RegistryObject<Block> HEMP_BRICK_SLAB = registerBlock("hemp_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F)));
	public static final RegistryObject<Block> HEMP_PLANK_SLAB = registerBlock("hemp_plank_slab", () -> new SlabBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F)));
	public static final RegistryObject<Block> HEMP_BRICK_WALL = registerBlock("hemp_brick_wall", () -> new WallBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F)));
	public static final RegistryObject<Block> HEMP_FENCE = registerBlock("hemp_fence", () -> new FenceBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F)));
	public static final RegistryObject<Block> HEMP_FENCE_GATE = registerBlock("hemp_fence_gate", () -> new FenceGateBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(1.5F, 6.0F)));
	public static final RegistryObject<Block> HEMP_DOOR = registerBlock("hemp_door", () -> new DoorBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));
	public static final RegistryObject<Block> HEMP_TRAPDOOR = registerBlock("hemp_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));
	public static final RegistryObject<Block> HEMP_SIGN = BLOCKS.register("hemp_sign", () -> new ModStandingSignBlock(AbstractBlock.Properties.create(Material.WOOD), ModWoodTypes.HEMPWOOD));
	public static final RegistryObject<Block> HEMP_WALL_SIGN = BLOCKS.register("hemp_wall_sign", () -> new ModWallSignBlock(AbstractBlock.Properties.create(Material.WOOD), ModWoodTypes.HEMPWOOD));

	private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn);
		return toReturn;
	}

	private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
		ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
				new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	}

	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}

}
