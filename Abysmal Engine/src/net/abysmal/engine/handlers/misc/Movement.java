package net.abysmal.engine.handlers.misc;

import net.abysmal.engine.entities.Entity;
import net.abysmal.engine.entities.Player;
import net.abysmal.engine.handlers.HID.Keyboard;
import net.abysmal.engine.maths.Math;
import net.abysmal.engine.maths.Vector3;

public class Movement {

	double angle;
	int acceleration;
	static int[] xyPressedKeys = new int[2];
	static boolean[] movementKeys;

	public Movement(Entity e) {}

	public static int[] readMovementButtons() {
		movementKeys = Keyboard.getPressedMovementButtons();
// int w, a, s, d;
//
// if (movementKeys[0]) w = 1;
// else if (!movementKeys[0]) w = 0;
//
// if (movementKeys[1]) a = 1;
// else if (!movementKeys[1]) a = 0;
//
// if (movementKeys[2]) s = 1;
// else if (!movementKeys[2]) s = 0;
//
// if (movementKeys[3]) d = 1;
// else if (!movementKeys[3]) d = 0;

		if (movementKeys[0] && movementKeys[2] || !movementKeys[0] && !movementKeys[2]) xyPressedKeys[0] = 0;
		else if (!movementKeys[0] && movementKeys[2]) xyPressedKeys[0] = 1;
		else if (movementKeys[0] && !movementKeys[2]) xyPressedKeys[0] = -1;

		if (movementKeys[1] && movementKeys[3] || !movementKeys[1] && !movementKeys[3]) xyPressedKeys[1] = 0;
		else if (!movementKeys[1] && movementKeys[3]) xyPressedKeys[1] = 1;
		else if (movementKeys[1] && !movementKeys[3]) xyPressedKeys[1] = -1;
		return xyPressedKeys;
	}

	public static void directionalMovement2(int[] keys, Player player) {
		walkToVector(new Vector3(keys[0], 0, 0), player);
	}

	public static void directionalMovement3(Player player) {
		walkToVector(new Vector3(readMovementButtons()[0], readMovementButtons()[1], 0), player);
	}

	void rotationalMovement(int[] keys, Player player, int rotation) {
		walkToVectorWithRotation(new Vector3(keys[0], keys[1], 0), player, rotation);
	}

	static int bezierIndex = 0;

	public static boolean walkToBezier(Vector3[] vector, Entity entity) {
		if (bezierIndex < vector.length) {
			if (walkToVector(vector[bezierIndex], entity)) bezierIndex++;
			return false;
		} else {
			bezierIndex = 0;
			return true;
		}
	}

	public static boolean walkToVector(Vector3 vector, Entity entity) {
		return walkToVectorWithRotation(vector, entity, 0);
	}

	public static boolean walkToVectorWithRotation(Vector3 vector, Entity entity, int rotation) {
//		if (vector.checkProximity(entity.pos) < 2) {
//			entity.moving = false;
//			return true;
//		} else {
//			entity.moving = true;
//		}
//		vector = entity.pos.add(vector);
		double phi = java.lang.Math.atan(vector.getX() / vector.getY()) + rotation;
		if (vector.getY() != java.lang.Math.abs(vector.getY())) phi += Math.TAU / 2;
		System.out.println(vector.getX() + ", " + vector.getY());
		entity.pos.x += /* calculateMomentum(entity) */entity.stepLength * java.lang.Math.sin(phi % Math.TAU);
		entity.pos.y += /* calculateMomentum(entity) */entity.stepLength * java.lang.Math.cos(phi % Math.TAU);
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
