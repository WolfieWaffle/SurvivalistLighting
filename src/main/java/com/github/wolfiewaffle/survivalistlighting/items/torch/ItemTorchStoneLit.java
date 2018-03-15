package com.github.wolfiewaffle.survivalistlighting.items.torch;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class ItemTorchStoneLit extends ItemTorchLit {

	public static final String NAME = "torch_stone_lit";

	public ItemTorchStoneLit(Block block) {
		super(block);
		this.setCreativeTab(CreativeTabs.MISC);
	}

}
