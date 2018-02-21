package com.github.wolfiewaffle.survivalistlighting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.wolfiewaffle.survivalistlighting.blocks.ModBlocks;
import com.github.wolfiewaffle.survivalistlighting.config.ConfigHandler;
import com.github.wolfiewaffle.survivalistlighting.items.ModItems;
import com.github.wolfiewaffle.survivalistlighting.proxy.CommonProxy;
import com.github.wolfiewaffle.survivalistlighting.worldgen.TorchGenerator;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
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
		proxy.preInit(event);
		//HardcoreTorchesBlocks.register();
		//HardcoreTorchesItems.register();

		ConfigHandler.loadConfig(event.getSuggestedConfigurationFile());
		ConfigHandler.printConfigInfo();

		GameRegistry.registerWorldGenerator(new TorchGenerator(), 0);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
		//RecipeHandler.removeRecipe(new ItemStack(Blocks.TORCH));
		//RecipeHandler.registerRecipes();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
		//RecipeHandler.removeRecipes();
		//RecipeHandler.registerVanillaRecipes();
	}

}
