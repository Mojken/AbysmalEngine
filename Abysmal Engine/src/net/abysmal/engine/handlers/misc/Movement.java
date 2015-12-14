package net.abysmal.engine.handlers.misc;

import net.abysmal.engine.entities.Entity;
import net.abysmal.engine.entities.Player;
import net.abysmal.engine.handlers.HID.Keyboard;
import net.abysmal.engine.main.FundamentalGameSpecifics;
import net.abysmal.engine.maths.Math;
import net.abysmal.engine.maths.Vector2;

public class Movement {

	double angle;
	int acceleration;
	int momentum;
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

	void directionalMovement3(int[] keys, Player player) {
		walkToVector(new Vector2(keys[0],keys[1]), player);
	}

	void rotationalMovement2(int[] keys, Player player, int rotation) {

	}

	void rotationalMovement3(int[] keys, Player player, int rotation) {
	}

	void walkToVector(Vector2 vector, Entity entity) {
		double phi = java.lang.Math.atan(vector.getY() / vector.getX());
		if (vector.getX() != java.lang.Math.abs(vector.getX())) phi += Math.TAU / 2;
		entity.x += entity.stepLength * java.lang.Math.sin(phi);
		entity.y += entity.stepLength * java.lang.Math.cos(phi);
	}
	
	void walkToVectorWithRotation(Vector2 vector, Entity entity, int rotation) {
		double phi = java.lang.Math.atan(vector.getY() / vector.getX()) + rotation;
		if (vector.getX() != java.lang.Math.abs(vector.getX())) phi += Math.TAU / 2;
		entity.x += entity.stepLength * java.lang.Math.sin(phi);
		entity.y += entity.stepLength * java.lang.Math.cos(phi);
	}
}