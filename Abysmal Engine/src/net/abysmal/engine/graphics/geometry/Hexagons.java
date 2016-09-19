package net.abysmal.engine.graphics.geometry;

import net.abysmal.engine.maths.HexCoord;
import net.abysmal.engine.maths.Vector;

public class Hexagons {

	public static int HexXSpacing = 20, HexYSpacing = 20;

	public static HexCoord PointToHex(int xp, int yp) {
		int q = (int) ((xp * Math.sqrt(3) / 3 - yp / 3) / HexXSpacing);
		int r = yp * 2 / 3 / HexYSpacing;
		return hexRound(q, r);
	}

	public static Vector vectorRound(Vector point) {
		int rx = Math.round(point.x);
		int ry = Math.round(point.y);
		int rz = Math.round(point.z);

		int x_diff = (int) Math.abs(rx - point.x);
		int y_diff = (int) Math.abs(ry - point.y);
		int z_diff = (int) Math.abs(rz - point.z);

		if (x_diff > y_diff && x_diff > z_diff) rx = -ry - rz;
		else if (y_diff > z_diff) ry = -rx - rz;
		else rz = -rx - ry;

		return new Vector(rx, ry, rz);
	}

	public static HexCoord hexRound(float q, float r) {
		return vectorToHex(vectorRound(hexToVector(q, r)));
	}

	public static Vector hexToVector(float q, float r) {
		return new Vector(q, r, -q - r);
	}

	public static HexCoord vectorToHex(Vector h) {
		return new HexCoord((int) h.x, (int) h.z);
	}
}