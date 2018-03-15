package com.github.wolfiewaffle.survivalistlighting.blocks.te;

import com.github.wolfiewaffle.survivalistlighting.blocks.ModBlocks;
import com.github.wolfiewaffle.survivalistlighting.config.SurvivalistLightingConfigTorches;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TELantern extends TileEntity implements ITickable {

	public static final String NAME = "te_lantern";
	/* The ItemBlock uses durability which can be 0, but the TE extinguishes at 0, therefore it has 1 extra */
	public static final int MAX_FUEL = SurvivalistLightingConfigTorches.lanternFuel;

	private int fuel;

	public TELantern() {
		fuel = MAX_FUEL;
	}

	@Override
	public void update() {
		if (!world.isRemote) {
			if (isLit()) {
				fuel--;
			}

			if (fuel < 1) {
				unlight();
				fuel = 0;
			}
		}
	}

	public boolean isLit() {
		if (getBlockType() == ModBlocks.lanternLit) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setInteger("fuel", fuel);

		return tag;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		fuel = tag.getInteger("fuel");
	}

	protected void lightLantern() {
		int oldFuel = fuel;
		world.setBlockState(getPos(), ModBlocks.lanternLit.getDefaultState());
		((TELantern) world.getTileEntity(getPos())).setFuel(oldFuel);
	}

	protected void unlightLantern() {
		int oldFuel = fuel;
		world.setBlockState(getPos(), ModBlocks.lanternUnlit.getDefaultState());
		((TELantern) world.getTileEntity(getPos())).setFuel(oldFuel);
	}

	/**
	 * Attempt to extinguish the lantern
	 * @return if the action can be performed
	 */
	public boolean unlight() {
		if (isLit()) {
			unlightLantern();
			return true;
		}
		return false;
	}

	/**
	 * Attempt to light the lantern
	 * @return if the action can be performed
	 */
	public boolean light() {
		if (fuel > 0 && !isLit()) {
			lightLantern();
			return true;
		}
		return false;
	}

	public void setFuel(int fuel) {
		this.fuel = fuel;
	}

	public int getFuel() {
		return fuel;
	}

	public void addFuel(int fuel) {
		this.fuel += fuel;
	}

	public void removeFuel(int fuel) {
		this.fuel -= fuel;
	}

}
