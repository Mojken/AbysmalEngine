package net.abysmal.engine.graphics;

import net.abysmal.engine.graphics.geometry.Square;
import net.abysmal.engine.maths.Dimension;
import net.abysmal.engine.maths.Vector;

public class Partition {

	double[] xPartitions, yPartitions;
	public Square[] partitions;

	public Partition(double[] xP, double[] yP, Dimension w) {
		this(xP, yP, w, null, null);
	}

	public Partition(double[] xP, double[] yP, Partition p, int index) {
		this(xP, yP, p.partitions[index].d, p.partitions[index].a, null);
	}

	public static Partition generateEvenPartitions(int x, int y, Dimension d) {
		double[] dx = new double[x + 1];
		double[] dy = new double[y + 1];
		for (double ix = 0; ix <= x; ix++) dx[(int) ix] = ix / x;
		for (double iy = 0; iy <= y; iy++) dy[(int) iy] = iy / y;
		return new Partition(dx, dy, d);
	}

	public Partition(double[] xP, double[] yP, Dimension w, Vector topOffset, Vector bottomOffset) {
		if (xP.length == 0) xP[0] = 1;
		if (yP.length == 0) yP[0] = 1;

		if (topOffset == null) {
			topOffset = Vector.ZERO();
		}
		if (bottomOffset == null) bottomOffset = Vector.ZERO();

		xPartitions = xP;
		yPartitions = yP;
// Dimension d = new Dimension((int) (w.getWidth() - topOffset.x - bottomOffset.x), (int) (w.getHeight() - topOffset.y - bottomOffset.y));
		partitions = new Square[(xP.length - 1) * (yP.length - 1)];

		int[] xCoords = new int[xP.length];
		int[] yCoords = new int[yP.length];

		for (int x = 0; x < xP.length; x++) {
			xCoords[x] = (int) (xP[x] * w.getWidth());
		}

		for (int y = 0; y < yP.length; y++) {
			yCoords[y] = (int) (yP[y] * w.getHeight());
		}

		for (int y = 0; y < yP.length - 1; y++) {
			for (int x = 0; x < xP.length - 1; x++) {
				partitions[x + y * (xP.length - 1)] = new Square(new Vector(xCoords[x], yCoords[y]).add(topOffset), new Vector(xCoords[x + 1], yCoords[y + 1]).add(topOffset));
			}
		}
	}
}