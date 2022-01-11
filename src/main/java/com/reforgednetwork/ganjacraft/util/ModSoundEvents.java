package com.reforgednetwork.ganjacraft.util;

import com.reforgednetwork.ganjacraft.GanjaCraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSoundEvents {

	public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
			DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, GanjaCraft.MOD_ID);

	public static final RegistryObject<SoundEvent> SMOKING =
			registerSoundEvent("smoking");

	public static final RegistryObject<SoundEvent> COUGHING =
			registerSoundEvent("coughing");

	public static final RegistryObject<SoundEvent> DISC_AFROMAN =
			registerSoundEvent("disc_afroman");

	public static final RegistryObject<SoundEvent> BONGHIT =
			registerSoundEvent("bonghit");

	private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
		return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(GanjaCraft.MOD_ID, name)));
	}

	public static void register(IEventBus eventBus) {
		SOUND_EVENTS.register(eventBus);
	}
}
