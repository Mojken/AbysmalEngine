package net.abysmal.engine.maths;

import net.abysmal.engine.entities.Entity;

public class Hitbox {

	Vector3[] hitboxVector = new Vector3[2];
	Entity e;

	public Hitbox(Entity entity) {
		e = entity;
		hitboxVector = entity.getHitboxPoints();
	}

	public boolean detectCollision(Hitbox hitbox) {
		Vector3[] a = getHitboxPosition();
		Vector3[] b = hitbox.getHitboxPosition();
		if (b[0].getX() >= a[0].getX() && b[0].getX() <= a[1].getX() || b[1].getX() >= a[0].getX() && b[1].getX() <= a[1].getX()) {
			if (b[0].getY() >= a[0].getY() && b[0].getX() <= a[1].getY() || b[1].getY() >= a[0].getY() && b[1].getY() <= a[1].getY()) {
				if (b[0].getZ() >= a[0].getZ() && b[0].getZ() <= a[1].getZ() || b[1].getZ() >= a[0].getZ() && b[1].getZ() <= a[1].getZ()) return true;
			}
		}
		return false;
	}

	public Vector3[] getHitboxPosition() {
		Vector3[] hitboxPosition = new Vector3[2];
		for (int i = 0; i < 2; i++) {
			hitboxPosition[i] = hitboxVector[i].add(e.pos);
		}
		return hitboxPosition;
	}
}