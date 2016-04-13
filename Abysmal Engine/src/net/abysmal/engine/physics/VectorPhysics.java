package net.abysmal.engine.physics;

import net.abysmal.engine.entities.Entity;
import net.abysmal.engine.maths.Vector;

public class VectorPhysics {

	public Vector calculateResultant(Vector[] vectors) {
		Vector a = new Vector(0, 0);
		for (Vector v:vectors) {
			a.add(v);
		}
		return a;
	}
	
	public double calculateLength(Vector v) {
		return Math.sqrt(Math.pow(v.x, 2) + Math.pow(v.y, 2));
	}
	
//	F = m * a
//	a = F/m
	
	public double calculateAcceleration(double Length, Entity e){
		return Length/e.mass;
	}
	
}