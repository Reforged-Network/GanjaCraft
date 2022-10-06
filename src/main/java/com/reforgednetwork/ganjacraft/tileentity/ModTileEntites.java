package com.reforgednetwork.ganjacraft.tileentity;

import com.reforgednetwork.ganjacraft.GanjaCraft;
import com.reforgednetwork.ganjacraft.block.ModBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntites {

	public static DeferredRegister<TileEntityType<?>> TILE_ENTITES =
			DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, GanjaCraft.MOD_ID);

	public static final RegistryObject<TileEntityType<ModSignTileEntity>> SIGN_TILE_ENTITY =
			TILE_ENTITES.register("sign", () -> TileEntityType.Builder.create(ModSignTileEntity::new, ModBlocks.HEMP_SIGN.get(), ModBlocks.HEMP_WALL_SIGN.get()).build(null));

	public static void register(IEventBus eventBus) { TILE_ENTITES.register(eventBus); }
}
