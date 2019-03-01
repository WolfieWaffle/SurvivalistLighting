package com.github.wolfiewaffle.survivalistlighting.handler;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class OilCanFuelRecipe implements IRecipe {
	private ResourceLocation name;

	@Override
	public IRecipe setRegistryName(ResourceLocation name) {
		this.name = name;
		return this;
	}

	@Override
	public ResourceLocation getRegistryName() {
		return name;
	}

//	@Override
//	public Class<IRecipe> getRegistryType() {
//		return (Class<IRecipe>) this.getClass();
//	}

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean func_194133_a(int p_194133_1_, int p_194133_2_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack getRecipeOutput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<IRecipe> getRegistryType() {
		// TODO Auto-generated method stub
		return null;
	}

}