package com.github.wolfiewaffle.survivalistlighting.config;

import net.minecraftforge.common.config.Configuration;

public class SurvivalistLightingConfigTorches {

	public static boolean enableUnlitParticles;
	public static boolean enableRelight;
	public static boolean generateLitTorches;
	public static boolean enableStoneTorches;
	public static boolean enableCokeTorches;

	public static int torchBurnoutChance;
	public static int lanternFuel;
	public static int oilcanFuel;

	public static void load(Configuration config) {
		String category = "Torch Settings";

		config.addCustomCategoryComment(category, "Configure the torches");
		config.setCategoryRequiresMcRestart(category, true);

		enableUnlitParticles = config.get(category, "Enable Unlit Torch Particles", true, "Enable/Disable the smoke particles of unlit torches").getBoolean();
		enableRelight = config.get(category, "Enable Torch Relight", true, "Enable/Disable torches to drop unlit or burnt variant").getBoolean();
		generateLitTorches = config.get(category, "Generate Lit Torches", true, "Enable/Disable replacing all world vanilla torches with lit torches").getBoolean();
		enableStoneTorches = config.get(category, "Enable Stone Torches", true, "Enable/Disable stone torches (Requires rodStone oredict)").getBoolean();
		enableCokeTorches = config.get(category, "Enable Coke Torches", true, "Enable/Disable coke torches (Requires REPLACE oredict)").getBoolean(); //TODO: Find coal coke oredict

		torchBurnoutChance = config.get(category, "Torch Burnout Chance", 72000, "The chance a torch will burn out").getInt();
		lanternFuel = config.get(category, "Lantern Fuel", 36000, "The amount of ticks a lantern can stay lit for").getInt();
		oilcanFuel = config.get(category, "Oilcan Fuel", 360000, "The amount of ticks an oilcan can store").getInt();
	}

}
