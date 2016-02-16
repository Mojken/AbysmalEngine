package net.abysmal.engine.handlers.misc;

import net.abysmal.engine.entities.Entity;
import net.abysmal.engine.entities.Player;
import net.abysmal.engine.handlers.HID.Keyboard;
import net.abysmal.engine.maths.Math;
import net.abysmal.engine.maths.Vector;

public class Movement {

	double angle;
	int acceleration;
	static int[] xyPressedKeys = new int[2];
	static boolean[] movementKeys;

	public Movement(Entity e) {}

	public static int[] readMovementButtons() {
		movementKeys = Keyboard.getPressedMovementButtons();

		if (movementKeys[0] && movementKeys[2] || !movementKeys[0] && !movementKeys[2]) xyPressedKeys[1] = 0;
		else if (!movementKeys[0] && movementKeys[2]) xyPressedKeys[1] = 1;
		else if (movementKeys[0] && !movementKeys[2]) xyPressedKeys[1] = -1;

		if (movementKeys[1] && movementKeys[3] || !movementKeys[1] && !movementKeys[3]) xyPressedKeys[0] = 0;
		else if (!movementKeys[1] && movementKeys[3]) xyPressedKeys[0] = 1;
		else if (movementKeys[1] && !movementKeys[3]) xyPressedKeys[0] = -1;
		return xyPressedKeys;
	}

	public static void directionalMovement2(int[] keys, Player player) {
		walkToVector(new Vector(keys[0], 0, 0), player, 1);
	}

	public static void directionalMovement3(Player player) {
		walkToVector(new Vector(readMovementButtons()[0], readMovementButtons()[1], 0), player, 1);
	}

	public static void rotationalMovement(int[] keys, Player player, double rotation) {
		walkToVectorWithRotation(new Vector(keys[0], keys[1], 0).add(player.pos), player, rotation, 1);
	}

	static int bezierIndex = 0;

	public static boolean walkToBezier(Vector[] vector, Entity entity) {
		if (bezierIndex < vector.length) {
			if (walkToVector(vector[bezierIndex], entity, 2)) bezierIndex++;
			return false;
		} else {
			bezierIndex = 0;
			return true;
		}
	}

	public static boolean walkToVector(Vector vector, Entity entity, int proximity) {
		return walkToVectorWithRotation(vector, entity, 0, proximity);
	}

	public static boolean walkToVectorWithRotation(Vector vector, Entity entity, double rotation, double proximity) {
		if (vector.checkProximity(entity.pos) < proximity) {
			entity.moving = false;
			return true;
		} else {
			entity.moving = true;
		}
		vector = vector.sub(entity.pos);
		
		double phi = java.lang.Math.atan(vector.getX() / vector.getY()) + rotation;
		if (vector.getY() != java.lang.Math.abs(vector.getY())) phi += Math.TAU / 2;
		System.out.println("Destination: " + vector.getX() + ", " + vector.getY());
		System.out.println("Current location: " + entity.getX() + ", " + entity.getY());
		entity.setX( (float)(entity.getX() + /* calculateMomentum(entity) */entity.stepLength * java.lang.Math.sin(phi % Math.TAU)));
		entity.setY( (float)(entity.getY() + /* calculateMomentum(entity) */entity.stepLength * java.lang.Math.cos(phi % Math.TAU)));
		return false;
	}

	static float calculateMomentum(Entity entity) {
		if (entity.moving) {
			if (entity.getMomentum() < entity.getMovementSpeed()) entity.setMomentum(entity.getMomentum() + entity.getAcceleration());
			if (entity.getMomentum() < 0) entity.setMomentum(0);
		} else {
			if (entity.getMomentum() > 0) entity.setMomentum(entity.getMomentum() + entity.getAcceleration());
			if (entity.getMomentum() < 0) entity.setMomentum(0);
		}
		return entity.getMomentum();
	}
}
