package net.abysmal.engine.maths;

public class Dimension {

	int w, x, y, z, dimensions;

	public Dimension(int width, int height) {
		x = width;
		y = height;
		dimensions = 2;
	}

	public Dimension(int width, int height, int depth) {
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

	public int[] getDimension(){
		switch (dimensions) {
			case 2: return new int[] {x, y};
			case 3: return new int[] {x, y, z};
			case 4: return new int[] {w, x, y, z};
			default: return null;
		}
		
	}
}