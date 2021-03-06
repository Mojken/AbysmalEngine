package net.abysmal.engine.entities;

import net.abysmal.engine.handlers.HID.Keyboard;
import net.abysmal.engine.maths.Vector;
import net.abysmal.engine.physics.misc.ForceArray;

public class Player extends Entity {

	int EXP, MP;

	public int eyeLevel = 10;
	public ForceArray forceArray;
	public int mass = 80;

	public int getEXP() {
		return EXP;
	}

	public void setEXP(int EXP) {
		this.EXP = EXP;
	}

	public int getMP() {
		return MP;
	}

	public void setMP(int MP) {
		this.MP = MP;
	}

	public Player(Vector position, int mass, ForceArray forceArray) {
		super(position, mass);
		this.forceArray = forceArray;
	}

	public int getWalkMode() {
		return Keyboard.getPressedMovementButtons()[7] ? 2:Keyboard.getPressedMovementButtons()[6] ? 1:0;
	}

	public ForceArray getForceArray() {
		return forceArray;
	}
}