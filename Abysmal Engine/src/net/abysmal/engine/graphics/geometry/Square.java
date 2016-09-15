package net.abysmal.engine.graphics.geometry;

import java.util.Random;
import net.abysmal.engine.entities.Entity;
import net.abysmal.engine.maths.Dimension;
import net.abysmal.engine.maths.Vector;

public class Square {

	public Vector a, b;
	public Dimension d;
	public int colour;
	
	public Square(Vector a, Vector b) {
		this.a = a;
		this.b = b;
		colour = new Random().nextInt(0xffffff);
		d = new Dimension((int) (b.x - a.x), (int) (b.y - a.y));
	}
	
	public boolean isWithin(Entity e) {
		if (e.hitbox.getHitboxPosition()[1].x > a.x && e.hitbox.getHitboxPosition()[0].x < b.x && e.hitbox.getHitboxPosition()[1].y > a.y && e.hitbox.getHitboxPosition()[0].y < b.y) {
			return true;
		} else return false;
	}
}
