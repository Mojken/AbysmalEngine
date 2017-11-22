package net.abysmal.engine.graphics.rendering3D;

import net.abysmal.engine.graphics.Graphics;
import net.abysmal.engine.maths.Vector;

public class Cube {

	int x = 0;
	int y = 0;
	int z = 10;
	int r = 16;
	Camera c;

	public Cube(Camera c) {
		this.c = c;
	}

	void drawLine(Vector a, Vector b, Graphics g) {
		g.drawLine(c.transform(a), c.transform(b));
	}

	public void render(Graphics g) {
		drawLine(new Vector(x - r, y - r, z), new Vector(x - r, y + r, z), g);
		drawLine(new Vector(x - r, y - r, z), new Vector(x + r, y - r, z), g);
		drawLine(new Vector(x + r, y - r, z), new Vector(x + r, y + r, z), g);
		drawLine(new Vector(x - r, y + r, z), new Vector(x + r, y + r, z), g);

		drawLine(new Vector(x - r, y - r, z + r), new Vector(x - r, y + r, z + r), g);
		drawLine(new Vector(x - r, y - r, z + r), new Vector(x + r, y - r, z + r), g);
		drawLine(new Vector(x + r, y - r, z + r), new Vector(x + r, y + r, z + r), g);
		drawLine(new Vector(x - r, y + r, z + r), new Vector(x + r, y + r, z + r), g);

		drawLine(new Vector(x - r, y - r, z), new Vector(x - r, y - r, z + r), g);
		drawLine(new Vector(x - r, y + r, z), new Vector(x - r, y + r, z + r), g);
		drawLine(new Vector(x + r, y - r, z), new Vector(x + r, y - r, z + r), g);
		drawLine(new Vector(x + r, y + r, z), new Vector(x + r, y + r, z + r), g);
	}
}
