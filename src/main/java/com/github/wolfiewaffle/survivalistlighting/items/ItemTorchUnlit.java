package com.github.wolfiewaffle.survivalistlighting.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import toughasnails.api.TANBlocks;
import toughasnails.block.BlockTANCampfire;

public class ItemTorchUnlit extends ItemBlock {

	public static final String NAME = "torch_unlit";

	public ItemTorchUnlit(Block block) {
		super(block);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		Block block = worldIn.getBlockState(pos).getBlock();

		if (Loader.isModLoaded("toughasnails")) {
			if (block == TANBlocks.campfire) {
				if (worldIn.getBlockState(pos).getValue(BlockTANCampfire.BURNING)) {
					lightTorch(player, hand);
					return EnumActionResult.FAIL;
				}
			}
		}

		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

	private void lightTorch(EntityPlayer player, EnumHand hand) {
		player.setHeldItem(hand, new ItemStack(ModItems.torchLit, player.getHeldItem(hand).getCount()));
	}

}
