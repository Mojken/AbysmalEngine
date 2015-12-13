package net.abysmal.engine.entities;

import net.abysmal.engine.handlers.HID.Keyboard;

public class Entity {

	int HP, DEF;
	public float x, y;
	public double movementSpeed, sprintMultiplier, crouchMultiplier;
	
	
	public Entity() {}
	
	public int getHP() {
		return HP;
	}

	public void setHP(int HP) {
		this.HP = HP;
	}

	public int getDEF() {
		return DEF;
	}

	public void setDEF(int DEF) {
		this.DEF = DEF;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public double getMovementSpeed() {
		return Keyboard.getPressedMovementButtons()[1] ? movementSpeed * sprintMultiplier : Keyboard.getPressedMovementButtons()[2] ? movementSpeed * crouchMultiplier : movementSpeed;
	}

}