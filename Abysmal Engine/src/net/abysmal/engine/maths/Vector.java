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
		return java.lang.Math.abs(sub(v).x) + java.lang.Math.abs(sub(v).y);
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

}