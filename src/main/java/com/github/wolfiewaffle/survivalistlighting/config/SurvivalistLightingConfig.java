package com.github.wolfiewaffle.survivalistlighting.config;

import com.github.wolfiewaffle.survivalistlighting.SurvivalistLighting;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SurvivalistLightingConfig {

	static Configuration config;

	public static void load(FMLPreInitializationEvent event) {
		config = new Configuration(event.getSuggestedConfigurationFile());

		reloadConfig();

		MinecraftForge.EVENT_BUS.register(new SurvivalistLightingConfig());
	}

	private static void reloadConfig() {

		SurvivalistLightingConfigTorches.load(config);

		if (config.hasChanged()) {
			config.save();
		}
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(SurvivalistLighting.MODID)) {
			reloadConfig();
		}
	}

}