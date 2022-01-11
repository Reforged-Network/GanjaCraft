package com.reforgednetwork.ganjacraft.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

	public static final ItemGroup GANJACRAFT_GROUP = new ItemGroup("ganjacraftModTab") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModItems.INDICAGUMMY.get());
		}
	};




}
