package com.laylataydragon.rustlemod.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class RustleConfigConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.ConfigValue<Boolean> REPLACEMOBS;
	public static final ForgeConfigSpec.ConfigValue<Double> CALFADULTTIME;
	static {
		BUILDER.push("Behavior");
		REPLACEMOBS = BUILDER.define("Replace Mobs", true);
		CALFADULTTIME = BUILDER.define("Calf Grow Time", (double) 1000);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
