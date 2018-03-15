package com.github.wolfiewaffle.survivalistlighting.items.torch;

import com.github.wolfiewaffle.survivalistlighting.SurvivalistLighting;
import com.github.wolfiewaffle.survivalistlighting.config.SurvivalistLightingConfigTorches;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemOilcan extends Item {

	public static final String NAME = "oilcan";

	public ItemOilcan() {
		super();
		setUnlocalizedName(SurvivalistLighting.MODID + "." + NAME);
		setMaxStackSize(1);
		setMaxDamage(SurvivalistLightingConfigTorches.oilcanFuel);
		setCreativeTab(CreativeTabs.TOOLS);
		setNoRepair();
	}

	/*
	 * Blue durability bar
	 */
	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return SurvivalistLighting.barColor;
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		NBTTagCompound nbt;
		if (stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
		} else {
			nbt = new NBTTagCompound();
			nbt.setInteger("Fuel", 0);
			stack.setTagCompound(nbt);
		}
		return true;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1 - ((double) stack.getTagCompound().getInteger("Fuel") / (double) SurvivalistLightingConfigTorches.oilcanFuel);
	}

	/*@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List lores, boolean b) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Fuel")) {
			lores.add("Fuel: " + Integer.toString(stack.getTagCompound().getInteger("Fuel")));
		}
	}*/

}
