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
//		System.out.println(e.hitbox.getHitboxPosition()[1].x + ", " +  a.x + "; " + e.hitbox.getHitboxPosition()[0].x + ", " +  b.x
//				+ "; " + e.hitbox.getHitboxPosition()[1].y + ", " +  a.y + "; " + e.hitbox.getHitboxPosition()[0].y + ", " +  b.y);
		if (e.hitbox.getHitboxPosition()[1].x > a.x && e.hitbox.getHitboxPosition()[0].x < b.x
				&& e.hitbox.getHitboxPosition()[1].y > a.y && e.hitbox.getHitboxPosition()[0].y < b.y) {
			return true;
		} else
			return false;
	}

	public boolean isWithin(Vector v) {
		if (v.x > a.x && v.x < b.x && v.y > a.y && v.y < b.y) {
			return true;
		} else
			return false;
	}

	@Override
	public String toString() {
		return a + ", " + b;
	}

	public int getWidth() {
		return (int) (b.x - a.x);
	}
	
	public int getHeight(){
		return (int) (b.y - a.y);
	}
	
	public Square translate(Vector v){
		return new Square(a.add(v), b.add(v));
	}
	
	public Square scale(float f){
		Vector d = this.d.toVector().multiply(f);
		return new Square(b.sub(d), this.d.toVector().add(a));
	}
}
