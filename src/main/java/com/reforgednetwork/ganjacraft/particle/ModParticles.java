package com.reforgednetwork.ganjacraft.particle;

import com.reforgednetwork.ganjacraft.GanjaCraft;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModParticles {
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(
			ForgeRegistries.PARTICLE_TYPES, GanjaCraft.MOD_ID);

	public static final RegistryObject<BasicParticleType> SMOKE = PARTICLES.register("smoke", () -> new BasicParticleType(true));
	public static final RegistryObject<BasicParticleType> FLAME = PARTICLES.register("flame", () -> new BasicParticleType(true));

	public static void register(IEventBus eventBus) {
		PARTICLES.register(eventBus);
	}
}
