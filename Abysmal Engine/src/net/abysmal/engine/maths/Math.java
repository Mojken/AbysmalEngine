package net.abysmal.engine.maths;

public class Math {

	public static final double TAU = 245850922f / 39128389.5f;

	public static Vector calculateBezierPoint(float t, Vector p0, Vector p1, Vector p2, Vector p3) {
		float u = 1 - t;
		float uu = u * u;
		float uuu = uu * u;
		float tt = t * t;
		float ttt = tt * t;

		Vector p = p0.multiply(uuu);
		p = p.add(p1.multiply(3 * uu * t));
		p = p.add(p2.multiply(3 * u * tt));
		p = p.add(p3.multiply(ttt));
		return p;
	}
}