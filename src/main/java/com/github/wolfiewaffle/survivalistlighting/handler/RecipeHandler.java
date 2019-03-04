package com.github.wolfiewaffle.survivalistlighting.handler;

import com.github.wolfiewaffle.survivalistlighting.SurvivalistLighting;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryModifiable;

@Mod.EventBusSubscriber(modid = SurvivalistLighting.MODID)
public class RecipeHandler {

	@SubscribeEvent
	public static void removeRecipes(RegistryEvent.Register<IRecipe> event) {
		ResourceLocation torch = new ResourceLocation("minecraft:torch");
		ResourceLocation torch2 = new ResourceLocation("forge:torch");
		IForgeRegistryModifiable<IRecipe> modRegistry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
		modRegistry.remove(torch);
		modRegistry.remove(torch2);
	}

}
