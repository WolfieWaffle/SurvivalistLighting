package com.github.wolfiewaffle.survivalistlighting.blocks;

import java.util.Random;

import com.github.wolfiewaffle.survivalistlighting.SurvivalistLighting;
import com.github.wolfiewaffle.survivalistlighting.config.ConfigHandler;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTorchUnlit extends BlockHardcoreTorch {

	public static final String NAME = "torch_unlit";

	public BlockTorchUnlit() {
		this.setUnlocalizedName(SurvivalistLighting.MODID + "." + NAME);
		this.setCreativeTab(CreativeTabs.MISC);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (!ConfigHandler.unlitParticlesEnabled) {
			return;
		}

		final EnumFacing facing = state.getValue(FACING);
		final double x = (double) pos.getX() + 0.5D;
		final double y = (double) pos.getY() + 0.7D;
		final double z = (double) pos.getZ() + 0.5D;
		final double mod1 = 0.22D;
		final double mod2 = 0.27D;

		if (facing.getAxis().isHorizontal()) {
			final EnumFacing opposite = facing.getOpposite();
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x + mod2 * (double) opposite.getFrontOffsetX(), y + mod1, z + mod2 * (double) opposite.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D);
		} else {
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0.0D, 0.0D, 0.0D);
		}
	}

}
