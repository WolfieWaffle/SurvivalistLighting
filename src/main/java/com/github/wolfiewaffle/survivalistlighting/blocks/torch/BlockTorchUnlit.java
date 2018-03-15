package com.github.wolfiewaffle.survivalistlighting.blocks.torch;

import java.util.Random;

import com.github.wolfiewaffle.survivalistlighting.SurvivalistLighting;
import com.github.wolfiewaffle.survivalistlighting.config.SurvivalistLightingConfigTorches;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTorchUnlit extends BlockHardcoreTorch {

	public static final String NAME = "torch_unlit";
	private BlockTorchLit litVariant;

	public BlockTorchUnlit() {
		this.setUnlocalizedName(SurvivalistLighting.MODID + "." + NAME);
		this.setCreativeTab(CreativeTabs.MISC);
	}

	public void setLitVariant(BlockTorchLit litTorch) {
		this.litVariant = litTorch;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float side, float hitX, float hitY) {
		return lightTorch(world, pos, player, player.getHeldItem(hand));
	}

	public boolean lightTorch(World world, BlockPos pos, EntityPlayer player, ItemStack heldItem) {
		if (world.getBlockState(pos).getBlock() instanceof BlockHardcoreTorch) {
			if (!heldItem.isEmpty() && (heldItem.getItem() == Items.FLINT_AND_STEEL/* || (ConfigHandler.matchboxCreatesFire && heldItem.getItem() == HardcoreTorchesItems.matchbox)) && (!ConfigHandler.noRelightEnabled || !isLit()*/)) {
				heldItem.damageItem(1, player);
				playIgniteSound(world, pos);
				if (!world.isRainingAt(pos)) {
					world.setBlockState(pos, getState(world, pos, litVariant), 2);
				}

				return true;
			}
		}

		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if (!SurvivalistLightingConfigTorches.enableUnlitParticles) {
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
