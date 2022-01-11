package com.reforgednetwork.ganjacraft.item;

import com.reforgednetwork.ganjacraft.GanjaCraft;
import com.reforgednetwork.ganjacraft.block.ModBlocks;
import com.reforgednetwork.ganjacraft.item.custom.IndicaPipeUse;
import com.reforgednetwork.ganjacraft.item.custom.IndicaSpliffUse;
import com.reforgednetwork.ganjacraft.util.ModSoundEvents;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

	public static final DeferredRegister<Item> ITEMS =
			DeferredRegister.create(ForgeRegistries.ITEMS, GanjaCraft.MOD_ID);


	public static final RegistryObject<Item> INDICA_SEED = ITEMS.register("indica_seed", () -> new BlockNamedItem(ModBlocks.INDICA_CROP.get(), new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> SATIVA_SEED = ITEMS.register("sativa_seed", () -> new BlockItem(ModBlocks.SATIVA_SEED.get(), new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> COOKED_SEED = ITEMS.register("cooked_seed", () -> new Item(new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> HEMPFLOUR = ITEMS.register("hemp_flour", () -> new Item(new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> HEMPSTEM = ITEMS.register("hemp_stem", () -> new Item(new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> HEMPFIBER = ITEMS.register("hemp_fiber", () -> new Item(new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> HEMPBRICK = ITEMS.register("hemp_brick", () -> new Item(new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> HEMPCRETE = ITEMS.register("hempcrete", () -> new Item(new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> INDICA_BUD = ITEMS.register("indica_bud", () -> new IndicaSpliffUse(new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> INDICA_SPLIFF = ITEMS.register("indica_spliff", () -> new IndicaSpliffUse(new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> INDICA_BONG = ITEMS.register("indica_bong", () -> new Item(new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> INDICA_PIPE = ITEMS.register("indica_pipe", () -> new IndicaPipeUse(new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> INDICAGUMMY = ITEMS.register("indica_gummy", () -> new Item(new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> SATIVA_BUD = ITEMS.register("sativa_bud", () -> new IndicaSpliffUse(new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> SATIVA_SPLIFF = ITEMS.register("sativa_spliff", () -> new IndicaSpliffUse(new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> SATIVA_BONG = ITEMS.register("sativa_bong", () -> new Item(new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> SATIVA_PIPE = ITEMS.register("sativa_pipe", () -> new Item(new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> SATIVAGUMMY = ITEMS.register("sativa_gummy", () -> new Item(new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> HEMPMILKBUCKET = ITEMS.register("hempmilk_bucket", () -> new Item(new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> CANNABUTTER = ITEMS.register("cannabutter", () -> new Item(new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> HEMPTEA = ITEMS.register("hemp_tea", () -> new Item(new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> MAGICCOOKIE = ITEMS.register("magic_cookie", () -> new SimpleFoiledItem(new Item.Properties().maxStackSize(4).food(new Food.Builder().hunger(5).saturation(0.1f).effect(() -> new EffectInstance(Effects.FIRE_RESISTANCE, 100, 2), 1.0f).build()).group(ModItemGroup.GANJACRAFT_GROUP)));
    public static final RegistryObject<Item> GOLDENCANNAPPLE = ITEMS.register("golden_cannapple", () -> new SimpleFoiledItem(new Item.Properties().maxStackSize(4).food(new Food.Builder().hunger(5).saturation(0.1f).effect(() ->new EffectInstance(Effects.FIRE_RESISTANCE, 100, 2), 1.0f).build()).group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> GOLDENCANNARROT = ITEMS.register("golden_cannarrot", () -> new SimpleFoiledItem(new Item.Properties().maxStackSize(4).food(new Food.Builder().hunger(5).saturation(0.1f).effect(() -> new EffectInstance(Effects.FIRE_RESISTANCE, 100, 2), 1.0f).build()).group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> EMPTYPIPE = ITEMS.register("empty_pipe", () -> new Item(new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> EMPTYBONG = ITEMS.register("empty_bong", () -> new Item(new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> LIGHTER = ITEMS.register("lighter", () -> new Item(new Item.Properties().group(ModItemGroup.GANJACRAFT_GROUP)));
    public static final RegistryObject<Item> DISC_AFROMAN = ITEMS.register("disc_afroman", () -> new MusicDiscItem(1, () -> ModSoundEvents.DISC_AFROMAN.get(), new Item.Properties().maxStackSize(1).group(ModItemGroup.GANJACRAFT_GROUP)));

	//armor
	public static final RegistryObject<Item> HEMPBEANIE = ITEMS.register("hemp_beanie", () -> new ArmorItem(ModArmorMaterial.HEMPFIBER, EquipmentSlotType.HEAD, (new Item.Properties()).group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> HEMPSHIRT = ITEMS.register("hemp_shirt", () -> new ArmorItem(ModArmorMaterial.HEMPFIBER, EquipmentSlotType.CHEST, (new Item.Properties()).group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> HEMPPANTS = ITEMS.register("hemp_pants", () -> new ArmorItem(ModArmorMaterial.HEMPFIBER, EquipmentSlotType.LEGS, (new Item.Properties()).group(ModItemGroup.GANJACRAFT_GROUP)));
	public static final RegistryObject<Item> HEMPFLIPFLOPS = ITEMS.register("hemp_flipflops", () -> new ArmorItem(ModArmorMaterial.HEMPFIBER, EquipmentSlotType.FEET, (new Item.Properties()).group(ModItemGroup.GANJACRAFT_GROUP)));

	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}
