package com.github.wolfiewaffle.survivalistlighting.blocks;

import java.util.ArrayList;

import com.github.wolfiewaffle.survivalistlighting.SurvivalistLighting;
import com.github.wolfiewaffle.survivalistlighting.blocks.lantern.BlockLanternHook;
import com.github.wolfiewaffle.survivalistlighting.blocks.lantern.BlockLanternHookUnlit;
import com.github.wolfiewaffle.survivalistlighting.blocks.lantern.BlockLanternLit;
import com.github.wolfiewaffle.survivalistlighting.blocks.lantern.BlockLanternUnlit;
import com.github.wolfiewaffle.survivalistlighting.blocks.te.TELantern;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

//@Mod.EventBusSubscriber(modid = SurvivalistLighting.MODID)
public class ModBlocks {

	public static final ArrayList<Block> REGISTRY = new ArrayList<Block>();
	public static BlockTorchUnlit torchUnlit;
	public static BlockTorchLit torchLit;
	public static BlockTorchSmoldering torchSmoldering;
	public static BlockTorchBurnt torchBurnt;
	public static BlockLanternLit lanternLit;
	public static BlockLanternUnlit lanternUnlit;
	public static BlockLanternHook lanternHook;
	public static BlockLanternHookUnlit lanternHookUnlit;

	//@SubscribeEvent
	public static void init(/*RegistryEvent.Register<Block> event*/) {
		torchUnlit = registerBlock(new BlockTorchUnlit(), BlockTorchUnlit.NAME, false);
		torchLit = registerBlock(new BlockTorchLit(), BlockTorchLit.NAME, false);
		torchSmoldering = registerBlock(new BlockTorchSmoldering(), BlockTorchSmoldering.NAME, true);
		torchBurnt = registerBlock(new BlockTorchBurnt(), BlockTorchBurnt.NAME, true);
		lanternLit = registerBlock(new BlockLanternLit(), BlockLanternLit.NAME, false);
		lanternUnlit = registerBlock(new BlockLanternUnlit(), BlockLanternUnlit.NAME, false);
		lanternHook = registerBlock(new BlockLanternHook(), BlockLanternHook.NAME, true);
		lanternHookUnlit = registerBlock(new BlockLanternHookUnlit(), BlockLanternHookUnlit.NAME, true);

		GameRegistry.registerTileEntity(TELantern.class, TELantern.NAME);
	}

	protected static <T extends Block> T registerBlock(T blockType, String name, boolean genItemBlock) {
		T block = blockType;
		block.setRegistryName(name);
		ForgeRegistries.BLOCKS.register(block);
		if (genItemBlock)
			ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(name).setCreativeTab(CreativeTabs.MISC));
		REGISTRY.add(block);

		return block;
	}

}
