package com.github.wolfiewaffle.survivalistlighting.items.torch;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class ItemLanternLit extends ItemSurvivalLantern {

	public static final String NAME = "lantern_lit";

	public ItemLanternLit(Block block) {
		super(block);
		this.setCreativeTab(CreativeTabs.MISC);
	}

}
