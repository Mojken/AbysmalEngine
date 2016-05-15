package net.abysmal.engine.maths;

import net.abysmal.engine.entities.Entity;

public class Hitbox {

	Vector[] hitboxVector = new Vector[2];
	Entity e;

	public Hitbox(Entity entity) {
		e = entity;
		hitboxVector = entity.getHitboxPoints();
	}

	public Hitbox(Entity e, Hitbox hitbox) {
		if (hitbox.e == e) return;
		this.e = e;
		hitboxVector = hitbox.hitboxVector;
	}

	public Hitbox(Vector a, Vector b) {
		hitboxVector = new Vector[] { a, b };
		e = null;
	}

	public boolean detectCollision(Hitbox hitbox) {
		Vector[] a = getHitboxPosition();
		Vector[] b = hitbox.getHitboxPosition();
		if (b[0].getW() >= a[0].getW() && b[0].getW() <= a[1].getW() || b[1].getW() >= a[0].getW() && b[1].getW() <= a[1].getW()) {
			if (b[0].getX() >= a[0].getX() && b[0].getX() <= a[1].getX() || b[1].getX() >= a[0].getX() && b[1].getX() <= a[1].getX()) {
				if (b[0].getY() >= a[0].getY() && b[0].getX() <= a[1].getY() || b[1].getY() >= a[0].getY() && b[1].getY() <= a[1].getY()) {
					if (b[0].getZ() >= a[0].getZ() && b[0].getZ() <= a[1].getZ() || b[1].getZ() >= a[0].getZ() && b[1].getZ() <= a[1].getZ()) return true;
				}
			}
		}
		return false;
	}

	public Vector[] getHitboxPoints() {
		return hitboxVector;
	}
	
	public Vector[] getHitboxPosition() {
		if (e == null) return hitboxVector;
		Vector[] hitboxPosition = new Vector[2];
		for (int i = 0; i < 2; i++) {
			hitboxPosition[i] = hitboxVector[i].add(e.pos);
		}
		return hitboxPosition;
	}
}