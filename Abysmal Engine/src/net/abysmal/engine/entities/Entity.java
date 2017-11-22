package net.abysmal.engine.entities;

import java.net.URL;
import java.util.ArrayList;
import net.abysmal.engine.handlers.misc.Movement;
import net.abysmal.engine.maths.Hitbox;
import net.abysmal.engine.maths.Vector;
import net.abysmal.engine.physics.Gravity;
import net.abysmal.engine.physics.misc.ForceArray;

public class Entity {

	public boolean moving = false, onGround = false, kill = false;
	public double rotation, jumpHeight;
	public Vector pos = Vector.ZERO(), momentum = Vector.ZERO(), facing = Vector.ZERO().add(1f);
	public Vector[] hitboxPoints = { new Vector(-1, -1, -1), new Vector(1, 1, 1) }, forces = new Vector[0xC];
	public Hitbox hitbox = new Hitbox(this);
	public ForceArray forceArray;
	public float mass;
	public int width, height, depth, eyeLevel, walkmode, ID, HP, DEF, ATK;
	public String textureStr, path = "entities/";
	public URL textureURL;
	public boolean template = true;
	public static ArrayList<Entity> entityTypes = new ArrayList<Entity>();

	protected Entity() {}
	
	public Entity(Vector position) {
		template = false;
		teleport(position);
	}
	
	public Entity(Vector position, float mass, Hitbox hitbox, String name) {
		template = false;
		teleport(position);
		this.mass = mass;
		for (int i = 0; i < forces.length; i++) {
			forces[i] = new Vector(0, 0);
		}

		this.hitbox = new Hitbox(this, hitbox);
		hitboxPoints = hitbox.getHitboxPoints();

		width = (int) (hitboxPoints[1].x - hitboxPoints[0].x);
		height = (int) (hitboxPoints[1].y - hitboxPoints[0].y);
		depth = (int) (hitboxPoints[1].z - hitboxPoints[0].z);
		walkmode = 0;
		
		textureStr = name;
		textureURL = ClassLoader.getSystemResource(path + name + ".png");
	}

	public Entity(Entity type, Vector position) {
		this(position, type.mass, type.hitbox, type.textureStr);
	}

	public Entity(int id, float mass, Hitbox hitbox, String name) {
		this.mass = mass;
		textureStr = name;
		this.hitbox = hitbox;
	}

	public boolean move() {
		return true;
	}

	public void update() {
		Gravity.fall(this);
		Movement.translate(this);
	}

	public static void kill(Entity e) {
		e = null;
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

	public float getMass() {
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
		if (hitbox == null) return new Vector[] { Vector.ZERO(), Vector.ZERO() };
		return hitbox.getHitboxPoints();
	}

	public void setHitboxPoints(Vector[] hitboxPoints) {
		this.hitboxPoints = hitboxPoints;
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public static Entity getEntity(int ID) {
		// TODO Error handling
		return entityTypes.get(ID);
	}
}