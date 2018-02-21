package com.github.wolfiewaffle.survivalistlighting.blocks.lantern;

import java.util.Random;

import com.github.wolfiewaffle.survivalistlighting.SurvivalistLighting;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLanternUnlit extends BlockHardcoreLantern implements ITileEntityProvider {

	public static final String NAME = "lantern_unlit";

	public BlockLanternUnlit() {
		super(Material.ANVIL);
		this.setUnlocalizedName(SurvivalistLighting.MODID + "." + NAME);
		this.setLightLevel(0);
		this.setCreativeTab(CreativeTabs.MISC);
	}

	/*
	 * No particles
	 */
	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		super.randomDisplayTick(state, world, pos, rand);
	}

}
