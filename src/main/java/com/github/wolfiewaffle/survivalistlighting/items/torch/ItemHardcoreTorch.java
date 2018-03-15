package com.github.wolfiewaffle.survivalistlighting.items.torch;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class ItemHardcoreTorch extends ItemBlock {

	public final String NAME;

	public ItemHardcoreTorch(String name, Block block) {
		super(block);
		this.NAME = name;
		this.setCreativeTab(CreativeTabs.MISC);
	}

}
