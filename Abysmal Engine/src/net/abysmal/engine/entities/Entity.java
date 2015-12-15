package net.abysmal.engine.entities;

import net.abysmal.engine.main.FundamentalGameSpecifics;
import net.abysmal.engine.maths.Vector2;

public class Entity {

	int HP, DEF;
	public boolean moving = false;
	public Vector2 pos = new Vector2(-1, -1);
	public float movementSpeed, sprintMultiplier, crouchMultiplier, momentum;
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

	public float getMovementSpeed() {
		return 0;
	}

	public float getAcceleration() {
		return 0;
	}

	public float getMomentum() {
		return momentum;
	}

	public void setMomentum(float momentum) {
		this.momentum = momentum;
	}
}