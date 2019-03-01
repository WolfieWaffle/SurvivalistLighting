package com.github.wolfiewaffle.survivalistlighting.config;

import java.util.Arrays;
import java.util.List;

import net.minecraftforge.common.config.Configuration;

public class SurvivalistLightingConfigTorches {

	public static boolean enableUnlitParticles;
	public static boolean enableRelight;
	public static boolean generateLitTorches;
	public static boolean enableStoneTorches;
	public static boolean enableCokeTorches;
	public static boolean lightFromFire;

	public static int torchBurnoutChance;
	public static int lanternFuel;
	public static int oilcanFuel;

	public static List<String> lighterBlocks;

	public static void load(Configuration config) {
		String category = "Torch Settings";

		config.addCustomCategoryComment(category, "Configure the torches");
		config.setCategoryRequiresMcRestart(category, true);

		enableUnlitParticles = config.get(category, "Enable Unlit Torch Particles", true, "Enable/Disable the smoke particles of unlit torches").getBoolean();
		enableRelight = config.get(category, "Enable Torch Relight", true, "Enable/Disable torches to drop unlit variant instead of burnt variant").getBoolean();
		generateLitTorches = config.get(category, "Generate Lit Torches", true, "Enable/Disable replacing all world vanilla torches with lit torches").getBoolean();
		enableStoneTorches = config.get(category, "Enable Stone Torches", true, "Enable/Disable stone torches (Requires rodStone oredict)").getBoolean();
		enableCokeTorches = config.get(category, "Enable Coke Torches", true, "Enable/Disable coke torches (Requires REPLACE oredict)").getBoolean(); //TODO: Find coal coke oredict
		lightFromFire = config.get(category, "Light Torches From Fire", true, "Enable/Disable lighting a torch by right-clicking on fire").getBoolean();

		torchBurnoutChance = config.get(category, "Torch Burnout Chance", 20, "The chance a torch will burn out. Lower is more likely.").getInt();
		lanternFuel = config.get(category, "Lantern Fuel", 36000, "The amount of ticks a lantern can stay lit for").getInt();
		oilcanFuel = config.get(category, "Oilcan Fuel", 360000, "The amount of ticks an oilcan can store").getInt();

		lighterBlocks = Arrays.asList(config.get(category, "Lighter Blocks", new String[]{"minecraft:torch", "survivalistlighting:torch_lit", "survivalistlighting:coke_torch_lit"}, "Blocks that you can right click to light a torch").getStringList());
	}

}
