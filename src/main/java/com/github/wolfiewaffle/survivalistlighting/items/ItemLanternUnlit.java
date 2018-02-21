package com.github.wolfiewaffle.survivalistlighting.items;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class ItemLanternUnlit extends ItemSurvivalLantern {

	public static final String NAME = "lantern_unlit";

	public ItemLanternUnlit(Block block) {
		super(block);
		this.setCreativeTab(CreativeTabs.MISC);
	}

}
