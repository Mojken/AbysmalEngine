package net.abysmal.engine.handlers.misc;

import net.abysmal.engine.Constants;
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
	
	@SuppressWarnings("deprecation")
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

	// applies force vectors in opposite directions in order to walk properly
	public static Vector tempName(Entity p) {
		Vector m = p.momentum;
		double angle = p.forces[0].calculateAngle(), mAngle = m.calculateAngle();
		double angleTreshold = net.abysmal.engine.maths.Math.TAU / 16;
		boolean x = false;
		if (angle <= mAngle + angleTreshold && angle >= mAngle - angleTreshold) {
			if (angle > mAngle) {
				if (mAngle > Constants.RIGHT && mAngle < Constants.UP || mAngle > Constants.LEFT && mAngle < Constants.DOWN) x = true;
				else x = false;
			} else {
				if (mAngle > Constants.RIGHT && mAngle < Constants.UP || mAngle > Constants.LEFT && mAngle < Constants.DOWN) x = false;
				else x = true;
			}
		} else return Vector.ZERO();
		float length = (float) Math.abs(((1 / Math.cos(angle)) * (x ? p.momentum.x:p.momentum.y)));
		if (Math.abs(length) > Math.abs(x ? p.momentum.x:p.momentum.y)) length = Math.abs((x ? p.momentum.x:p.momentum.y));
//		System.out.println(Math.abs((x ? p.momentum.x:p.momentum.y)));
		return new Vector(p.forces[0].calculateAngle(), length);
	}

	public static boolean walkToVectorWithRotation(Vector vector, Entity entity, float rotation, double speed) {
		if (vector.checkProximity(entity.pos) < 3) {
			entity.moving = false;
			return true;
		} else {
			entity.moving = true;
		}
		vector = vector.sub(entity.pos);
		double phi = java.lang.Math.atan(vector.getX() / vector.getY()) + rotation;
		if (vector.getY() != java.lang.Math.abs(vector.getY())) phi += net.abysmal.engine.maths.Math.TAU / 2;
		entity.pos.x += speed * java.lang.Math.sin(phi % net.abysmal.engine.maths.Math.TAU);
		entity.pos.y += speed * java.lang.Math.cos(phi % net.abysmal.engine.maths.Math.TAU);
		return false;
	}

	public static boolean walkToVector(Vector vector, Entity entity, double speed) {
		return walkToVectorWithRotation(vector, entity, 0f, speed);
	}

	public static void moveInAngleWithRotation(double angle, Entity entity, double rotation, int speed) {
		double phi = (angle + rotation) % net.abysmal.engine.maths.Math.TAU;
		entity.pos.x += speed * java.lang.Math.sin(phi);
		entity.pos.y += speed * java.lang.Math.cos(phi);
	}
}