package com.github.wolfiewaffle.survivalistlighting.items.torch;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class ItemTorchStoneUnlit extends ItemTorchUnlit {

	public static final String NAME = "torch_stone_unlit";

	public ItemTorchStoneUnlit(Block block) {
		super(block);
		this.setCreativeTab(CreativeTabs.MISC);
	}

}
