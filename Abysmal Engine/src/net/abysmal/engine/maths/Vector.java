package net.abysmal.engine.maths;

public class Vector {

	public float x, y, z;

	public Vector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
		this.z = 0;
	}
	
	public Vector multiply(float f) {
		return new Vector(x * f, y * f, z * f);
	}

	public Vector add(Vector v) {
		return new Vector(x + v.x, y + v.y, z + v.z);
	}

	public Vector sub(Vector v) {
		return new Vector(x - v.x, y - v.y, z - v.z);
	}

	public float checkProximity(Vector v) {
		return sub(v).calculateLength();
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}
	
	public float calculateLength() {
		return Math.calculateHypotenuse(x, y, z);
	}
	
	public double calculateRotation() {
		double phi = java.lang.Math.atan(getX() / getY());
		if (getY() != java.lang.Math.abs(getY())) phi += Math.TAU / 2;
		return phi % Math.TAU;
	}
}