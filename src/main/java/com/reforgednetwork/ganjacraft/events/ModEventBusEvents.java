package com.reforgednetwork.ganjacraft.events;

import com.reforgednetwork.ganjacraft.GanjaCraft;
import com.reforgednetwork.ganjacraft.events.loot.GanjaSeedsModifier;
import com.reforgednetwork.ganjacraft.particle.ModParticles;
import com.reforgednetwork.ganjacraft.particle.Smoke;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = GanjaCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
	@SubscribeEvent
	public static void addEntityAttributes(EntityAttributeCreationEvent event) {
		//placeholder for future villagers
	}

	@SubscribeEvent
	public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
		//placeholder for future villagers
	}

	@SubscribeEvent
	public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
														   event) {
		event.getRegistry().registerAll(
				new GanjaSeedsModifier.Serializer().setRegistryName
						(new ResourceLocation(GanjaCraft.MOD_ID,"indica_seeds_from_grass")),
				new GanjaSeedsModifier.Serializer().setRegistryName
						(new ResourceLocation(GanjaCraft.MOD_ID,"sativa_seeds_from_grass"))
		);
	}
}
