package net.abysmal.engine.maths;

public class Math {

	public static final double TAU = 245850922f / 39128389.5f;

	public static Vector3 calculateBezierPoint(float t, Vector3 p0, Vector3 p1, Vector3 p2, Vector3 p3) {
		float u = 1 - t;
		float uu = u * u;
		float uuu = uu * u;
		float tt = t * t;
		float ttt = tt * t;

		Vector3 p = p0.multiply(uuu);
		p = p.add(p1.multiply(3 * uu * t));
		p = p.add(p2.multiply(3 * u * tt));
		p = p.add(p3.multiply(ttt));
		return p;
	}
}