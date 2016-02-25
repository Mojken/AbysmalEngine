package net.abysmal.engine.entities;

import net.abysmal.engine.handlers.HID.Keyboard;
import net.abysmal.engine.maths.Vector;

public class Player extends Entity {

	int EXP, MP;

	public float movementSpeed = 10.0f;
	public float sprintMultiplier = 2.0f;
	public float crouchMultiplier = 0.3f;

	public float crouchMovementSpeed = movementSpeed * crouchMultiplier;
	public float sprintMovementSpeed = movementSpeed * sprintMultiplier;

	public float walkAcc = 1f;
	public float crouchAcc = walkAcc * crouchMultiplier;
	public float sprintAcc = walkAcc * sprintMultiplier;
	
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

	public float getMovementSpeed() {
		return Keyboard.getPressedMovementButtons()[7] ? movementSpeed * crouchMultiplier:Keyboard.getPressedMovementButtons()[6] ? movementSpeed * sprintMultiplier:movementSpeed;
	}

	public float getAcceleration() {
		float acceleration;
		if (moving) {
			if (Keyboard.getPressedMovementButtons()[7]) acceleration = crouchAcc;
			else if (Keyboard.getPressedMovementButtons()[6]) acceleration = sprintAcc;
			else acceleration = walkAcc;
		} else acceleration = -1;
		return acceleration;
	}
}