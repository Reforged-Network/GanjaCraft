package com.reforgednetwork.ganjacraft.util;

import net.minecraft.util.IStringSerializable;

public enum TrippleBlockCrop implements IStringSerializable {
	UPPER,
	MIDDLE,
	LOWER;

	public String toString() {
		return this.getString();
	}

	public String getString() {
		return this == UPPER ? "upper" : this ==  LOWER ? "lower" : "middle";
	}
}
