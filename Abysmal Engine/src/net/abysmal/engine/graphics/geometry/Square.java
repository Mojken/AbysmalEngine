package net.abysmal.engine.graphics.geometry;

import java.util.Random;
import net.abysmal.engine.entities.Entity;
import net.abysmal.engine.maths.Dimension;
import net.abysmal.engine.maths.Vector;

public class Square {

	public Vector a, b, c, d;
	public Dimension dimension;
	public int colour;

	public Square(Vector a, Vector b) {
		this.a = a;
		this.d = b;
		this.b = new Vector(b.x, a.y);
		this.c = new Vector(a.x, b.y);
		colour = new Random().nextInt(0xffffff);
		dimension = new Dimension((int) (b.x - a.x), (int) (b.y - a.y));
	}

	public boolean isWithin(Entity e) {
		if (e.hitbox.getHitboxPosition()[1].x > a.x && e.hitbox.getHitboxPosition()[0].x < d.x
				&& e.hitbox.getHitboxPosition()[1].y > a.y && e.hitbox.getHitboxPosition()[0].y < d.y) {
			return true;
		} else
			return false;
	}

	public boolean isWithin(Vector v) {
		if (v.x > a.x && v.x < d.x && v.y > a.y && v.y < d.y) {
			return true;
		} else
			return false;
	}

	@Override
	public String toString() {
		return a + ", " + d;
	}

	public int getWidth() {
		return (int) (d.x - a.x);
	}
	
	public int getHeight(){
		return (int) (d.y - a.y);
	}
	
	public Square translate(Vector v){
		return new Square(a.add(v), d.add(v));
	}
	
	public Square scale(float f){
		Vector v = this.dimension.toVector().multiply(f);
		return new Square(d.sub(v), this.dimension.toVector().add(a));
	}
}
