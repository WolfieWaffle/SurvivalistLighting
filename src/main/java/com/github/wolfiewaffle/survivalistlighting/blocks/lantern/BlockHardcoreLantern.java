package com.github.wolfiewaffle.survivalistlighting.blocks.lantern;

import com.github.wolfiewaffle.survivalistlighting.SurvivalistLighting;
import com.github.wolfiewaffle.survivalistlighting.blocks.ModBlocks;
import com.github.wolfiewaffle.survivalistlighting.blocks.te.TELantern;
import com.github.wolfiewaffle.survivalistlighting.config.SurvivalistLightingConfigTorches;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHardcoreLantern extends Block implements ITileEntityProvider {

	public static final String NAME = "lantern";
	private static final Item[] lighters = { Items.FLINT_AND_STEEL/*, HardcoreTorchesItems.matchbox */};

	public BlockHardcoreLantern(Material material) {
		super(material);
		this.setUnlocalizedName(SurvivalistLighting.MODID + "." + NAME);
		this.setLightOpacity(0);
		this.setLightLevel(1.0f);
		this.setCreativeTab(CreativeTabs.MISC);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.35, 0.0, 0.35, 0.65, 0.5, 0.65);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	public void playExtinguishSound(World world, BlockPos pos) {
		world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
	}

	public void playIgniteSound(World world, BlockPos pos) {
		world.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
	}

	public void playFillSound(World world, BlockPos pos) {
		world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
	}

	public void playerMetalSound(World world, BlockPos pos) {
		world.playSound(null, pos, SoundEvents.ITEM_ARMOR_EQUIP_IRON, SoundCategory.BLOCKS, 1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
	}

	/*
	 * Called to make the TileEntity, overridden to provide the correct one
	 */
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TELantern();
	}

	/**
	 * Sets the correct fuel value for the lantern when placed
	 */
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if (!world.isRemote) {
			TELantern te = ((TELantern) world.getTileEntity(pos));
			int fuel = SurvivalistLightingConfigTorches.lanternFuel - stack.getItemDamage();

			te.setFuel(fuel);
			playerMetalSound(world, pos);
			super.onBlockPlacedBy(world, pos, state, placer, stack);
		}
	}

	/**
	 * Call to get an ItemStack from a lantern Block
	 */
	public static ItemStack getLanternItem(World world, BlockPos pos) {
		Block lantern = world.getBlockState(pos).getBlock();
		if (lantern instanceof BlockHardcoreLantern) {
			TELantern te = ((TELantern) world.getTileEntity(pos));
			int fuel = SurvivalistLightingConfigTorches.lanternFuel - te.getFuel();

			if (te.isLit()) {
				lantern = ModBlocks.lanternLit;
			} else {
				lantern = ModBlocks.lanternUnlit;
			}

			ItemStack stack = new ItemStack(lantern, 1, fuel);
			return stack;
		}
		return new ItemStack(Blocks.ACACIA_FENCE);
	}

	/**
	 * Try to pick up the lantern
	 */
	protected boolean tryPickup(World world, BlockPos pos, EntityPlayer player, EnumHand hand) {
		if (player.getHeldItem(hand).getItem() == Items.AIR) {
			ItemStack stack = BlockHardcoreLantern.getLanternItem(world, pos);
			player.setHeldItem(hand, stack);
			removeLantern(world, pos);
			playerMetalSound(world, pos);
			return false;
		}
		return true;
	}

	/**
	 * Removes the lantern. Simply calls setBlockToAir() for regular lanterns, but is used in lantern hooks  
	 */
	protected void removeLantern(World world, BlockPos pos) {
		world.setBlockToAir(pos);
	}

	/**
	 * Try to light the lantern
	 */
	protected boolean tryLight(World world, BlockPos pos, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);

		if (!player.isSneaking()) {
			for (int i = 0; i < lighters.length; i++) {
				if (stack.getItem() == lighters[i]) {

					// Attempt to light the lantern
					if (((TELantern) world.getTileEntity(pos)).light()) {
						if (stack.getItem().isDamageable() && !player.capabilities.isCreativeMode) {
							stack.attemptDamageItem(1, RANDOM, (EntityPlayerMP) player);
						}
						playIgniteSound(world, pos);
						return true;
					}

				}
			}
			if (stack.getItem() == Item.getItemFromBlock(Blocks.WOOL) || stack.getItem() == Item.getItemFromBlock(Blocks.CARPET)) {

				// Attempt to extinguish the lantern
				if (((TELantern) world.getTileEntity(pos)).unlight()) {
					if (stack.getItem().isDamageable() && !player.capabilities.isCreativeMode) {
						stack.attemptDamageItem(1, RANDOM, (EntityPlayerMP) player);
					}
					playExtinguishSound(world, pos);
					return true;
				}

			}
		}
		return true;
	}

	/*
	 * Handles right click behavior, lighting the lantern etc.
	 */
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		// Oil lantern
		Block block = world.getBlockState(pos).getBlock();

		if (block == ModBlocks.lanternUnlit || block == ModBlocks.lanternLit) {
			fillLantern(player, world, player.getHeldItem(hand), (TELantern) world.getTileEntity(pos));
		}

		if (player.isSneaking()) {
			return tryPickup(world, pos, player, hand);
		} else {
			return tryLight(world, pos, player, hand);
		}

	}

	/**
	 * Fills the lantern
	 */
	private void fillLantern(EntityPlayer player, World world, ItemStack stack, TELantern te) {

		if (!world.isRemote) {
			NBTTagCompound nbt;
			if (stack.hasTagCompound()) {
				nbt = stack.getTagCompound();
			} else {
				nbt = new NBTTagCompound();
				nbt.setInteger("Fuel", 0);
				stack.setTagCompound(nbt);
			}

			int maxLanternAdd = TELantern.MAX_FUEL - te.getFuel();
			int maxOilcanSubtract = stack.getTagCompound().getInteger("Fuel");
			int change = Math.min(maxLanternAdd, maxOilcanSubtract);

			System.out.println("L BEFORE " + te.getFuel());
			System.out.println("O BEFORE " + stack.getTagCompound().getInteger("Fuel"));

			te.addFuel(change);
			nbt.setInteger("Fuel", maxOilcanSubtract - change);
			if (change > 0)
				playFillSound(world, player.getPosition());

			System.out.println("L AFTER " + te.getFuel());
			System.out.println("O AFTER " + stack.getTagCompound().getInteger("Fuel"));
		}

	}

	/**
	 * Siphons the lantern
	 */
	private void siphonLantern(EntityPlayer player, World world, ItemStack stack, TELantern te) {
		if (!world.isRemote) {
			NBTTagCompound nbt;
			if (stack.hasTagCompound()) {
				nbt = stack.getTagCompound();
			} else {
				nbt = new NBTTagCompound();
				nbt.setInteger("Fuel", 0);
				stack.setTagCompound(nbt);
			}

			int maxLanternSubtract = te.getFuel();
			int maxOilcanAdd = SurvivalistLightingConfigTorches.lanternFuel - stack.getTagCompound().getInteger("Fuel");
			int change = Math.min(maxLanternSubtract, maxOilcanAdd);

			System.out.println("CHANGE" + change);

			te.removeFuel(change);
			nbt.setInteger("Fuel", stack.getTagCompound().getInteger("Fuel") + change);
		}
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

}
