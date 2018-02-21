package com.github.wolfiewaffle.survivalistlighting.blocks.lantern;

import javax.annotation.Nullable;

import com.github.wolfiewaffle.survivalistlighting.blocks.ModBlocks;
import com.github.wolfiewaffle.survivalistlighting.items.ItemSurvivalLantern;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockLanternHookUnlit extends BlockHardcoreLantern {

	public static final String NAME = "lanternhook_unlit";
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	// public static final PropertyBool POWERED =
	// PropertyBool.create("powered");
	// public static final PropertyBool ATTACHED =
	// PropertyBool.create("attached");
	protected static final AxisAlignedBB HOOK_NORTH_AABB = new AxisAlignedBB(0.3125D, 0.625D, 0.625D, 0.6875D, 0.9375D, 1.0D);
	protected static final AxisAlignedBB HOOK_SOUTH_AABB = new AxisAlignedBB(0.3125D, 0.625D, 0.0D, 0.6875D, 0.9375D, 0.375D);
	protected static final AxisAlignedBB HOOK_WEST_AABB = new AxisAlignedBB(0.625D, 0.625D, 0.3125D, 1.0D, 0.9375D, 0.6875D);
	protected static final AxisAlignedBB HOOK_EAST_AABB = new AxisAlignedBB(0.0D, 0.625D, 0.3125D, 0.375D, 0.9375D, 0.6875D);

	public BlockLanternHookUnlit() {
		super(Material.WOOD);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setCreativeTab(CreativeTabs.REDSTONE);
		this.setTickRandomly(false);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch ((EnumFacing) state.getValue(FACING)) {
		case EAST:
		default:
			return HOOK_EAST_AABB;
		case WEST:
			return HOOK_WEST_AABB;
		case SOUTH:
			return HOOK_SOUTH_AABB;
		case NORTH:
			return HOOK_NORTH_AABB;
		}
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
		return side.getAxis().isHorizontal() && worldIn.getBlockState(pos.offset(side.getOpposite())).isSideSolid(worldIn, pos.offset(side.getOpposite()), side);
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
			if (worldIn.getBlockState(pos.offset(enumfacing)).isSideSolid(worldIn, pos.offset(enumfacing), enumfacing.getOpposite())) {
				return true;
			}
		}

		return false;
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		IBlockState iblockstate = this.getDefaultState();

		if (facing.getAxis().isHorizontal()) {
			iblockstate = iblockstate.withProperty(FACING, facing);
		}

		return iblockstate;
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (blockIn != this) {
			if (this.checkForDrop(worldIn, pos, state)) {
				EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);

				if (!worldIn.getBlockState(pos.offset(enumfacing.getOpposite())).isSideSolid(worldIn, pos.offset(enumfacing.getOpposite()), enumfacing)) {
					this.dropBlockAsItem(worldIn, pos, state, 0);
					worldIn.setBlockToAir(pos);
				}
			}
		}
	}

	private boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state) {
		if (!this.canPlaceBlockAt(worldIn, pos)) {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockToAir(pos);
			return false;
		} else {
			return true;
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 3));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();

		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}

	/**
	 * Removes the lantern. Simply calls setBlockToAir() for regular lanterns,
	 * but is used in lantern hooks
	 */
	@Override
	protected void removeLantern(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		state = ModBlocks.lanternHook.getStateFromMeta(getMetaFromState(state));
		PropertyDirection direction = FACING;
		world.setBlockState(pos, state, 2);
	}

}