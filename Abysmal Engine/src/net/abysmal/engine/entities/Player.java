package net.abysmal.engine.entities;

import net.abysmal.engine.handlers.HID.Keyboard;
import net.abysmal.engine.maths.Vector;

public class Player extends Entity {

	int EXP, res;

	public int eyeLevel = 10;
//	public ForceArray forceArray;
//	public int mass = 80;

	public int getEXP() {
		return EXP;
	}

	public void setEXP(int EXP) {
		this.EXP = EXP;
	}

	public int getMP() {
		return res;
	}

	public void setMP(int MP) {
		this.res = MP;
	}

	public Player(Vector position) {
		super(position);
	}

	public Player(Entity entity, Vector position) {
		super(entity, position);
	}
	
	public void update(){
		super.update();
	}

	@SuppressWarnings("deprecation")
	public int getWalkMode() {
		return Keyboard.getPressedMovementButtons()[7] ? 2:Keyboard.getPressedMovementButtons()[6] ? 1:0;
	}
}