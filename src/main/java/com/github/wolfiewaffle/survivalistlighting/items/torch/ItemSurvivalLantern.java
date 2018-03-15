package com.github.wolfiewaffle.survivalistlighting.items.torch;

import com.github.wolfiewaffle.survivalistlighting.SurvivalistLighting;
import com.github.wolfiewaffle.survivalistlighting.blocks.ModBlocks;
import com.github.wolfiewaffle.survivalistlighting.config.SurvivalistLightingConfigTorches;

import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSurvivalLantern extends ItemBlock {

	public static final String NAME = "lantern";

	public ItemSurvivalLantern(Block block) {
		super(block);
		setUnlocalizedName(SurvivalistLighting.MODID + "." + NAME);
		setMaxStackSize(1);
		setMaxDamage(SurvivalistLightingConfigTorches.lanternFuel);
		setCreativeTab(CreativeTabs.TOOLS);
		setNoRepair();
	}

	/*
	 * Used to drain the fuel of the lantern
	 */
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entityIn, int itemSlot, boolean isSelected) {
		if (stack.getItemDamage() < stack.getMaxDamage()) {
			if (!world.isRemote && entityIn instanceof EntityPlayerMP) {
				stack.attemptDamageItem(1, itemRand, (EntityPlayerMP) entityIn);
			}
		} else {
			ItemStack newStack = new ItemStack(ModBlocks.lanternUnlit, 1, SurvivalistLightingConfigTorches.lanternFuel);
			if (world.isRemote) {
				((EntityPlayerSP) entityIn).inventory.setInventorySlotContents(itemSlot, newStack);
			} else {
				((EntityPlayerMP) entityIn).inventory.setInventorySlotContents(itemSlot, newStack);
			}
		}
	}

	/*
	 * Blue durability bar
	 */
	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return SurvivalistLighting.barColor;
	}

	/*
	 * Always show the bar
	 */
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}

	/*
	 * Don't play re equip animation
	 */
	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return (newStack.getItem() instanceof ItemSurvivalLantern) ? false : true;
	}

}
