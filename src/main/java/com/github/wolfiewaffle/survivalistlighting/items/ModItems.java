package com.github.wolfiewaffle.survivalistlighting.items;

import java.util.ArrayList;

import com.github.wolfiewaffle.survivalistlighting.SurvivalistLighting;
import com.github.wolfiewaffle.survivalistlighting.blocks.ModBlocks;
import com.github.wolfiewaffle.survivalistlighting.config.SurvivalistLightingConfigTorches;
import com.github.wolfiewaffle.survivalistlighting.items.torch.ItemHardcoreTorch;
import com.github.wolfiewaffle.survivalistlighting.items.torch.ItemLanternLit;
import com.github.wolfiewaffle.survivalistlighting.items.torch.ItemLanternUnlit;
import com.github.wolfiewaffle.survivalistlighting.items.torch.ItemOilcan;
import com.github.wolfiewaffle.survivalistlighting.items.torch.ItemTorchLit;
import com.github.wolfiewaffle.survivalistlighting.items.torch.ItemTorchUnlit;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModItems {

	public static final ArrayList<Item> REGISTRY = new ArrayList<Item>();

	public static Item glowstoneCrystal;
	public static Item glowstonePaste;
	public static ItemOilcan oilcan;
	public static ItemLanternLit lanternLit;
	public static ItemLanternUnlit lanternUnlit;
	public static ItemTorchLit torchLit;
	public static ItemTorchUnlit torchUnlit;
	public static ItemTorchLit torchStoneLit;
	public static ItemTorchUnlit torchStoneUnlit;
	public static ItemTorchLit torchCokeLit;
	public static ItemTorchUnlit torchCokeUnlit;

	public static void init() {
		glowstoneCrystal = registerItem(new Item().setUnlocalizedName(SurvivalistLighting.MODID + ".glowstone_crystal").setCreativeTab(CreativeTabs.MATERIALS), "glowstone_crystal");
		oilcan = registerItem(new ItemOilcan(), ItemOilcan.NAME);
		lanternLit = registerItem(new ItemLanternLit(ModBlocks.lanternLit), "lantern_lit");
		lanternUnlit = registerItem(new ItemLanternUnlit(ModBlocks.lanternUnlit), "lantern_unlit");

		// Regular Torches
		torchLit = registerItem(new ItemTorchLit("torch_lit", ModBlocks.torchLit));
		torchUnlit = registerItem(new ItemTorchUnlit("torch_unlit", ModBlocks.torchUnlit));
		torchUnlit.setLitVariant(torchLit);

		// Stone Torches
		if (SurvivalistLightingConfigTorches.enableStoneTorches) {
			torchStoneLit = registerItem(new ItemTorchLit("torch_stone_lit", ModBlocks.torchStoneLit));
			torchStoneUnlit = registerItem(new ItemTorchUnlit("torch_stone_unlit", ModBlocks.torchStoneUnlit));
			torchStoneUnlit.setLitVariant(torchStoneLit);
		}

		// Coke Torches
		if (SurvivalistLightingConfigTorches.enableCokeTorches) {
			torchCokeLit = registerItem(new ItemTorchLit("torch_coke_lit", ModBlocks.torchCokeLit));
			torchCokeUnlit = registerItem(new ItemTorchUnlit("torch_coke_unlit", ModBlocks.torchCokeUnlit));
			torchCokeUnlit.setLitVariant(torchCokeLit);
		}	
	}

	protected static <T extends ItemHardcoreTorch> T registerItem(T itemType) {
		T item = itemType;
		item.setRegistryName(item.NAME);
		ForgeRegistries.ITEMS.register(item);
		REGISTRY.add(item);

		return item;
	}

	protected static <T extends Item> T registerItem(T itemType, String name) {
		T item = itemType;
		item.setRegistryName(name);
		ForgeRegistries.ITEMS.register(item);
		REGISTRY.add(item);

		return item;
	}

}
