package net.abysmal.engine.physics;

import net.abysmal.engine.maths.Vector;

public class VectorPhysics {

	public Vector calculateResultant(Vector[] vectors) {
		Vector a = new Vector(0, 0);
		for (Vector v:vectors) {
			a.add(v);
		}
		return a;
	}

	public static Vector calculateAcceleration(Vector v, float mass) {
		return v.multiply(1/mass);
	}

}