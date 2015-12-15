package net.abysmal.engine.handlers.misc;

import net.abysmal.engine.entities.Entity;
import net.abysmal.engine.entities.Player;
import net.abysmal.engine.handlers.HID.Keyboard;
import net.abysmal.engine.maths.Math;
import net.abysmal.engine.maths.Vector2;

public class Movement {

	double angle;
	int acceleration;
	int[] xyPressedKeys;
	boolean[] movementKeys = Keyboard.getPressedMovementButtons();

	public Movement(Entity e) {
		readMovementButtons();
	}

	void readMovementButtons() {
		if (movementKeys[0]) xyPressedKeys[0] += 1;
		else if (!movementKeys[0]) xyPressedKeys[0] -= 1;

		if (movementKeys[1]) xyPressedKeys[1] -= 1;
		else if (!movementKeys[1]) xyPressedKeys[1] += 1;

		if (movementKeys[2]) xyPressedKeys[0] -= 1;
		else if (!movementKeys[2]) xyPressedKeys[0] += 1;

		if (movementKeys[3]) xyPressedKeys[1] += 1;
		else if (!movementKeys[3]) xyPressedKeys[1] -= 1;

		if (movementKeys[0] && movementKeys[1] || !movementKeys[0] && !movementKeys[1]) xyPressedKeys[0] = 0;
		if (movementKeys[2] && movementKeys[3] || !movementKeys[2] && !movementKeys[3]) xyPressedKeys[1] = 0;

		if (java.lang.Math.abs(xyPressedKeys[0]) == 2) xyPressedKeys[0] = 0;
		if (java.lang.Math.abs(xyPressedKeys[1]) == 2) xyPressedKeys[1] = 0;
	}

	void directionalMovement2(int[] keys, Player player) {

	}

	void directionalMovement3(int[] keys, Player player, int clock) {
		walkToVector(new Vector2(keys[0], keys[1]), player, clock);
	}

	void rotationalMovement(int[] keys, Player player, int rotation, int clock) {
		walkToVectorWithRotation(new Vector2(keys[0], keys[1]), player, rotation, clock);
	}

	public static void walkToVector(Vector2 vector, Entity entity, int clock) {
		walkToVectorWithRotation(vector, entity, 0, clock);
	}

	public static void walkToVectorWithRotation(Vector2 vector, Entity entity, int rotation, int clock) {
		if (vector.checkProximity(entity.pos) < 10) return;
		vector = vector.sub(entity.pos);
		double phi = java.lang.Math.atan(vector.getX() / vector.getY()) + rotation;
		if (vector.getY() != java.lang.Math.abs(vector.getY())) phi += Math.TAU / 2;

		if ((int)(clock % (10 / calculateMomentum(entity))) == 0) {
			entity.pos.x += entity.stepLength * java.lang.Math.sin(phi % Math.TAU);
			entity.pos.y += entity.stepLength * java.lang.Math.cos(phi % Math.TAU);
		}
	}

	static float calculateMomentum(Entity entity) {
		if (entity.getMomentum() > entity.getMovementSpeed()) entity.setMomentum(entity.getMomentum() + entity.getAcceleration());
		return entity.getMomentum();
	}
}