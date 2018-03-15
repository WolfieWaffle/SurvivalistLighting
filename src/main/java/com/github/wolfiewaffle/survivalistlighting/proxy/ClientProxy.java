package com.github.wolfiewaffle.survivalistlighting.proxy;

import com.github.wolfiewaffle.survivalistlighting.blocks.ModBlocks;
import com.github.wolfiewaffle.survivalistlighting.items.ModItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
		registerModels();
	}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}

	private void registerModels() {
		registerModel(ModItems.lanternLit);
		registerModel(ModItems.lanternUnlit);
		registerModel(Item.getItemFromBlock(ModBlocks.torchBurnt));
		registerModel(Item.getItemFromBlock(ModBlocks.torchLit));
		registerModel(Item.getItemFromBlock(ModBlocks.torchSmoldering));
		registerModel(Item.getItemFromBlock(ModBlocks.torchUnlit));
		registerModel(Item.getItemFromBlock(ModBlocks.torchStoneBurnt));
		registerModel(Item.getItemFromBlock(ModBlocks.torchStoneLit));
		registerModel(Item.getItemFromBlock(ModBlocks.torchStoneUnlit));
		registerModel(ModItems.glowstoneCrystal);
		registerModel(ModItems.oilcan);
	}

	private void registerModel(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

}
