package net.abysmal.engine.maths;

import net.abysmal.engine.Constants;

public class Vector {

	public static final Vector ZERO() {
		return new Vector(0, 0);
	}
	
	public float w, x, y, z;

	public Vector(float w, float x, float y, float z) {
		this.w = w;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector(float x, float y, float z) {
		this.w = 0;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector(float x, float y) {
		this.w = 0;
		this.x = x;
		this.y = y;
		this.z = 0;
	}
	
	public Vector(double angle, float length){
		x = (float) (length * java.lang.Math.cos(angle));
		y = (float) (length * java.lang.Math.sin(angle));
	}
	
	public boolean equals(Vector v){
		if (w == v.w && x == v.x && y == v.y && z == v.z) {
			return true;
		} else return false;
	}
	
	public void set(Vector v) {
		w = v.w;
		x = v.x;
		y = v.y;
		z = v.z;
	}

	public Vector multiply(float f) {
		return new Vector(w * f, x * f, y * f, z * f);
	}

	public Vector multiply(Vector v) {
		return new Vector(w * v.w, x * v.x, y * v.y, z * v.z);
	}
	
	public Vector add(Vector v) {
		return new Vector(w + v.w, x + v.x, y + v.y, z + v.z);
	}

	public Vector add(float f) {
		return new Vector(w + f, x + f, y + f, z + f);
	}

	public Vector sub(Vector v) {
		return new Vector(w - v.w, x - v.x, y - v.y, z - v.z);
	}

	public float checkProximity(Vector v) {
		return sub(v).calculateLength();
	}

	public float getW() {
		return w;
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
		return Math.calculateHypotenuse(w, x, y, z);
	}

	@Override
	public String toString() {
		return "w: " + w + " x: " + x + " y: " + y + " z: " + z;
	}

	public double calculateAngle() {
		if (getX() < 0.1 && getX() > -0.1) {
			if (getY() < 0) {
				return Constants.UP;
			} else if (getY() > 0) {
				return Constants.DOWN;
			} else return 0;
		} else if (getY() < 0.1 && getY() > -0.1) {
			if (getX() < 0) {
				return Constants.LEFT;
			} else if (getX() > 0) {
				return Constants.RIGHT;
			} else return 0;
		} else {
			double phi = java.lang.Math.atan(getX() / getY());
			if (getY() != java.lang.Math.abs(getY())) phi += Math.TAU / 2;
			return phi % Math.TAU;
		}
	}
}