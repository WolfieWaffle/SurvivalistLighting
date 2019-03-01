package com.github.wolfiewaffle.survivalistlighting.blocks.torch;

import java.util.Random;

import com.github.wolfiewaffle.survivalistlighting.SurvivalistLighting;
import com.github.wolfiewaffle.survivalistlighting.blocks.ModBlocks;
import com.github.wolfiewaffle.survivalistlighting.config.SurvivalistLightingConfigTorches;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTorchLit extends BlockHardcoreTorch {

	private BlockTorchBurnt burntVariant;
	private BlockTorchUnlit unlitVariant;
	private int burnoutChanceMultiplier = 1;

	public BlockTorchLit(String name) {
		super(name);
		setUnlocalizedName(SurvivalistLighting.MODID + "." + NAME);
		setLightLevel(0.9375F);
		setTickRandomly(true);
		setLit(true);
	}

	public void setBurntVariant(BlockTorchBurnt burntVariant) {
		this.burntVariant = burntVariant;
	}

	public void setUnlitVariant(BlockTorchUnlit torchUnlit) {
		this.unlitVariant = torchUnlit;
	}

	public void setBurnoutMult(int i) {
		this.burnoutChanceMultiplier = (int) Math.max(0.1, i);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float side, float hitX, float hitY) {
		Item playerItem = player.getHeldItem(hand).getItem();

		// Wool extinguishes
		if (playerItem == Item.getItemFromBlock(Blocks.WOOL) || playerItem == Item.getItemFromBlock(Blocks.CARPET)) {
			unlight(world, pos);
			if (!player.isCreative()) {
				player.getHeldItem(hand).shrink(1);
			}
			return true;
		}

		// Water bucket extinguishes
		if (playerItem == Items.WATER_BUCKET) {
			unlight(world, pos);
			return true;
		}

		return super.onBlockActivated(world, pos, state, player, hand, facing, side, hitX, hitY);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		if (canBurnout()) {
			if (world.isRainingAt(pos)) {
				extinguish(world, pos, true);
			} else {
				System.out.println("Hello " + (int) (burnoutChanceMultiplier * SurvivalistLightingConfigTorches.torchBurnoutChance * 0.9));
//				world.scheduleUpdate(pos, this, (int) (burnoutChanceMultiplier * SurvivalistLightingConfigTorches.torchBurnoutChance * 0.9));
			}
		}
	}

	@Override
	public void randomTick(World world, BlockPos pos, IBlockState state, Random random) {
		updateTorch(world, pos);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		extinguish(world, pos, false);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random random, int fortune) {
		if (SurvivalistLightingConfigTorches.enableRelight) {
			return Item.getItemFromBlock(unlitVariant);
		}

		return Item.getItemFromBlock(burntVariant);
	}

	@Override
	public void extinguish(World world, BlockPos pos, boolean extinguishFully) {
		playExtinguishSound(world, pos);
		spawnSmokePuff(world, pos);
		if (!extinguishFully) {
			System.out.println("UPDATE TORCH");
			world.setBlockState(pos, getState(world, pos, unlitVariant), 3);
		} else {
			world.setBlockState(pos, getState(world, pos, burntVariant), 3);
		}
	}

	public void unlight(World world, BlockPos pos) {
		playExtinguishSound(world, pos);
		spawnSmokePuff(world, pos);
		world.setBlockState(pos, getState(world, pos, unlitVariant), 2);
	}

	protected void spawnSmokePuff(World world, BlockPos pos) {
		final EnumFacing facing = world.getBlockState(pos).getValue(FACING);
		final double x = (double) pos.getX() + 0.5D;
		final double y = (double) pos.getY() + 0.7D;
		final double z = (double) pos.getZ() + 0.5D;
		final double mod1 = 0.22D;
		final double mod2 = 0.27D;

		if (facing.getAxis().isHorizontal()) {
			final EnumFacing opposite = facing.getOpposite();
			for (int i = 0; i < 3; i++)
				world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, x + mod2 * (double) opposite.getFrontOffsetX(), y + mod1, z + mod2 * (double) opposite.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D);
		} else {
			for (int i = 0; i < 3; i++)
				world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, x, y, z, 0.0D, 0.0D, 0.0D);
		}
	}

}
