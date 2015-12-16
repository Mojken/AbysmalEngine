package net.abysmal.engine.maths;

import net.abysmal.engine.entities.Entity;

public class Hitbox {

	Vector3[] hitboxVector = new Vector3[2];

	public Hitbox(Entity entity) {
		hitboxVector[0] = new Vector3(entity.getX() + entity.getHitboxPoints()[0].getX(),
				entity.getY() + entity.getHitboxPoints()[0].getY(), entity.getZ() + entity.getHitboxPoints()[0].getZ());
		hitboxVector[1] = new Vector3(entity.getX() + entity.getHitboxPoints()[1].getX(),
				entity.getY() + entity.getHitboxPoints()[1].getY(), entity.getZ() + entity.getHitboxPoints()[1].getZ());
	}

	public boolean detectCollition(Hitbox hitbox) {
		if (hitbox.hitboxVector[0].getX() > hitboxVector[0].getX()
				&& hitbox.hitboxVector[0].getX() < hitboxVector[1].getX()
				|| hitbox.hitboxVector[1].getX() > hitboxVector[0].getX()
						&& hitbox.hitboxVector[1].getX() < hitboxVector[1].getX()) {
			if (hitbox.hitboxVector[0].getY() > hitboxVector[0].getY()
					&& hitbox.hitboxVector[0].getX() < hitboxVector[1].getY()
					|| hitbox.hitboxVector[1].getY() > hitboxVector[0].getY()
							&& hitbox.hitboxVector[1].getY() < hitboxVector[1].getY()) {
				if (hitbox.hitboxVector[0].getZ() > hitboxVector[0].getZ()
						&& hitbox.hitboxVector[0].getZ() < hitboxVector[1].getZ()
						|| hitbox.hitboxVector[1].getZ() > hitboxVector[0].getZ()
								&& hitbox.hitboxVector[1].getZ() < hitboxVector[1].getZ()) {
					return true;
				}
			}
		}
		return false;
	}
}
