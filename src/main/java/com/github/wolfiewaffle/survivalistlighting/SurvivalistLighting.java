package com.github.wolfiewaffle.survivalistlighting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.wolfiewaffle.survivalistlighting.config.SurvivalistLightingConfig;
import com.github.wolfiewaffle.survivalistlighting.proxy.CommonProxy;
import com.github.wolfiewaffle.survivalistlighting.worldgen.TorchGenerator;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = SurvivalistLighting.MODID, name = SurvivalistLighting.NAME, version = SurvivalistLighting.VERSION, acceptedMinecraftVersions = "[1.12.2]")

public class SurvivalistLighting {

	public static final String MODID = "survivalistlighting";
	public static final String NAME = "Survivalist Lighting";
	public static final String VERSION = "1.0";

	public static final Logger logger = LogManager.getLogger(MODID);

	@SidedProxy(clientSide = "com.github.wolfiewaffle.survivalistlighting.proxy.ClientProxy", serverSide = "com.github.wolfiewaffle.survivalistlighting.proxy.ServerProxy")
	public static CommonProxy proxy;

	public static int barColor = 2395903;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		SurvivalistLightingConfig.load(event);
		proxy.preInit(event);

		GameRegistry.registerWorldGenerator(new TorchGenerator(), 0);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

}
