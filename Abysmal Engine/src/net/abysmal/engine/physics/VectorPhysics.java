package net.abysmal.engine.physics;

import net.abysmal.engine.entities.Entity;
import net.abysmal.engine.maths.Vector;

public class VectorPhysics {

	public static Vector calculateResultant(Vector[] vectors) {
		Vector a = new Vector(0, 0);
		for (Vector v:vectors) {
			a.add(v);
		}
		return a;
	}

	public static Vector calculateAcceleration(Vector v, float mass) {
		return v.multiply(1 / mass);
	}

	public static Vector calculateDirectionalAcceleration(Vector[] vectors, float mass) {
		return calculateAcceleration(calculateResultant(vectors), mass);
	}

	public static Vector calculateDirectionalAcceleration(Entity e) {
		return calculateDirectionalAcceleration(e.forces, e.mass);
	}

	public static Vector generateDrag(Entity e) {
		Vector v = e.getMomentum();
		double percentage = Math.pow(v.calculateLength(), 2) / Math.pow(e.getTerminalVelocity(), 2);
		v.multiply((float) -percentage);
		return v;
	}
}