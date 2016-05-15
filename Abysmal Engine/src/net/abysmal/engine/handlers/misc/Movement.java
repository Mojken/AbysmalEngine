package net.abysmal.engine.handlers.misc;

import net.abysmal.engine.GlobalVariables;
import net.abysmal.engine.entities.Entity;
import net.abysmal.engine.handlers.HID.Keyboard;
import net.abysmal.engine.maths.Vector;
import net.abysmal.engine.physics.VectorPhysics;

public class Movement {

	double angle;
	int acceleration;
	static int[] xyPressedKeys = {0,0};
	static boolean[] movementKeys;

	public Movement(Entity e) {}

	public static Vector readMovementButtons() {
		movementKeys = Keyboard.getPressedMovementButtons();

		if (movementKeys[0] && movementKeys[2] || !movementKeys[0] && !movementKeys[2]) xyPressedKeys[1] = 0;
		else if (!movementKeys[0] && movementKeys[2]) xyPressedKeys[1] = 1;
		else if (movementKeys[0] && !movementKeys[2]) xyPressedKeys[1] = -1;

		if (movementKeys[1] && movementKeys[3] || !movementKeys[1] && !movementKeys[3]) xyPressedKeys[0] = 0;
		else if (!movementKeys[1] && movementKeys[3]) xyPressedKeys[0] = 1;
		else if (movementKeys[1] && !movementKeys[3]) xyPressedKeys[0] = -1;
		
		return new Vector(xyPressedKeys[0], xyPressedKeys[1]);
	}

	public static void translate(Entity entity) {
		Vector v = VectorPhysics.calculateDirectionalAcceleration(entity);
		entity.setMomentum(entity.getMomentum().add(v.multiply(1f / 60)));
		double phi = v.calculateAngle() + GlobalVariables.getWorldRotation();
		if (phi == 0) return;
		entity.teleport(entity.getPosition().add(new Vector((float) (entity.getMomentum().calculateLength() * java.lang.Math.sin(phi)), (float) (entity.getMomentum().calculateLength() * java.lang.Math.cos(phi)))));
	}
}