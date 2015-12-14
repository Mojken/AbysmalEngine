package net.abysmal.engine.handlers.misc;

import net.abysmal.engine.entities.Entity;
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
		if (FundamentalGameSpecifics.dimentionMode == FundamentalGameSpecifics.MODE_2D_TOP) {
			topMovementButtons();
		} else if (FundamentalGameSpecifics.dimentionMode == FundamentalGameSpecifics.MODE_2D_SIDE) {
			sideMovement();
		}
	}

	int[] angleToMovement(double angle) {
		return null;
	}

	Vector2 calculateDirection(int[] keys, Entity player) {
		return new Vector2(player.getX() + keys[0], player.getY() + keys[1]);
	}

	void sideMovement() {}

	void topMovementButtons() {
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

	void topMovement(Entity entity) {
		Vector2 walkPoint = calculateDirection(xyPressedKeys, entity);
		angle = java.lang.Math.atan(walkPoint.getY() / walkPoint.getX());
		if (movementKeys[3]) angle += Math.TAU / 2;

		entity.x += (entity.getMovementSpeed() / 10) * java.lang.Math.sin(angle);
		entity.y += (entity.getMovementSpeed() / 10) * java.lang.Math.cos(angle);
	}

	void FPMovement() {}

	void TPMovement() {}
}