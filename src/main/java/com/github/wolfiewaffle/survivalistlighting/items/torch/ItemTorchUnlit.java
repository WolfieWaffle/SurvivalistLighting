package com.github.wolfiewaffle.survivalistlighting.items.torch;

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
	private ItemTorchLit litVariant;

	public ItemTorchUnlit(Block block) {
		super(block);
	}

	public void setLitVariant(ItemTorchLit litVariant) {
		this.litVariant = litVariant;
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		Block block = world.getBlockState(pos).getBlock();

		if (Loader.isModLoaded("toughasnails")) {
			if (block == TANBlocks.campfire) {
				if (world.getBlockState(pos).getValue(BlockTANCampfire.BURNING)) {
					lightTorch(player, hand);
					return EnumActionResult.SUCCESS;
				}
			}
		}

		return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
	}

	private void lightTorch(EntityPlayer player, EnumHand hand) {
		player.setHeldItem(hand, new ItemStack(litVariant, player.getHeldItem(hand).getCount()));
	}

}
