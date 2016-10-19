package net.abysmal.engine.maths;

public class Dimension {

	final int w;
	private final int x;
	final int y;
	final int z;
	final int dimensions;

	public Dimension(int width, int height) {
		w = -1;
		x = width;
		y = height;
		z = -1;
		dimensions = 2;
	}

	public Dimension(int width, int height, int depth) {
		w = -1;
		x = width;
		y = height;
		z = depth;
		dimensions = 3;
	}

	public Dimension(int spissitude, int width, int height, int depth) {
		w = spissitude;
		x = width;
		y = height;
		z = depth;
		dimensions = 4;
	}

	public int[] getDimension() {
		switch (dimensions) {
			case 2:
				return new int[] { x, y };
			case 3:
				return new int[] { x, y, z };
			case 4:
				return new int[] { w, x, y, z };
			default:
				return null;
		}
	}

	public int getSpissitude() {
		return w;
	}

	public int getWidth() {
		return x;
	}

	public int getHeight() {
		return y;
	}

	public int getDepth() {
		return z;
	}

	public int getArea() {
		int area = 1;
		for (int i:getDimension()) {
			area *= i;
		}
		return area;
	}
	
	@Override
	public String toString() {
		return (w == -1 ? "":("Spissitude: " + w + "\n")) + "Width: " + x + "\nHeight: " + y + (z == -1 ? "":("\nDepth: " + z));
	}

	public Vector toVector() {
		return new Vector(w, x, y, z);
	}
}