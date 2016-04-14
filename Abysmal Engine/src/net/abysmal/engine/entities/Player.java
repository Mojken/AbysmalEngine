package net.abysmal.engine.entities;

import net.abysmal.engine.handlers.HID.Keyboard;
import net.abysmal.engine.maths.Vector;

public class Player extends Entity {

	int EXP, MP;

	public int eyeLevel = 10;

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

	public Player(Vector position) {
		super(position);
	}

	public int getWalkMode() {
		return Keyboard.getPressedMovementButtons()[7] ? 2:Keyboard.getPressedMovementButtons()[6] ? 1:0;
	}
}