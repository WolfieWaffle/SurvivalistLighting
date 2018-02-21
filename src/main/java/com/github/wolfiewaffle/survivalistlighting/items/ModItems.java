package com.github.wolfiewaffle.survivalistlighting.items;

import java.util.ArrayList;

import com.github.wolfiewaffle.survivalistlighting.SurvivalistLighting;
import com.github.wolfiewaffle.survivalistlighting.blocks.ModBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

//@Mod.EventBusSubscriber(modid = SurvivalistLighting.MODID)
public class ModItems {

	public static final ArrayList<Item> REGISTRY = new ArrayList<Item>();

	public static Item glowstoneCrystal;
	public static Item glowstonePaste;
	public static ItemOilcan oilcan;
	public static ItemLanternLit lanternLit;
	public static ItemLanternUnlit lanternUnlit;
	public static ItemTorchLit torchLit;
	public static ItemTorchUnlit torchUnlit;

	//@SubscribeEvent
	public static void init(/*RegistryEvent.Register<Item> event*/) {
		glowstoneCrystal = registerItem(new Item().setUnlocalizedName(SurvivalistLighting.MODID + ".glowstone_crystal").setCreativeTab(CreativeTabs.MATERIALS), "glowstone_crystal");
		oilcan = registerItem(new ItemOilcan(), ItemOilcan.NAME);
		lanternLit = registerItem(new ItemLanternLit(ModBlocks.lanternLit), ItemLanternLit.NAME);
		lanternUnlit = registerItem(new ItemLanternUnlit(ModBlocks.lanternUnlit), ItemLanternUnlit.NAME);
		torchLit = registerItem(new ItemTorchLit(ModBlocks.torchLit), ItemTorchLit.NAME);
		torchUnlit = registerItem(new ItemTorchUnlit(ModBlocks.torchUnlit), ItemTorchUnlit.NAME);
	}

	protected static <T extends Item> T registerItem(T itemType, String name/*, RegistryEvent.Register<Item> event*/) {
		T item = itemType;
		item.setRegistryName(name);
		ForgeRegistries.ITEMS.register(item);
		REGISTRY.add(item);

		return item;
	}

}
