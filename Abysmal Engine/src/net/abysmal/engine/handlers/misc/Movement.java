package net.abysmal.engine.handlers.misc;

import net.abysmal.engine.entities.Entity;
import net.abysmal.engine.handlers.HID.Keyboard;
import net.abysmal.engine.maths.Vector;
import net.abysmal.engine.physics.VectorPhysics;

public class Movement {

	double angle;
	int acceleration;
	public static Vector xyPressedKeysOld = Vector.ZERO();
	public static Vector xyPressedKeys = Vector.ZERO();
	public static Vector xyReleasedKeys = Vector.ZERO();
	static boolean[] movementKeys;

	public Movement(Entity e) {}

	public static Vector readMovementButtons() {
		movementKeys = Keyboard.getPressedMovementButtons();

		if (movementKeys[0] && movementKeys[2] || !movementKeys[0] && !movementKeys[2]) xyPressedKeys.y = 0;
		else if (!movementKeys[0] && movementKeys[2]) xyPressedKeys.y = 1;
		else if (movementKeys[0] && !movementKeys[2]) xyPressedKeys.y = -1;

		if (movementKeys[1] && movementKeys[3] || !movementKeys[1] && !movementKeys[3]) xyPressedKeys.x = 0;
		else if (!movementKeys[1] && movementKeys[3]) xyPressedKeys.x = 1;
		else if (movementKeys[1] && !movementKeys[3]) xyPressedKeys.x = -1;

		if (Math.abs(xyPressedKeys.x) < Math.abs(xyPressedKeysOld.x)) xyReleasedKeys.x = -xyPressedKeysOld.x * 2;
		else xyReleasedKeys.x = 0;
		if (Math.abs(xyPressedKeys.y) < Math.abs(xyPressedKeysOld.y)) xyReleasedKeys.y = -xyPressedKeysOld.y * 2;
		else xyReleasedKeys.y = 0;

		xyPressedKeysOld.set(xyPressedKeys);

		return xyPressedKeys;
	}

	public static void translate(Entity entity) {
		Vector v = VectorPhysics.calculateDirectionalAcceleration(entity);
		entity.setMomentum(entity.getMomentum().add(v.multiply(1f / 60)));
		entity.teleport(entity.getPosition().add(entity.getMomentum()));
	}
}