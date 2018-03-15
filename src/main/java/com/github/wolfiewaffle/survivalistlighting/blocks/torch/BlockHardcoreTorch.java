package com.github.wolfiewaffle.survivalistlighting.blocks.torch;

import com.github.wolfiewaffle.survivalistlighting.config.SurvivalistLightingConfigTorches;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockHardcoreTorch extends BlockTorch {

	private boolean isLit;
	public final String NAME;

	public BlockHardcoreTorch(String name) {
		this.NAME = name;
		this.setCreativeTab(CreativeTabs.MISC);
	}

	public void extinguish(World world, BlockPos pos, boolean extinguishFully) {
		
	}

	public void updateTorch(World world, BlockPos pos) {
		if (canBurnout() && world.isRainingAt(pos) && isLit()) {
			// world.setBlockState(pos, getState(world, pos,
			// RealisticTorchesBlocks.torchBurnt), 2);
			extinguish(world, pos, true);
		} else if (canBurnout() && isLit() && ((Math.random() * SurvivalistLightingConfigTorches.torchBurnoutChance) < 1)) {
			// world.setBlockState(pos, getState(world, pos,
			// RealisticTorchesBlocks.torchBurnt), 2);
			extinguish(world, pos, true);
		}
	}

	public IBlockState getState(World world, BlockPos pos, BlockTorch torch) {
		return torch.getStateFromMeta(torch.getMetaFromState(world.getBlockState(pos)));
	}

	public void playIgniteSound(World world, BlockPos pos) {
		world.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
	}

	public void playExtinguishSound(World world, BlockPos pos) {
		world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
	}

	public BlockHardcoreTorch setLit(boolean lit) {
		isLit = lit;
		return this;
	}

	public boolean isLit() {
		return isLit;
	}

	public boolean canBurnout() {
		return SurvivalistLightingConfigTorches.torchBurnoutChance > 0;
	}

}
