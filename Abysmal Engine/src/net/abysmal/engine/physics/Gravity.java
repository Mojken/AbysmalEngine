package net.abysmal.engine.physics;

import net.abysmal.engine.entities.Entity;
import net.abysmal.engine.maths.Vector;

public class Gravity {

	static float terminalVelocity = 300;
	static double gravity = 9.8;

	public static void fall(Entity e) {
		if (!e.onGround) {
			e.forces[1] = new Vector(0, (float) VectorPhysics.fma(e.mass, gravity));
			e.forces[2] = generateDrag(e);
			e.forces[3] = new Vector(0, 0);
		} else if (e.onGround) {
			e.forces[1] = new Vector(0, 0);
			e.forces[2] = generateDrag(e);
			e.forces[3] = new Vector(0, -Math.abs(e.getMomentum().y * 113.33333333339f));
			e.forces[4] = new Vector(0, 0);
			System.out.println(e.getMomentum().y);
			e.setMomentum(new Vector(e.getMomentum().x, 0));
		}
	}

	public static Vector generateDrag(Entity e) {
		terminalVelocity = (float) java.lang.Math.sqrt((2 * e.mass * gravity) / (1.225 * ((e.width / 10) * (e.depth / 10)) * 0.82));
		Vector v = e.getMomentum();
		double percentage = java.lang.Math.pow(v.calculateLength(), 2) / java.lang.Math.pow(terminalVelocity, 2);
		v = v.multiply((float) -percentage);
		return v;
	}
}
