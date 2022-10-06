package com.reforgednetwork.ganjacraft.entity;

import com.reforgednetwork.ganjacraft.GanjaCraft;
import com.reforgednetwork.ganjacraft.entity.custom.HempVillagerEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityType {
	public static DeferredRegister<EntityType<?>> ENTITY_TYPES
			= DeferredRegister.create(ForgeRegistries.ENTITIES, GanjaCraft.MOD_ID);

	public static final RegistryObject<EntityType<HempVillagerEntity>> HEMP_VILLAGER =
			ENTITY_TYPES.register("hemp_villager", () -> EntityType.Builder.create(HempVillagerEntity::new,
					EntityClassification.MISC).size(0.6F, 1.95F).trackingRange(10).build(new ResourceLocation(GanjaCraft.MOD_ID, "hemp_villager").toString()));



	public static void register(IEventBus eventBus) {
		ENTITY_TYPES.register(eventBus);
	}
}
