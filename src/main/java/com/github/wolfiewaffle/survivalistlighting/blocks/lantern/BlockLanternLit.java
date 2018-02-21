package com.github.wolfiewaffle.survivalistlighting.blocks.lantern;

import java.util.Random;

import com.github.wolfiewaffle.survivalistlighting.SurvivalistLighting;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLanternLit extends BlockHardcoreLantern implements ITileEntityProvider {

	public static final String NAME = "lantern_lit";

	public BlockLanternLit() {
		super(Material.ANVIL);
		this.setUnlocalizedName(SurvivalistLighting.MODID + "." + NAME);
		this.setLightLevel(1.0f);
		this.setCreativeTab(CreativeTabs.MISC);
	}

	/*
	 * Randomly spawns particles
	 */
	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 0.2, pos.getZ() + 0.5, 0.0D, 0.0D, 0.0D);
		super.randomDisplayTick(state, world, pos, rand);
	}

}
