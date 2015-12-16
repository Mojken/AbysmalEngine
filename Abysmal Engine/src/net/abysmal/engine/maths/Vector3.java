package net.abysmal.engine.maths;

public class Vector3 {

	public float x, y, z;

	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3(float x, float y) {
		this.x = x;
		this.y = y;
		this.z = 0;
	}
	
	public Vector3 multiply(float f) {
		return new Vector3(x * f, y * f, z * f);
	}

	public Vector3 add(Vector3 v) {
		return new Vector3(x + v.x, y + v.y, z + v.z);
	}

	public Vector3 sub(Vector3 v) {
		return new Vector3(x - v.x, y - v.y, z - v.z);
	}

	public float checkProximity(Vector3 v) {
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
