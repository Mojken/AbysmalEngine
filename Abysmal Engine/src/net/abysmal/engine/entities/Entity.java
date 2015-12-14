package net.abysmal.engine.entities;

import net.abysmal.engine.handlers.HID.Keyboard;
import net.abysmal.engine.main.FundamentalGameSpecifics;
import net.abysmal.engine.maths.Vector2;

public class Entity {

	int HP, DEF;
	public Vector2 pos = new Vector2(-1, -1);
	public double movementSpeed, sprintMultiplier, crouchMultiplier;
	public double stepLength = FundamentalGameSpecifics.stepLength;
	
	
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
		return pos.x;
	}

	public void setX(float x) {
		pos.x = x;
	}

	public float getY() {
		return pos.y;
	}

	public void setY(float y) {
		pos.y = y;
	}

	public double getMovementSpeed() {
		return Keyboard.getPressedMovementButtons()[1] ? movementSpeed * sprintMultiplier : Keyboard.getPressedMovementButtons()[2] ? movementSpeed * crouchMultiplier : movementSpeed;
	}

}