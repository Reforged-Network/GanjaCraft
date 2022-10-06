package com.reforgednetwork.ganjacraft.tileentity;

import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;

public class ModSignTileEntity extends SignTileEntity {
	public ModSignTileEntity() {
		super();
	}

	@Override
	public TileEntityType<?> getType() {
		return ModTileEntites.SIGN_TILE_ENTITY.get();
	}
}
