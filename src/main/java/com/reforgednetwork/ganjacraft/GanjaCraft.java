package com.reforgednetwork.ganjacraft;

import com.reforgednetwork.ganjacraft.block.ModBlocks;
import com.reforgednetwork.ganjacraft.block.ModWoodTypes;
import com.reforgednetwork.ganjacraft.entity.ModEntityType;
import com.reforgednetwork.ganjacraft.item.ModItems;
import com.reforgednetwork.ganjacraft.particle.ModParticles;
import com.reforgednetwork.ganjacraft.particle.Smoke;
import com.reforgednetwork.ganjacraft.tileentity.ModTileEntites;
import com.reforgednetwork.ganjacraft.util.ModSoundEvents;
import net.minecraft.block.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.tileentity.SignTileEntityRenderer;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod(GanjaCraft.MOD_ID)
public class GanjaCraft {

    public static final String MOD_ID = "ganjacraft";
    private static final Logger LOGGER = LogManager.getLogger();

    public GanjaCraft() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModTileEntites.register(eventBus);
        ModSoundEvents.register(eventBus);
        ModEntityType.register(eventBus);
        WoodType.register(ModWoodTypes.HEMPWOOD);
        ModParticles.register(eventBus);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ComposterBlock.CHANCES.put(ModItems.HEMPSTEM.get(), 0.5f);
            ComposterBlock.CHANCES.put(ModItems.INDICA_BUD.get(), 0.5f);
            ComposterBlock.CHANCES.put(ModItems.INDICA_SEED.get(), 0.5f);
            ComposterBlock.CHANCES.put(ModItems.SATIVA_BUD.get(), 0.5f);
            ComposterBlock.CHANCES.put(ModItems.SATIVA_SEED.get(), 0.5f);
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {

            RenderTypeLookup.setRenderLayer(ModBlocks.HEMP_DOOR.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.HEMP_TRAPDOOR.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.SATIVA_CROP.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.INDICA_CROP.get(), RenderType.getCutout());
//            RenderTypeLookup.setRenderLayer(ModBlocks.TEST_CROP.get(), RenderType.getCutout());

            ClientRegistry.bindTileEntityRenderer(ModTileEntites.SIGN_TILE_ENTITY.get(), SignTileEntityRenderer::new);
            Atlases.addWoodType(ModWoodTypes.HEMPWOOD);
        });
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        }

        @SubscribeEvent
        public static void onParticlesRegistry(ParticleFactoryRegisterEvent e) {
            Minecraft.getInstance().particles.registerFactory(ModParticles.SMOKE.get(), Smoke.Factory::new);
            Minecraft.getInstance().particles.registerFactory(ModParticles.FLAME.get(), Smoke.Factory::new);
        }
    }
}
