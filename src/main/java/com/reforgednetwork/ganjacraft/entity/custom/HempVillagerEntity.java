package com.reforgednetwork.ganjacraft.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.villager.VillagerType;
import net.minecraft.world.World;

public class HempVillagerEntity extends VillagerEntity {
	public HempVillagerEntity(EntityType<? extends VillagerEntity> type, World worldIn) {
		super(type, worldIn, VillagerType.PLAINS);
	}

}
