package net.mcreator.rustle.configuration;

import net.minecraftforge.common.ForgeConfigSpec;

public class RustleConfigConfiguration {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SPEC;
	public static final ForgeConfigSpec.ConfigValue<Boolean> REPLACEMOBS;
	static {
		BUILDER.push("Behavior");
		REPLACEMOBS = BUILDER.define("", true);
		BUILDER.pop();

		SPEC = BUILDER.build();
	}

}
