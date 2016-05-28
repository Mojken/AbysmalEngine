package net.abysmal.engine.graphics.geometry;

import net.abysmal.engine.maths.Vector;

public class Triangle {

	Vector a, b, c, normal;

	public Triangle(Vector a, Vector b, Vector c) {
		this.a = a;
		this.b = b;
		this.c = c;

	}

	public Vector calculateNormal() {
		Vector normal = Vector.ZERO(), u, v;
		u = b.sub(a);
		v = c.sub(a);

		normal.x = u.y * v.z - u.z * v.y;
		normal.y = u.z * v.x - u.x * v.z;
		normal.z = u.x * v.y - u.y * v.x;

		return normal;

	}

}