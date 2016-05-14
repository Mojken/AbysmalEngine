package net.abysmal.engine.entities;

import java.io.File;
import java.util.ArrayList;
import net.abysmal.engine.maths.Hitbox;
import net.abysmal.engine.maths.Vector;
import net.abysmal.engine.physics.misc.ForceArray;

public class Entity {

	public boolean moving = false;
	public double rotation;
	public Vector pos = new Vector(-1, -1, -1), momentum = new Vector(0, 0, 0);
	public Vector[] hitboxPoints = { new Vector(-1, -1, -1), new Vector(1, 1, 1) }, forces = new Vector[0xC];
	public Hitbox hitbox = new Hitbox(this);
	public ForceArray forceArray;
	public int mass, width, height, depth, eyeLevel, walkmode, ID, HP, DEF, ATC;
	public File texture;
	public boolean template = true;
	public static ArrayList<Entity> entities = new ArrayList<Entity>();

	public Entity(Vector position, int mass) {
		template = false;
		teleport(position);
		this.mass = mass;
		for (int i = 0; i < forces.length; i++) {
			forces[i] = new Vector(0,0);
		}
	}
	
	public Entity(Entity type, Vector position) {
		this(position, type.mass);
	}
	
	public Entity(int id, int mass) {
		this.mass = mass;
	}

	public boolean isMoving() {
		return moving;
	}

	
	public double getRotation() {
		return rotation;
	}

	
	public Vector getPos() {
		return pos;
	}

	
	public Vector[] getForces() {
		return forces;
	}

	
	public ForceArray getForceArray() {
		return forceArray;
	}

	
	public int getMass() {
		return mass;
	}

	
	public int getWidth() {
		return width;
	}

	
	public int getHeight() {
		return height;
	}

	
	public int getDepth() {
		return depth;
	}

	
	public int getEyeLevel() {
		return eyeLevel;
	}

	
	public int getWalkmode() {
		return walkmode;
	}

	public void teleport(Vector v) {
		pos = v;
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

	public double getMovementSpeed() {
		if (walkmode % 3 == walkmode) return forceArray.getMovementSpeeds()[walkmode];
		else return 0;
	}

	public Vector getMomentum() {
		return momentum;
	}

	public void setMomentum(Vector momentum) {
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
	
	public static Entity getEntity(int ID) {
		//TODO Error handling
			return entities.get(ID);
	}
}