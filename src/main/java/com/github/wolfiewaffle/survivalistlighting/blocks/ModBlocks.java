package com.github.wolfiewaffle.survivalistlighting.blocks;

import java.util.ArrayList;

import com.github.wolfiewaffle.survivalistlighting.blocks.lantern.BlockLanternHook;
import com.github.wolfiewaffle.survivalistlighting.blocks.lantern.BlockLanternHookUnlit;
import com.github.wolfiewaffle.survivalistlighting.blocks.lantern.BlockLanternLit;
import com.github.wolfiewaffle.survivalistlighting.blocks.lantern.BlockLanternUnlit;
import com.github.wolfiewaffle.survivalistlighting.blocks.te.TELantern;
import com.github.wolfiewaffle.survivalistlighting.blocks.torch.BlockHardcoreTorch;
import com.github.wolfiewaffle.survivalistlighting.blocks.torch.BlockTorchBurnt;
import com.github.wolfiewaffle.survivalistlighting.blocks.torch.BlockTorchLit;
import com.github.wolfiewaffle.survivalistlighting.blocks.torch.BlockTorchSmoldering;
import com.github.wolfiewaffle.survivalistlighting.blocks.torch.BlockTorchUnlit;
import com.github.wolfiewaffle.survivalistlighting.config.SurvivalistLightingConfigTorches;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

	public static final ArrayList<Block> REGISTRY = new ArrayList<Block>();
	public static BlockTorchBurnt torchBurnt;
	public static BlockTorchUnlit torchUnlit;
	public static BlockTorchLit torchLit;
	public static BlockTorchBurnt torchStoneBurnt;
	public static BlockTorchUnlit torchStoneUnlit;
	public static BlockTorchLit torchStoneLit;
	public static BlockTorchUnlit torchCokeUnlit;
	public static BlockTorchLit torchCokeLit;
	public static BlockTorchSmoldering torchSmoldering;
	public static BlockLanternLit lanternLit;
	public static BlockLanternUnlit lanternUnlit;
	public static BlockLanternHook lanternHook;
	public static BlockLanternHookUnlit lanternHookUnlit;

	public static void init() {

		// Regular Torches
		torchBurnt = registerBlock(new BlockTorchBurnt("torch_burnt"), true);
		torchUnlit = registerBlock(new BlockTorchUnlit("torch_unlit"), false);
		torchLit = registerBlock(new BlockTorchLit("torch_lit"), false);

		torchLit.setBurntVariant(torchBurnt);
		torchLit.setUnlitVariant(torchUnlit);
		torchUnlit.setLitVariant(torchLit);

		// Stone Torches
		if (SurvivalistLightingConfigTorches.enableStoneTorches) {
			torchStoneBurnt = registerBlock(new BlockTorchBurnt("torch_stone_burnt"), true);
			torchStoneUnlit = registerBlock(new BlockTorchUnlit("torch_stone_unlit"), false);
			torchStoneLit = registerBlock(new BlockTorchLit("torch_stone_lit"), false);		

			torchStoneLit.setBurntVariant(torchStoneBurnt);
			torchStoneLit.setUnlitVariant(torchStoneUnlit);
			torchStoneUnlit.setLitVariant(torchStoneLit);
		}

		// Coke Torches
		if (SurvivalistLightingConfigTorches.enableCokeTorches) {
			torchCokeUnlit = registerBlock(new BlockTorchUnlit("torch_coke_unlit"), false);
			torchCokeLit = registerBlock((BlockTorchLit) (new BlockTorchLit("torch_coke_lit").setLightLevel(1.0F)), false);		

			torchCokeLit.setBurntVariant(torchBurnt);
			torchCokeLit.setUnlitVariant(torchCokeUnlit);
			torchCokeUnlit.setLitVariant(torchCokeLit);
		}

		//torchSmoldering = registerBlock(new BlockTorchSmoldering(), BlockTorchSmoldering.NAME, true);
		lanternLit = registerBlock(new BlockLanternLit(), BlockLanternLit.NAME, false);
		lanternUnlit = registerBlock(new BlockLanternUnlit(), BlockLanternUnlit.NAME, false);
		//lanternHook = registerBlock(new BlockLanternHook(), BlockLanternHook.NAME, true);
		//lanternHookUnlit = registerBlock(new BlockLanternHookUnlit(), BlockLanternHookUnlit.NAME, true);

		GameRegistry.registerTileEntity(TELantern.class, TELantern.NAME);
	}

	protected static <T extends BlockHardcoreTorch> T registerBlock(T blockType, boolean genItemBlock) {
		T block = blockType;
		String name = blockType.NAME;
		block.setRegistryName(name);
		ForgeRegistries.BLOCKS.register(block);
		if (genItemBlock)
			ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(name).setCreativeTab(CreativeTabs.MISC));
		REGISTRY.add(block);

		return block;
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
