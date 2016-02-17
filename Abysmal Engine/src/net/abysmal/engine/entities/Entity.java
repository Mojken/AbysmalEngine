package net.abysmal.engine.entities;

import net.abysmal.engine.main.FundamentalGameSpecifics;
import net.abysmal.engine.maths.Hitbox;
import net.abysmal.engine.maths.Vector;

public class Entity {

	int HP, DEF;
	public boolean moving = false;
	public double rotation;
	public Vector pos = new Vector(-1, -1, -1);
	public Vector[] hitboxPoints = { new Vector(-1, -1, -1), new Vector(1, 1, 1) };
	public Hitbox hitbox = new Hitbox(this);
	public float movementSpeed, sprintMultiplier, crouchMultiplier, momentum;
	public double stepLength = FundamentalGameSpecifics.stepLength;
	public int mass, width, height, depth, eyeLevel;

	public Entity(Vector position) {
		teleport(position);
	}

	public void teleport(Vector v) {
		setX(v.x);
		setY(v.y);
		setZ(v.z);
	}
	
	public Vector getPosition() {
		return pos;
	}

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

	public float getZ() {
		return pos.z;
	}

	public void setZ(float z) {
		pos.z = z;
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

	public Vector[] getHitboxPoints() {
		return hitboxPoints;
	}

	public void setHitboxPoints(Vector[] hitboxPoints) {
		this.hitboxPoints = hitboxPoints;
	}

	public Hitbox getHitbox() {
		return hitbox;
	}
}